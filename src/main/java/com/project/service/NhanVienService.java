package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.NhanVien;
import com.project.repository.NhanVienRepository;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    public Optional<NhanVien> getNhanVienById(int id) {
        return nhanVienRepository.findById(id);
    }

    public NhanVien saveNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    public NhanVien update(int id, NhanVien nhanVienDetails) {
        return nhanVienRepository.findById(id).map(nhanVien -> {
            nhanVien.setTenNV(nhanVienDetails.getTenNV());
            nhanVien.setChucVu(nhanVienDetails.getChucVu());
            return nhanVienRepository.save(nhanVien);
        }).orElse(null);
    }

    public boolean deleteNhanVien(int id) {
        if (nhanVienRepository.existsById(id)) {
            nhanVienRepository.deleteById(id);
            return true;
        }
        return false;
    }
}