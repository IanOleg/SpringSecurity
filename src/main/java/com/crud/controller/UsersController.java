package com.crud.controller;

import com.crud.model.Role;
import com.crud.model.User;
import com.crud.service.RoleService;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    UserDetailsService userDetails;

    @GetMapping(value = "/hello")
    public String printWelcome(ModelMap model) {

        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @GetMapping(value = "/")
    public String loginPage0() {

        return "login";
    }

    @GetMapping(value = "/login")
    public String loginPage() {

        return "login";
    }

    @GetMapping(value = "/admin")
    public String printCarsList(Model model) {

        List<User> messages = userService.getAllUsers();
        model.addAttribute("messages", messages);
        return "UsersList";
    }

    @GetMapping(value = "/admin/removeUser")
    public String removeUser(@RequestParam(name = "loginName", required = true) Optional<String> loginName, Model model) {

        userService.removeUser(loginName.get());
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/editUser")
    public String editUser(@RequestParam(name = "loginName", required = true) Optional<String > loginName, Model model) {

        List<Role> rolesList = roleService.getAllRoles();
        User user = userService.getUser(loginName.get(), true);
        model.addAttribute("user", user);
        model.addAttribute("rolesList", rolesList);
        model.addAttribute("action", "/admin/mergeUser?id="+loginName.get());
        return "UserForAdmin";
    }

    @PostMapping (value = "/admin/mergeUser")
    public String mergeUser(@ModelAttribute("user") User user, Model model) {

        userService.mergeUser(user, true);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/addUser")
    public String addUser(Model model) {

        User user = new User();
        List<Role> rolesList = roleService.getAllRoles();
        model.addAttribute("rolesList", rolesList);
        model.addAttribute("user", user);
        model.addAttribute("action", "/admin/saveUser");
        return "UserForAdmin";
    }

    @PostMapping(value = "/admin/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        user.setPassword("");
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/user/getProfile")
    public String getUser(Model model, Principal principal) {

        model.addAttribute("user", userService.getUser(principal.getName(), true));
        model.addAttribute("action", "/user/mergeUser");
        return "UserForUser";
    }

    @PostMapping(value = "/user/mergeUser")
    public String saveProfile(@ModelAttribute("user") User user, Principal principal) {

        if(!principal.getName().equals(user.getLoginName())){
            return "redirect:/logout";
        }
        userService.mergeUser(user, false);
        return "redirect:/user/getProfile";
    }
}
