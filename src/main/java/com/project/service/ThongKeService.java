package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.ThongKe;
import com.project.repository.ThongKeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ThongKeService {
    @Autowired
    private ThongKeRepository thongKeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<ThongKe> getAll() {
        return thongKeRepository.findAll();
    }

    @Transactional
    public void generateReport() {
        String doanhThuDatPhong = """
            INSERT INTO ThongKe (loaiBaoCao, noiDung, giaTri, ngayBaoCao)
            SELECT
                'Doanh thu',
                'Tổng doanh thu từ đặt phòng',
                SUM(giaPhong),
                CURRENT_DATE()
            FROM Phong
            WHERE maPhong IN (SELECT maPhong FROM DatPhong);
        """;

        String doanhThuDichVu = """
            INSERT INTO ThongKe (loaiBaoCao, noiDung, giaTri, ngayBaoCao)
            SELECT
                'Doanh thu',
                'Tổng doanh thu từ dịch vụ',
                SUM(giaDV),
                CURRENT_DATE()
            FROM DichVu;
        """;

        String tongLuotKhach = """
            INSERT INTO ThongKe (loaiBaoCao, noiDung, giaTri, ngayBaoCao)
            SELECT
                'Thống kê khách hàng',
                'Tổng số lượt đặt phòng của khách hàng',
                COUNT(maDP),
                CURRENT_DATE()
            FROM DatPhong;
        """;

        entityManager.createNativeQuery(doanhThuDatPhong).executeUpdate();
        entityManager.createNativeQuery(doanhThuDichVu).executeUpdate();
        entityManager.createNativeQuery(tongLuotKhach).executeUpdate();
    }
}
