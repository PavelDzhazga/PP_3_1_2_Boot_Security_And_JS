package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;



@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/admin")
    public String show(Model model, @AuthenticationPrincipal User admin) {
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("userRoles", roleService.getAllRoles());
        model.addAttribute("userNew", new User());

        model.addAttribute("rolesNew", roleService.getAllRoles());
        return "admin";
    }


}
