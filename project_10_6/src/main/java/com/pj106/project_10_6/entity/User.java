package com.pj106.project_10_6.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Getter
@Setter       //추가하신이유가???
@NoArgsConstructor
@Table(name = "Users")
@NoArgsConstructor
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

    @Column(nullable = true)
    private String profile = "/*기본프로필url*/";

    @Enumerated(EnumType.STRING)
    @Column
    private UserRole userRole;

    @Builder
    public User(String loginId, String password, String email, String nickname, String profile) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
        this.userRole = UserRole.USER;
    }

    @Column(nullable = false)
    private boolean userStatus;

    public User(String loginId, String nickname, String password, String email) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = UserRoleEnum.USER;
    }
}
