package com.murtazin.bootstrap.controller;

import com.murtazin.bootstrap.model.Role;
import com.murtazin.bootstrap.model.User;
import com.murtazin.bootstrap.service.RoleService;
import com.murtazin.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String admin(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        model.addAttribute("listOfUsers", userService.allUsers());
        return "/admin/admin";
    }

    @PostMapping()
    public String addUser(@RequestParam("firstName") String firstName,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("age") int age,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("roles") String[] roles) {
        System.out.println(roles);
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleService.getRole("ROLE_" + role));
        }
        User user = new User();
        user.setUsername(firstName);
        user.setLastname(lastName);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("age") int age,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam(required=false, name= "roles") String[] roles) {
        User user = userService.getUserById(id);
        user.setUsername(firstName);
        user.setLastname(lastName);
        user.setAge(age);
        user.setEmail(email);
        if (!password.equals("")) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if(!(roles == null)) {
            Set<Role> roleSet = new HashSet<>();
            for (String role : roles) {
                System.out.println(role);
                roleSet.add(roleService.getRole("ROLE_"+ role));
            }
            user.setRoles(roleSet);
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}
