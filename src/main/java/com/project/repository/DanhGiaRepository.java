package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.model.DanhGia;

public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {
    List<DanhGia> findByKhachHangMaKH(Integer maKH);
}