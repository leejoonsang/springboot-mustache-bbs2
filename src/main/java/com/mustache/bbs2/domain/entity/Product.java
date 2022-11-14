package com.mustache.bbs2.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "product") // 생략해도 무방
public class Product {
    @Id
    @GeneratedValue
    private Long member;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
