package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.DanhGia;
import com.project.repository.DanhGiaRepository;

@Service
public class DanhGiaService {
    
    @Autowired
    private DanhGiaRepository danhGiaRepository;

    public List<DanhGia> getAll() {
        return danhGiaRepository.findAll();
    }

    public Optional<DanhGia> getDanhGiaById(int id) {
        return danhGiaRepository.findById(id);
    }

    public DanhGia saveDanhGia(DanhGia danhGia) {
        return danhGiaRepository.save(danhGia);
    }

    public DanhGia update(int id, DanhGia danhGiaDetails) {
        return danhGiaRepository.findById(id).map(danhGia -> {
            danhGia.setDiemDanhGia(danhGiaDetails.getDiemDanhGia());
            danhGia.setNhanXet(danhGiaDetails.getNhanXet());
            danhGia.setNgayDanhGia(danhGiaDetails.getNgayDanhGia());
            danhGia.setKhachHang(danhGiaDetails.getKhachHang());
            return danhGiaRepository.save(danhGia);
        }).orElse(null);
    }

    public boolean deleteDanhGia(int id) {
        if (danhGiaRepository.existsById(id)) {
            danhGiaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<DanhGia> getDanhGiaByKhachHangId(Integer maKH) {
        return danhGiaRepository.findByKhachHangMaKH(maKH);
    }
}
