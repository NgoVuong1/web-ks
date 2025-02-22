package com.project.model;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "ThanhToan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThanhToan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maTT")
	private Integer maTT;

	private BigDecimal soTien;
	private LocalDate ngayTT;
	private String phuongThucTT;
	
	@OneToMany(mappedBy = "thanhToan", fetch = FetchType.LAZY)
	private List<DatPhong> datPhongList; 
}
