package ru.khusyainov.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String getHomePage() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "modern-login";
    }
}