//package ru.clickgroup.vtbmockapi.services.jwt;
//
//import ru.clickgroup.vtbmockapi.services.jwt.impl.UserDetailServiceImpl;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.GenericFilterBean;
//
//
//import java.io.IOException;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//import static org.springframework.util.StringUtils.hasText;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtFilter extends GenericFilterBean {
//
//    public static final int BEGIN_INDEX = 7;
//
//    @Setter
//    @Value("#{'${auth.whitelist}'.split(' ')}")
//    private String[] AUTH_WHITELIST;
//    private final JwtProvider jwtProvider;
//
//    private final UserDetailServiceImpl userDetailService;
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException {
//        boolean isInWhiteList = false;
//        String token = null;
//        String path;
//        if (servletRequest instanceof HttpServletRequest) {
//            path = ((HttpServletRequest) servletRequest).getServletPath();
//            token = getTokenFromRequest((HttpServletRequest) servletRequest);
//            log.debug("Current request url: " + path);
//            AntPathMatcher matcher = new AntPathMatcher();
//            for (String s : AUTH_WHITELIST) {
//                if (matcher.match(s, path)) {
//                    isInWhiteList = true;
//                }
//            }
//        }
//
//
//        if (token != null && !isInWhiteList && jwtProvider.validateToken(token)) {
//            String userLogin = jwtProvider.getLoginFromToken(token);
//            path = ((HttpServletRequest) servletRequest).getServletPath();
//            if (path.contains("/validate-token")) {
//                log.debug(userLogin + " touched: " + path);
//            } else {
//                log.info(userLogin + " touched: " + path);
//            }
//            CustomUserDetails customUserDetails = userDetailService.loadUserByUsername(userLogin);
//            customUserDetails.setToken(token);
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        String bearer = request.getHeader(AUTHORIZATION);
//        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
//            return bearer.substring(BEGIN_INDEX);
//        }
//        return null;
//    }
//}