package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.KhachHang;
import com.project.model.NhanVien;
import com.project.repository.KhachHangRepository;
import com.project.repository.NhanVienRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private KhachHangRepository khachHangService;

    @Autowired
    private NhanVienRepository nhanVienService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        KhachHang khachHang = khachHangService.findByEmail(email);
        NhanVien nhanVien = nhanVienService.findByEmail(email);

        if (khachHang != null && khachHang.getMatKhau().equals(password)) {
            session.setAttribute("user", khachHang);
            session.setAttribute("role", "customer");
            return "redirect:/home";
        } else if (nhanVien != null && nhanVien.getMatKhau().equals(password)) {
            session.setAttribute("user", nhanVien);
            session.setAttribute("role", "employee");
            return "redirect:/admin/curd";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           HttpSession session, Model model) {
        if (khachHangService.findByEmail(email) != null || nhanVienService.findByEmail(email) != null) {
            return "redirect:/profile";
        } else {
        }

        KhachHang newKhachHang = new KhachHang();
        newKhachHang.setTenKH(username);
        newKhachHang.setEmail(email);
        newKhachHang.setMatKhau(password);
        khachHangService.save(newKhachHang);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
}