package com.project.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DichVu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDV")
    private Integer maDV;

    @Column(name = "tenDV")
    private String tenDV;

    @Column(name = "giaDV")
    private BigDecimal giaDV;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "thoiGianThemDV", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime thoiGianThemDV;

    @OneToMany(mappedBy = "dichVu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatPhong> datPhongList;
}