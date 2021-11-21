package com.crud.controller;

import com.crud.model.User;
import com.crud.service.UserService;
import com.crud.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String printCarsList(ModelMap model) {

        List<User> messages = userService.getAllUsers();
        model.addAttribute("messages", messages);
        return "UsersList";
    }

    @GetMapping(value = "/getUser")
    public String getUser(@RequestParam(name = "id", required = true) Optional<Long> id, Model model) {
        return "";
    }

    @GetMapping(value = "/removeUser")
    public String removeUser(@RequestParam(name = "id", required = true) Optional<Long> id, Model model) {

        userService.removeUser(id.get());
        return "redirect:/";
    }

    @GetMapping(value = "/editUser")
    public String editUser(@RequestParam(name = "id", required = true) Optional<Long> id, Model model) {

        User user = userService.getUser(id.get());
        model.addAttribute("user", user);
        model.addAttribute("action", "/mergeUser?id="+id.get());
        return "User";
    }

    @PostMapping (value = "/mergeUser")
    public String mergeUser(@ModelAttribute("user") User user, ModelMap model) {

        userService.mergeUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("action", "/saveUser");
        return "User";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);
        return "redirect:/";
    }
}
