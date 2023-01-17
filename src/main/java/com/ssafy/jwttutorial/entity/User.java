package com.ssafy.jwttutorial.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

// 데이터베이스 테이블과 1대1로 매핑되는 객체라는 뜻
@Entity
// 실제 테이블명
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "activated")
    private boolean activated;

    // User 객체와 Authority 객체의 다대다 관계를 일대다, 다대일 관계의 조인 테이블로 정의
    // pivot table 의 개념
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}