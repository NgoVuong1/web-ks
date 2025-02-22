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
@Table(name = "ThongKe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThongKe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maBC;

    private String loaiBaoCao;
    private String noiDung;
    private BigDecimal giaTri;
    private LocalDate ngayBaoCao;
}
