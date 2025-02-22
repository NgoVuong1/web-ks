package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.model.DatPhong;

@Repository
public interface DatPhongRepository extends JpaRepository<DatPhong, Integer> {
	@Query("select dp from DatPhong dp join fetch dp.phong where dp.khachHang.maKH = :maKH")
    List<DatPhong> findByKhachHangMaKH(@Param("maKH") Integer maKH);

}