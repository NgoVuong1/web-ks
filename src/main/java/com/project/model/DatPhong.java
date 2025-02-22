package com.project.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DatPhong")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatPhong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDP")
    private Integer maDP;

    @Column(name = "soNguoi")
    private Integer soNguoi;

    @Column(name = "ngayDat")
    private LocalDate ngayDat;

    @Column(name = "ngayTra")
    private LocalDate ngayTra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maKH", referencedColumnName = "maKH")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maNV", referencedColumnName = "maNV")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maPhong", referencedColumnName = "maPhong")
    private Phong phong;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maDV", referencedColumnName = "maDV")
    private DichVu dichVu;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maTT", referencedColumnName = "maTT")
    private ThanhToan thanhToan;

}