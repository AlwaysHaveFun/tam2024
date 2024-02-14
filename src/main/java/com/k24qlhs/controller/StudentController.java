package com.k24qlhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int x, @RequestParam String y) {
        System.out.println(x);
        System.out.println(y);
        return "edit";
    }

    @GetMapping("/nothing/{id}")
    public String part(@PathVariable int id) {
        System.out.println(id);
        return "detail";
    }
    @PostMapping("/save")
    public String save(@RequestParam String name,@RequestParam int age) {
        System.out.println(name);
        System.out.println(age);
        return "redirect:http://localhost:8080/student/create";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }
}
