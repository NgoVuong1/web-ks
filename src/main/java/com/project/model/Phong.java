package com.project.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Phong")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maPhong")
    private Integer maPhong;

    @Column(name = "loaiPhong")
    private String loaiPhong;

    @Column(name = "trangThaiPhong")
    private String trangThaiPhong;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "giaPhong")
    private BigDecimal giaPhong;

    @Column(name = "thoiGianCapNhat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime thoiGianCapNhat;

    @Column(name = "imagePath")
    private String imagePath;

    @OneToMany(mappedBy = "phong", fetch = FetchType.LAZY)
    private List<DatPhong> datPhongList;
}
