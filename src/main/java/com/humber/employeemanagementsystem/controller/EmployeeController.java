package com.humber.employeemanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/")
    public String home(Model model) {
        System.out.println(" marina ");
        model.addAttribute("message", "Hello, JSP in Spring Boot!");
        return "index"; // Refers to WEB-INF/views/index.jsp
    }
}
