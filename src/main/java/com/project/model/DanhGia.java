package com.project.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DanhGia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanhGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDG")
    private Integer maDG;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maKH", referencedColumnName = "maKH")
    private KhachHang khachHang;

    @Column(name = "diemDanhGia")
    private Integer diemDanhGia;

    @Column(name = "nhanXet")
    private String nhanXet;

    @Column(name = "ngayDanhGia")
    private LocalDate ngayDanhGia;
}