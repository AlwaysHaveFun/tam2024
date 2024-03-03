package com.k24qlhs.controller;

import com.k24qlhs.model.Score;
import com.k24qlhs.model.Student;
import com.k24qlhs.model.Subject;
import com.k24qlhs.model.TypeScore;
import com.k24qlhs.service.ScoreService;
import com.k24qlhs.service.StudentService;
import com.k24qlhs.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    @GetMapping("/score")
    public String scoreList(Model model){
        ArrayList<Score> list = ScoreService.getList(0);
        model.addAttribute("list",list);
        return "/score/list";
    }
    @GetMapping("/score/create")
    public String createScore(Model model){
        Score score = new Score();
        model.addAttribute(score);
        ArrayList<Student> list = StudentService.getList();
        model.addAttribute("stlist",list);
        ArrayList<TypeScore> typeScores = ScoreService.getListTypeScore();
        model.addAttribute("tlist",typeScores);
        ArrayList<Subject> slist = SubjectService.getList();
        model.addAttribute("slist",slist);
        return "/score/scoreCreate";
    }


    @GetMapping("/score/update")
    public String updateScore(Model model,@RequestParam int id){
        Score score = ScoreService.findScoreById(id);
        model.addAttribute(score);
        ArrayList<Student> list = StudentService.getList();
        model.addAttribute("stlist",list);
        ArrayList<TypeScore> typeScores = ScoreService.getListTypeScore();
        model.addAttribute("tlist",typeScores);
        ArrayList<Subject> slist = SubjectService.getList();
        model.addAttribute("slist",slist);
        System.out.println(id);

        return "/score/update";
    }
    @PostMapping("/score/updateP")
    public String scUpdate(@ModelAttribute Score score){
        ScoreService.updateScore(score);
        return "redirect:http://localhost:8080/student/score";
    }

    @PostMapping("/score/save")
    public String saveSc(@ModelAttribute Score score, @RequestParam int sid) {
        int id = ScoreService.saveScore(score);
        ScoreService.saveScoreDetail(id,sid);
        return "redirect:http://localhost:8080/student/score";
    }

    @GetMapping("/list")
    public String studentList(Model model) {
        ArrayList<Student> list = StudentService.getList();
        model.addAttribute("list",list);
        return "/student/list";
    }

    @GetMapping("/detail")
    public String studenDetail(Model model, @RequestParam int id) {
        ArrayList<Score> list = ScoreService.getList(id);
        model.addAttribute("list",list);
        return "/score/list";
    }
}
