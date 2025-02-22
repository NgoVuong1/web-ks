package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.DatPhong;
import com.project.model.DichVu;
import com.project.model.KhachHang;
import com.project.model.NhanVien;
import com.project.model.Phong;
import com.project.model.ThanhToan;
import com.project.model.ThongKe;
import com.project.service.DatPhongService;
import com.project.service.DichVuService;
import com.project.service.KhachHangService;
import com.project.service.NhanVienService;
import com.project.service.PhongService;
import com.project.service.ThanhToanService;
import com.project.service.ThongKeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private KhachHangService khachHangService;
    
    @Autowired
    private NhanVienService nhanVienService;
    
    @Autowired
    private ThongKeService thongKeService;
    
    @Autowired
    private DatPhongService datPhongService;
    
    @Autowired
    private DichVuService dichVuService;
    
    @Autowired
    private PhongService phongService;
    
    @Autowired
    private ThanhToanService thanhToanService;

    @GetMapping("/curd")
    public String showCurdPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("employee")) {
            return "redirect:/home";
        }
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("listKhachHang", khachHangService.getAll());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listNhanVien", nhanVienService.getAll());
        model.addAttribute("datPhong", new DatPhong());
        model.addAttribute("listDatPhong", datPhongService.getAll());
        model.addAttribute("dichVu", new DichVu());
        model.addAttribute("listDichVu", dichVuService.getAll());
        model.addAttribute("phong", new Phong());
        model.addAttribute("listPhong", phongService.getAll());
        model.addAttribute("thanhToan", new ThanhToan());
        model.addAttribute("listThanhToan", thanhToanService.getAll());
        model.addAttribute("content", "adm/curd.html");
        redirectAttributes.addFlashAttribute("message", "Đã tải toàn bộ danh sách quản lý!");
        return "adm/index";
    }

    @PostMapping("/create-kh")
    public String createKhachHang(@ModelAttribute KhachHang khachHang, RedirectAttributes redirectAttributes) {
        khachHangService.saveKhachHang(khachHang);
        redirectAttributes.addFlashAttribute("message", "Thêm khách hàng thành công!");
        return "redirect:/admin/curd";
    }
    
    @PostMapping("/update-kh")
    public String updateKhachHang(@ModelAttribute KhachHang khachHang, HttpSession session, RedirectAttributes redirectAttributes) {
        KhachHang updated = khachHangService.saveKhachHang(khachHang);
        Object sessionUser = session.getAttribute("user");
        if (sessionUser instanceof KhachHang) {
            KhachHang currentUser = (KhachHang) sessionUser;
            if (currentUser.getMaKH().equals(updated.getMaKH())) {
                session.setAttribute("user", updated);
            }
        }
        redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin khách hàng thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/delete-kh/{id}")
    public String deleteKhachHang(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        Object sessionUser = session.getAttribute("user");
        if (sessionUser instanceof KhachHang) {
            KhachHang currentUser = (KhachHang) sessionUser;
            if (currentUser.getMaKH().equals(id)) {
                session.removeAttribute("user");
            }
        }
        khachHangService.deleteKhachHang(id);
        redirectAttributes.addFlashAttribute("message", "Xóa khách hàng thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/edit-kh/{id}")
    public String editKhachHang(@PathVariable("id") Integer id, Model model) {
        Optional<KhachHang> opt = khachHangService.getKhachHangById(id);
        model.addAttribute("khachHang", opt.orElse(new KhachHang()));
        model.addAttribute("listKhachHang", khachHangService.getAll());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listNhanVien", nhanVienService.getAll());
        model.addAttribute("phong", new Phong());
        model.addAttribute("listPhong", phongService.getAll());
        model.addAttribute("dichVu", new DichVu());
        model.addAttribute("listDichVu", dichVuService.getAll());
        model.addAttribute("thanhToan", new ThanhToan());
        model.addAttribute("listThanhToan", thanhToanService.getAll());
        model.addAttribute("datPhong", new DatPhong());
        model.addAttribute("listDatPhong", datPhongService.getAll());
        model.addAttribute("content", "adm/curd.html");
        return "adm/index";
    }
    
    @PostMapping("/create-nv")
    public String createNhanVien(@ModelAttribute NhanVien nhanVien, RedirectAttributes redirectAttributes) {
        nhanVienService.saveNhanVien(nhanVien);
        redirectAttributes.addFlashAttribute("message", "Thêm nhân viên thành công!");
        return "redirect:/admin/curd";
    }
    
    @PostMapping("/update-nv")
    public String updateNhanVien(@ModelAttribute NhanVien nhanVien, HttpSession session, RedirectAttributes redirectAttributes) {
        NhanVien updated = nhanVienService.saveNhanVien(nhanVien);
        Object sessionUser = session.getAttribute("user");
        if (sessionUser instanceof NhanVien) {
            NhanVien currentUser = (NhanVien) sessionUser;
            if (currentUser.getMaNV().equals(updated.getMaNV())) {
                session.setAttribute("user", updated);
            }
        }
        redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin nhân viên thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/delete-nv/{id}")
    public String deleteNhanVien(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        Object sessionUser = session.getAttribute("user");
        if (sessionUser instanceof NhanVien) {
            NhanVien currentUser = (NhanVien) sessionUser;
            if (currentUser.getMaNV().equals(id)) {
                session.removeAttribute("user");
            }
        }
        nhanVienService.deleteNhanVien(id);
        redirectAttributes.addFlashAttribute("message", "Xóa nhân viên thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/edit-nv/{id}")
    public String editNhanVien(@PathVariable("id") Integer id, Model model) {
        Optional<NhanVien> opt = nhanVienService.getNhanVienById(id);
        model.addAttribute("nhanVien", opt.orElse(new NhanVien()));
        model.addAttribute("listNhanVien", nhanVienService.getAll());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("listKhachHang", khachHangService.getAll());
        model.addAttribute("phong", new Phong());
        model.addAttribute("listPhong", phongService.getAll());
        model.addAttribute("dichVu", new DichVu());
        model.addAttribute("listDichVu", dichVuService.getAll());
        model.addAttribute("thanhToan", new ThanhToan());
        model.addAttribute("listThanhToan", thanhToanService.getAll());
        model.addAttribute("datPhong", new DatPhong());
        model.addAttribute("listDatPhong", datPhongService.getAll());
        model.addAttribute("content", "adm/curd.html");
        return "adm/index";
    }
    
    @PostMapping("/create-dp")
    public String createDatPhong(@ModelAttribute DatPhong datPhong, RedirectAttributes redirectAttributes) {
        datPhongService.saveDatPhong(datPhong);
        redirectAttributes.addFlashAttribute("message", "Thêm đặt phòng thành công!");
        return "redirect:/admin/curd";
    }
    
    @PostMapping("/update-dp")
    public String updateDatPhong(@ModelAttribute DatPhong datPhong, RedirectAttributes redirectAttributes) {
        datPhongService.saveDatPhong(datPhong);
        redirectAttributes.addFlashAttribute("message", "Cập nhật đặt phòng thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/delete-dp/{id}")
    public String deleteDatPhong(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        datPhongService.deleteDatPhong(id);
        redirectAttributes.addFlashAttribute("message", "Xóa đặt phòng thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/edit-dp/{id}")
    public String editDatPhong(@PathVariable("id") Integer id, Model model) {
        Optional<DatPhong> opt = datPhongService.getDatPhongById(id);
        model.addAttribute("datPhong", opt.orElse(new DatPhong()));
        model.addAttribute("listDatPhong", datPhongService.getAll());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("listKhachHang", khachHangService.getAll());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listNhanVien", nhanVienService.getAll());
        model.addAttribute("phong", new Phong());
        model.addAttribute("listPhong", phongService.getAll());
        model.addAttribute("dichVu", new DichVu());
        model.addAttribute("listDichVu", dichVuService.getAll());
        model.addAttribute("thanhToan", new ThanhToan());
        model.addAttribute("listThanhToan", thanhToanService.getAll());
        model.addAttribute("content", "adm/curd.html");
        return "adm/index";
    }
    
    @PostMapping("/create-dv")
    public String createDichVu(@ModelAttribute DichVu dichVu, RedirectAttributes redirectAttributes) {
        dichVuService.saveDichVu(dichVu);
        redirectAttributes.addFlashAttribute("message", "Thêm dịch vụ thành công!");
        return "redirect:/admin/curd";
    }
    
    @PostMapping("/update-dv")
    public String updateDichVu(@ModelAttribute DichVu dichVu, RedirectAttributes redirectAttributes) {
        dichVuService.saveDichVu(dichVu);
        redirectAttributes.addFlashAttribute("message", "Cập nhật dịch vụ thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/delete-dv/{id}")
    public String deleteDichVu(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        dichVuService.deleteDichVu(id);
        redirectAttributes.addFlashAttribute("message", "Xóa dịch vụ thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/edit-dv/{id}")
    public String editDichVu(@PathVariable("id") Integer id, Model model) {
        Optional<DichVu> opt = dichVuService.getDichVuById(id);
        model.addAttribute("dichVu", opt.orElse(new DichVu()));
        model.addAttribute("listDichVu", dichVuService.getAll());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("listKhachHang", khachHangService.getAll());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listNhanVien", nhanVienService.getAll());
        model.addAttribute("phong", new Phong());
        model.addAttribute("listPhong", phongService.getAll());
        model.addAttribute("thanhToan", new ThanhToan());
        model.addAttribute("listThanhToan", thanhToanService.getAll());
        model.addAttribute("datPhong", new DatPhong());
        model.addAttribute("listDatPhong", datPhongService.getAll());
        model.addAttribute("content", "adm/curd.html");
        return "adm/index";
    }
    
    @PostMapping("/create-phong")
    public String createPhong(@ModelAttribute Phong phong, RedirectAttributes redirectAttributes) {
        phongService.savePhong(phong);
        redirectAttributes.addFlashAttribute("message", "Thêm phòng thành công!");
        return "redirect:/admin/curd";
    }
    
    @PostMapping("/update-phong")
    public String updatePhong(@ModelAttribute Phong phong, RedirectAttributes redirectAttributes) {
        phongService.savePhong(phong);
        redirectAttributes.addFlashAttribute("message", "Cập nhật phòng thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/delete-phong/{id}")
    public String deletePhong(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        phongService.deletePhong(id);
        redirectAttributes.addFlashAttribute("message", "Xóa phòng thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/edit-phong/{id}")
    public String editPhong(@PathVariable("id") Integer id, Model model) {
        Optional<Phong> opt = phongService.getPhongById(id);
        model.addAttribute("phong", opt.orElse(new Phong()));
        model.addAttribute("listPhong", phongService.getAll());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("listKhachHang", khachHangService.getAll());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listNhanVien", nhanVienService.getAll());
        model.addAttribute("dichVu", new DichVu());
        model.addAttribute("listDichVu", dichVuService.getAll());
        model.addAttribute("thanhToan", new ThanhToan());
        model.addAttribute("listThanhToan", thanhToanService.getAll());
        model.addAttribute("datPhong", new DatPhong());
        model.addAttribute("listDatPhong", datPhongService.getAll());
        model.addAttribute("content", "adm/curd.html");
        return "adm/index";
    }
    
    @PostMapping("/create-tt")
    public String createThanhToan(@ModelAttribute ThanhToan thanhToan, RedirectAttributes redirectAttributes) {
        thanhToanService.saveThanhToan(thanhToan);
        redirectAttributes.addFlashAttribute("message", "Thêm thanh toán thành công!");
        return "redirect:/admin/curd";
    }
    
    @PostMapping("/update-tt")
    public String updateThanhToan(@ModelAttribute ThanhToan thanhToan, RedirectAttributes redirectAttributes) {
        thanhToanService.saveThanhToan(thanhToan);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thanh toán thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/delete-tt/{id}")
    public String deleteThanhToan(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        thanhToanService.deleteThanhToan(id);
        redirectAttributes.addFlashAttribute("message", "Xóa thanh toán thành công!");
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/edit-tt/{id}")
    public String editThanhToan(@PathVariable("id") Integer id, Model model) {
        Optional<ThanhToan> opt = thanhToanService.getThanhToanById(id);
        model.addAttribute("thanhToan", opt.orElse(new ThanhToan()));
        model.addAttribute("listThanhToan", thanhToanService.getAll());
        model.addAttribute("khachHang", new KhachHang());
        model.addAttribute("listKhachHang", khachHangService.getAll());
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listNhanVien", nhanVienService.getAll());
        model.addAttribute("phong", new Phong());
        model.addAttribute("listPhong", phongService.getAll());
        model.addAttribute("dichVu", new DichVu());
        model.addAttribute("listDichVu", dichVuService.getAll());
        model.addAttribute("datPhong", new DatPhong());
        model.addAttribute("listDatPhong", datPhongService.getAll());
        model.addAttribute("content", "adm/curd.html");
        return "adm/index";
    }
    
    @GetMapping("/reset")
    public String reset() {
        return "redirect:/admin/curd";
    }
    
    @GetMapping("/thong-ke")
    public String review(Model model) {
        List<ThongKe> thongKeList = thongKeService.getAll();
        model.addAttribute("listThongKe", thongKeList);
        model.addAttribute("content", "adm/thong-ke.html");
        return "index";
    }
}