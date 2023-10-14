package ru.clickgroup.vtbmockapi.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clickgroup.vtbmockapi.services.jwt.UserAuthenticatable;

import java.util.UUID;

@Entity
@Table(name = "system_r_user", schema = "public", catalog = "user_arm_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserAuthenticatable {
    @GeneratedValue()
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "father_name")

    @Override
    public String getLogin() {
        return email;
    }
}
