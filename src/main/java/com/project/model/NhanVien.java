package com.project.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NhanVien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNV;

    private String tenNV;
    private String gioiTinh;
    private String soDT;
    private String email;
    private LocalDate ngaySinh;
    private String chucVu;
    private BigDecimal luong;
    private LocalDate ngayVaoLam;
    private String trangThaiCV;
    private String matKhau;
    private String imagePath;
}
