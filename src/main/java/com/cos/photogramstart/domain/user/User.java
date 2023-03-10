package com.cos.photogramstart.domain.user;

//JPA - Java Persistence API(자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

import com.cos.photogramstart.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity//DB에 Table 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    private String website;//웹 사이트
    private String bio;//자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;//사진
    private String role;//권한

    // 나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼 만들지 말것.
    // User를 Select할 때 해당 User id로 등록된 image를 다 가져와
    //Lazy = User를 Select할 때 해당 User id로 등록된 image들을 가져오지마 - 대신 getImages()함수의 image들이 호출될 때 가져와
    //Eager = User를 Select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져와
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist//DB에 INSERT되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
