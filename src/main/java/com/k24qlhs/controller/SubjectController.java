package com.k24qlhs.controller;

import com.k24qlhs.model.Subject;
import com.k24qlhs.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    @GetMapping
    public String getList(Model model){
        ArrayList<Subject> list = SubjectService.getList();
        model.addAttribute("list",list);
        return "subject/getlist";
    }

    @GetMapping("/create")
    public String create(Model model){
        Subject subject = new Subject();
        model.addAttribute("sj",subject);
        return "subject/create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Subject subject){
        SubjectService .create(subject);
        return "redirect:http://localhost:8080/subject";
    }
}
