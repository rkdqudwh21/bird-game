package com.example.bird.repository;

import com.example.bird.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

// 👉 JpaRepository를 상속하면 기본 CRUD 자동 생성됨
public interface BirdRepository extends JpaRepository<Bird, Long> {
}