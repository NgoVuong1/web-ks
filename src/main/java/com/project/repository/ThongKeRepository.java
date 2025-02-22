package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.ThongKe;

@Repository
public interface ThongKeRepository extends JpaRepository<ThongKe, Integer> {
}