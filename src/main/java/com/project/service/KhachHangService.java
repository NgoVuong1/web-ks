package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.KhachHang;
import com.project.repository.KhachHangRepository;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository repository;

    public List<KhachHang> getAll() {
        return repository.findAll();
    }

    public Optional<KhachHang> getKhachHangById(int id) {
        return repository.findById(id);
    }

    public KhachHang saveKhachHang(KhachHang khachHang) {
        return repository.save(khachHang);
    }

    public KhachHang updateKhachHang(int id, KhachHang newKhachHang) {
        return repository.findById(id).map(kh -> {
            kh.setTenKH(newKhachHang.getTenKH());
            kh.setGioiTinh(newKhachHang.getGioiTinh());
            kh.setSoDT(newKhachHang.getSoDT());
            kh.setEmail(newKhachHang.getEmail());
            kh.setNgaySinh(newKhachHang.getNgaySinh());
            kh.setLoaiKH(newKhachHang.getLoaiKH());
            kh.setMatKhau(newKhachHang.getMatKhau());
            return repository.save(kh);
        }).orElse(null);
    }

    public void deleteKhachHang(int id) {
        repository.deleteById(id);
    }
}