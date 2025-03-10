package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    NhanVien findByEmail(String email);
}