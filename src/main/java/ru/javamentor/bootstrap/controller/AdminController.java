package ru.javamentor.bootstrap.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.bootstrap.model.User;
import ru.javamentor.bootstrap.service.RoleService;
import ru.javamentor.bootstrap.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getListUsers(ModelMap model) {
        model.addAttribute("user",userService.findByLogin(SecurityContextHolder.getContext()
                .getAuthentication().getName()));
        model.addAttribute("users",userService.getListUsers());
        model.addAttribute("rolesList",roleService.getAllRoles());
        return "pages/admin";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        userService.editUser(user, request.getParameterValues("rolesSelectedList"));
        return "redirect:/admin";
    }
    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("rolesList",roleService.getAllRoles());
        return "pages/new";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        userService.addUser(user, request.getParameterValues("rolesSelectedList"));
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String removeUser(Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
