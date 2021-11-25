package com.crud.controller;

import com.crud.config.SecurityConfig;
import com.crud.model.User;
import com.crud.service.UserService;
import com.crud.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    UserDetailsService userDetails;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage0() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/admin")
    public String printCarsList(Model model) {

        List<User> messages = userService.getAllUsers();
        model.addAttribute("messages", messages);
        return "UsersList";
    }

    @GetMapping(value = "/getUser")
    public String getUser(Model model, Principal principal) {

        model.addAttribute("user", userDetails.loadUserByUsername(principal.getName()));
        model.addAttribute("action", "/getUser");
        return "User";
    }

    @GetMapping(value = "/admin/removeUser")
    public String removeUser(@RequestParam(name = "id", required = true) Optional<Long> id, Model model) {

        userService.removeUser(id.get());
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/editUser")
    public String editUser(@RequestParam(name = "id", required = true) Optional<Long> id, Model model) {

        User user = userService.getUser(id.get());
        model.addAttribute("user", user);
        model.addAttribute("action", "/admin/mergeUser?id="+id.get());
        return "User";
    }

    @PostMapping (value = "/admin/mergeUser")
    public String mergeUser(@ModelAttribute("user") User user, Model model) {

        userService.mergeUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/addUser")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("action", "/admin/saveUser");
        return "User";
    }

    @PostMapping(value = "/admin/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);
        return "redirect:/admin";
    }
}
