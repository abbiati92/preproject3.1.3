package com.example.web.controller;

import com.example.web.model.Role;
import com.example.web.model.User;
import com.example.web.service.RoleService;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminControler {

    private final UserService userService;
    private RoleService roleService;

    public AdminControler(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(ModelMap model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("user", userService.findByUsername(userDetails.getUsername()));
        model.addAttribute("allusers", userService.findAllWithRoles());
        return "/users-list";
    }

    @GetMapping(value = "/user-create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/user-create";
    }

    @PutMapping(value = "/user-update")
    public String editUser(@RequestParam("userId") Integer userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("roles", roleService.getAllRoles());
        return "/user-update";
    }

    @PostMapping(value = "/createUser")
    public String create(@ModelAttribute("user") User user, @RequestParam(name = "roles", required = false) List<Integer> roleId) {
        Set<Role> roleSet = new HashSet<>();
        if (roleId != null) {
            for (Integer role : roleId) {
                roleSet.add(roleService.findRoleById(role));
            }
            user.setUserRoles(roleSet);
        }
        userService.createUser(user);
        return "redirect:/admin";
    }

    @PutMapping(value = "/updateUser")
    public String update(@ModelAttribute("user") User user, @RequestParam(name = "roles", required = false) List<Integer> roleId) {
        Set<Role> roleSet = new HashSet<>();
        if (roleId != null) {
            for (Integer role : roleId) {
                roleSet.add(roleService.findRoleById(role));
            }
            user.setUserRoles(roleSet);
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestParam("userId") Integer userId) {
        userService.deleteUserById(userId);
        return "redirect:/admin";
    }
}
