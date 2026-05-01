package com.example.bird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }
    
    @GetMapping("/birds")
    public String birds(Model model){
        model.addAttribute("birdName", "첫번째 알");
        model.addAttribute("birdStage", "알");
        model.addAttribute("affection", 0);
        return "birds";
    } 
}