package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.ThanhToan;
import com.project.repository.ThanhToanRepository;

@Service
public class ThanhToanService {
    @Autowired
    private ThanhToanRepository thanhToanRepository;

    public List<ThanhToan> getAll() {
        return thanhToanRepository.findAll();
    }

    public Optional<ThanhToan> getThanhToanById(int id) {
        return thanhToanRepository.findById(id);
    }

    public ThanhToan saveThanhToan(ThanhToan thanhToan) {
        return thanhToanRepository.save(thanhToan);
    }

    public ThanhToan update(int id, ThanhToan thanhToanDetails) {
        return thanhToanRepository.findById(id).map(thanhToan -> {
            thanhToan.setSoTien(thanhToanDetails.getSoTien());
            thanhToan.setPhuongThucTT(thanhToanDetails.getPhuongThucTT());
            return thanhToanRepository.save(thanhToan);
        }).orElse(null);
    }

    public boolean deleteThanhToan(int id) {
        if (thanhToanRepository.existsById(id)) {
            thanhToanRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
