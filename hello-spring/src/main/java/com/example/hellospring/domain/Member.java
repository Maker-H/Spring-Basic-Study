package com.example.hellospring.domain;

import javax.persistence.*;

@Entity // 테이블로 매핑
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // pk고, db가 알아서 생성해주는
    private Long id;

//    @Column(name = "username"), db의 컬럼 이름이 username인 경우
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
