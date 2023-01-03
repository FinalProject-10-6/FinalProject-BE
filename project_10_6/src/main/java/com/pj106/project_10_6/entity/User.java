package com.pj106.project_10_6.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

import static com.pj106.project_10_6.entity.UserRoleEnum.USER;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column
    private String profile = "";

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role = USER;

    @Column(nullable = false)
    private boolean userStatus;

    public User(String loginId, String password, String email, String nickname, String profile) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
        this.role = role;
        getCreatedAt();
        getModifiedAt();
    }
}
