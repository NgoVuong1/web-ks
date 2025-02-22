package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.DichVu;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu, Integer> {
}