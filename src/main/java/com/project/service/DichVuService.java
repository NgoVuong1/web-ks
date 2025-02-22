package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.DichVu;
import com.project.repository.DichVuRepository;

@Service
public class DichVuService {
    @Autowired
    private DichVuRepository dichVuRepository;

    public List<DichVu> getAll() {
        return dichVuRepository.findAll();
    }

    public Optional<DichVu> getDichVuById(int id) {
        return dichVuRepository.findById(id);
    }

    public DichVu saveDichVu(DichVu dichVu) {
        return dichVuRepository.save(dichVu);
    }

    @Transactional
    public DichVu update(int id, DichVu dichVuDetails) {
        return dichVuRepository.findById(id)
                .map(dichVu -> {
                    dichVu.setTenDV(dichVuDetails.getTenDV());
                    dichVu.setGiaDV(dichVuDetails.getGiaDV());
                    return dichVuRepository.save(dichVu);
                })
                .orElseThrow(() -> new RuntimeException("Dịch vụ không tồn tại với ID: " + id));
    }

    @Transactional
    public boolean deleteDichVu(int id) {
        if (dichVuRepository.existsById(id)) {
            try {
                dichVuRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                throw new RuntimeException("Không thể xóa dịch vụ do liên kết dữ liệu.");
            }
        }
        throw new RuntimeException("Dịch vụ không tồn tại với ID: " + id);
    }

	public DichVu findById(Integer maDV) {
        return dichVuRepository.findById(maDV).orElse(null); 

	}
}