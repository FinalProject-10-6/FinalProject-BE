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
    private String profile;

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

}
