package com.project.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.model.DanhGia;
import com.project.model.DatPhong;
import com.project.model.DichVu;
import com.project.model.KhachHang;
import com.project.model.NhanVien;
import com.project.model.Phong;
import com.project.model.ThanhToan;
import com.project.service.DanhGiaService;
import com.project.service.DatPhongService;
import com.project.service.DichVuService;
import com.project.service.KhachHangService;
import com.project.service.NhanVienService;
import com.project.service.PhongService;
import com.project.service.ThanhToanService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private KhachHangService khachHangService;

	@Autowired
	private ThanhToanService thanhToanService;

	@Autowired
	private DanhGiaService danhGiaService;

	@Autowired
	private DichVuService dichVuService;

	@Autowired
	private NhanVienService nhanVienService;

	@Autowired
	private PhongService phongService;

	@Autowired
	private DatPhongService datPhongService;

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("content", "home.html");
		return "index";
	}

	@GetMapping("/rooms")
	public String rooms(@RequestParam(required = false) String roomType,
			@RequestParam(required = false) String checkIn,
			@RequestParam(required = false) String checkOut,
			Model model) {
		List<Phong> danhSachPhong = phongService.timKiem(roomType, checkIn, checkOut);
		model.addAttribute("danhSachPhong", danhSachPhong);
		model.addAttribute("content", "rooms.html");
		return "index";
	}

	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		Optional<Phong> phongOptional = phongService.getPhongById(id);
		if (phongOptional.isPresent()) {
			Phong phong = phongOptional.get();
			model.addAttribute("phong", phong);

			DatPhong datPhong = new DatPhong();
			model.addAttribute("datPhong", datPhong);

			if (phong.getMoTa() != null) {
				List<String> moTaLines = Arrays.asList(phong.getMoTa().split("\n"));
				model.addAttribute("moTaLines", moTaLines);
			} else {
				model.addAttribute("moTaLines", new ArrayList<String>());
			}

			List<DanhGia> danhGiaList = danhGiaService.getAll();
			model.addAttribute("danhGiaList", danhGiaList);

			List<DichVu> dichVuList = dichVuService.getAll();
			model.addAttribute("dichVuList", dichVuList);

			List<ThanhToan> thanhToanList = thanhToanService.getAll();
			model.addAttribute("thanhToanList", thanhToanList);
			model.addAttribute("thanhToan", new ThanhToan());

		} else {
			model.addAttribute("errorMessage", "Phòng không tồn tại!");
		}
		model.addAttribute("content", "detail.html");
		return "index";
	}


	@GetMapping("/review")
	public String review(Model model) {
		model.addAttribute("content", "review.html");
		return "index";
	}

	@GetMapping("/profile")
	public String profile(HttpSession session, Model model) {
		Object user = session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", user);

		if (user instanceof KhachHang) {
			KhachHang kh = (KhachHang) user;
			List<DatPhong> danhSachPhongDat = datPhongService.getBookingsByKhachHangId(kh.getMaKH());
			model.addAttribute("danhSachPhongDat", danhSachPhongDat);
		} else {
			model.addAttribute("danhSachPhongDat", null);
		}

		model.addAttribute("content", "ho-so/profile.html");
		return "index";
	}

	@PostMapping("/profile")
	public String editProfile(HttpSession session, Model model,
			@ModelAttribute KhachHang khachHang, @ModelAttribute NhanVien nhenVien,
			RedirectAttributes redirectAttributes) {
		Object user = session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", user);

		if (user instanceof KhachHang) {
			KhachHang updatedUser = khachHangService.saveKhachHang(khachHang);
			session.setAttribute("user", updatedUser);
		} else if (user instanceof NhanVien) {
			NhanVien updatedUser = nhanVienService.saveNhanVien(nhenVien);
			session.setAttribute("user", updatedUser);
		}
		redirectAttributes.addFlashAttribute("message", "Cập nhật hồ sơ thành công!");
		return "redirect:/profile";
	}

	@PostMapping("/dat-phong/{id}")
	public String booking(
			@PathVariable("id") Integer id,
			Model model,
			@ModelAttribute DatPhong datPhong,
			RedirectAttributes redirectAttributes,
			HttpSession session) {

		if (session.getAttribute("user") != null) {
			KhachHang user = (KhachHang) session.getAttribute("user");
			datPhong.setKhachHang(user);
		}

		if (session.getAttribute("user") != null && session.getAttribute("user") instanceof NhanVien) {
			NhanVien nhanVien = (NhanVien) session.getAttribute("user");
			datPhong.setNhanVien(nhanVien);
		} else {
			datPhong.setNhanVien(null);
		}

		Optional<Phong> phongOptional = phongService.getPhongById(id);
		if (phongOptional.isPresent()) {
			Phong phong = phongOptional.get();
			datPhong.setPhong(phong); 
		} else {
			redirectAttributes.addFlashAttribute("error", "Phòng không tồn tại!");
			return "redirect:/rooms";
		}

		ThanhToan thanhToan = datPhong.getThanhToan();
		if (thanhToan != null && thanhToan.getMaTT() == null) {
			thanhToanService.saveThanhToan(thanhToan);
		}

		datPhongService.saveDatPhong(datPhong);

		redirectAttributes.addFlashAttribute("message", "Đặt phòng thành công!");
		return "redirect:/rooms";
	}

	@PostMapping("/huy-dat-phong/{id}")
	public String huyDatPhong(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		datPhongService.deleteDatPhong(id);
		redirectAttributes.addFlashAttribute("message", "Hủy đặt phòng thành công!");
		return "redirect:/profile";
	}

	@PostMapping("/thanh-toan/{id}")
	public String thanhToan(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Optional<DatPhong> datPhongOpt = datPhongService.getDatPhongById(id);
		if (datPhongOpt.isPresent()) {
			DatPhong datPhong = datPhongOpt.get();
			ThanhToan thanhToan = new ThanhToan();
			thanhToan.setSoTien(datPhong.getPhong().getGiaPhong());
			thanhToan.setNgayTT(java.time.LocalDate.now());
			thanhToan.setPhuongThucTT("Online");
			thanhToanService.saveThanhToan(thanhToan);

			datPhong.setThanhToan(thanhToan);
			datPhongService.saveDatPhong(datPhong);

			redirectAttributes.addFlashAttribute("message", "Thanh toán thành công!");
			return "redirect:/profile";
		}
		redirectAttributes.addFlashAttribute("error", "Lỗi thanh toán!");
		return "redirect:/profile";
	}

	@PostMapping("/danh-gia/{id}")
	public String submitReview(@PathVariable("id") Integer id, 
			@ModelAttribute DanhGia danhGia, 
			HttpSession session, 
			RedirectAttributes redirectAttributes) {
		Object user = session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}
		if (user instanceof KhachHang) {
			KhachHang khachHang = (KhachHang) user;
			danhGia.setKhachHang(khachHang);

			Optional<Phong> phongOptional = phongService.getPhongById(id);
			if (phongOptional.isPresent()) {
				Phong phong = phongOptional.get();
				danhGia.setNgayDanhGia(LocalDate.now());
				danhGiaService.saveDanhGia(danhGia);

				redirectAttributes.addFlashAttribute("message", "Đánh giá thành công!");
			} else {
				redirectAttributes.addFlashAttribute("error", "Phòng không tồn tại!");
			}
		}
		return "redirect:/profile";
	}

}