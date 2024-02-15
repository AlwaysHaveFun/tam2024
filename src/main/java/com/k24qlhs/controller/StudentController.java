package com.k24qlhs.controller;

import com.k24qlhs.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping
    public String home(Model model) {
        String msg = "Hello";
        model.addAttribute("message",msg);
        return "home";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int x, @RequestParam String y) {
        System.out.println(x);
        System.out.println(y);
        return "edit";
    }

    @GetMapping("/nothing/{id}/{name}")
    public String part(@PathVariable int id,@PathVariable String name , Model model) {
        System.out.println(id);
        System.out.println(name);
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return "detail";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        System.out.println(student);
        return "redirect:http://localhost:8080/student/create";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Student student = new Student();
        model.addAttribute("student",student);
        return "create";
    }


}
