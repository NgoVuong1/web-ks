package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.DatPhong;
import com.project.repository.DatPhongRepository;

@Service
public class DatPhongService {
    @Autowired
    private DatPhongRepository datPhongRepository;

    public List<DatPhong> getAll() {
        return datPhongRepository.findAll();
    }

    public Optional<DatPhong> getDatPhongById(int id) {
        return datPhongRepository.findById(id);
    }

    public DatPhong saveDatPhong(DatPhong datPhong) {
        return datPhongRepository.save(datPhong);
    }

    public DatPhong update(int id, DatPhong datPhongDetails) {
        return datPhongRepository.findById(id).map(datPhong -> {
            datPhong.setNgayDat(datPhongDetails.getNgayDat());
            datPhong.setNgayTra(datPhongDetails.getNgayTra());
            return datPhongRepository.save(datPhong);
        }).orElse(null);
    }

    public boolean deleteDatPhong(int id) {
        if (datPhongRepository.existsById(id)) {
            datPhongRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<DatPhong> getBookingsByKhachHangId(Integer maKH) {
        return datPhongRepository.findByKhachHangMaKH(maKH);
    }
}