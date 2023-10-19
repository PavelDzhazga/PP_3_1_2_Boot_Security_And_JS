package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    // Create
    @PostMapping("")
    public String createUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin";
    }



    //Update
    @PostMapping("/{id}")
    public String edit(Model model, @ModelAttribute("user") User user, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roleList", userService.getAllUser());
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    // Delete
    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @GetMapping()
    public String show(Model model, @AuthenticationPrincipal User admin) {
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("userRoles", roleService.getAllRoles());
        model.addAttribute("userNew", new User());
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("rolesNew", roles);
        model.addAttribute("rolesNew", roleService.getAllRoles());
        return "admin";
    }
}
