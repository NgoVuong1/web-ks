package com.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Phong;
import com.project.repository.PhongRepository;

@Service
public class PhongService {
    @Autowired
    private PhongRepository phongRepository;

    public List<Phong> getAll() {
        return phongRepository.findAll();
    }

    public Optional<Phong> getPhongById(int id) {
        return phongRepository.findById(id);
    }

    public Phong savePhong(Phong phong) {
        return phongRepository.save(phong);
    }

    public Phong update(int id, Phong phongDetails) {
        return phongRepository.findById(id).map(phong -> {
            phong.setLoaiPhong(phongDetails.getLoaiPhong());
            phong.setTrangThaiPhong(phongDetails.getTrangThaiPhong());
            return phongRepository.save(phong);
        }).orElse(null);
    }

    public boolean deletePhong(int id) {
        if (phongRepository.existsById(id)) {
            phongRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Phong> timKiem(String roomType, String checkIn, String checkOut) {
        List<Phong> danhSachPhong = phongRepository.findAll();

        if (roomType != null && !roomType.isEmpty()) {
            danhSachPhong = danhSachPhong.stream()
                .filter(p -> p.getLoaiPhong().equals(roomType))
                .collect(Collectors.toList());
        }
        return danhSachPhong;
    }

}
