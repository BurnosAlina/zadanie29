package com.example.zadanie29;

import com.example.zadanie29.user.UserInfoDto;
import com.example.zadanie29.user.UserInfoService;
import com.example.zadanie29.userRole.UserRoleDto;
import com.example.zadanie29.userRole.UserRoleService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class AppController {

    private final UserInfoService userInfoService;

    private final UserRoleService userRoleService;

    public AppController(UserInfoService userInfoService, UserRoleService userRoleService) {
        this.userInfoService = userInfoService;
        this.userRoleService = userRoleService;
    }

    @RequestMapping("/")
    String home(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfoDto user = userInfoService.findByEmail(userName);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/edit")
    String edit(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfoDto userInfoDto = userInfoService.findByEmail(userName);
        if (userInfoDto != null) {
            model.addAttribute("user", userInfoDto);
        }
        return "edit";
    }

    @PostMapping("/edit")
    String editUserInfo(UserInfoDto userInfoDto, Model model) {
        UserInfoDto savedUser = userInfoService.save(userInfoDto);
        model.addAttribute("user", savedUser);
        return "/index";
    }

    @GetMapping("/usersList")
    String userList(Model model) {
        model.addAttribute("users", userInfoService.findAll());
        return "usersList";
    }

    @GetMapping("/changeRole/{id}")
    String changeRole(@PathVariable Long id, Model model) {
        UserInfoDto userInfoDto = userInfoService.findById(id);
        model.addAttribute("user", userInfoDto);
        model.addAttribute("roles", userRoleService.findAll());
        return "changeRole";
    }

    @PostMapping("/changeRole")
    String changeUserRoles(UserInfoDto userInfoDto, Model model) {
        userInfoService.save(userInfoDto);
        model.addAttribute("users", userInfoService.findAll());
        return "usersList";
    }
}
