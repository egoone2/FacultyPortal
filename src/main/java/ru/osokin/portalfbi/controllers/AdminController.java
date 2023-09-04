package ru.osokin.portalfbi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.osokin.portalfbi.models.User;
import ru.osokin.portalfbi.security.UserDetailsImpl;
import ru.osokin.portalfbi.services.security.AdminService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/showUserInfo")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails);
        return "auth/userinfo";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, String keyword) {
        List<User> users = adminService.findByKeyword(keyword);
        model.addAttribute("users", users);
        return "auth/admin";
    }


    @PostMapping("/teacher/{id}")
    public String giveTeacherRole(@PathVariable("id") Long id) {
        adminService.giveTeacherRole(id);
        return "redirect:/admin";
    }

    @PostMapping("/student/{id}")
    public String giveStudentRole(@PathVariable("id") Long id) {
        adminService.giveStudentRole(id);
        return "redirect:/admin";
    }
}
