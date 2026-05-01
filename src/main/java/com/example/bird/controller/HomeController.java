package com.example.bird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class HomeController {

private int affection = 0;
private String getBirdStage(){
    if (affection>=20){
        return "닭";
    }
    if (affection>=10){
        return "병아리";
    }

    return "알";
}

private String getBirdName(){
    if (affection>=20){
        return "첫번째 닭";
    }
    if (affection>=10){
        return "첫 번째 병아리";
    }
     return "첫 번째 알";
}

    @GetMapping("/")
    public String home(){
        return "index";
    }
    
    @GetMapping("/birds")
    public String birds(Model model){
        model.addAttribute("birdName", getBirdName());
        model.addAttribute("birdStage", getBirdStage());
        model.addAttribute("affection", 0);
        return "birds";
    } 

    @GetMapping("/birds/{id}")
    public String birdDetail(@PathVariable int id,Model model) {
        model.addAttribute("birdName", getBirdName());
        model.addAttribute("birdStage", getBirdStage());
        model.addAttribute("affection", affection);
        model.addAttribute("birdId", id);
        return "bird-detail";
    }

    @PostMapping("/birds/{id}/feed")
    public String feed(@PathVariable int id, RedirectAttributes redirectAttributes){
        affection +=1;
        
        redirectAttributes.addFlashAttribute("messages","먹이를 줬습니다.(+1)");
        return "redirect:/birds/" + id;
    }
}
    
    
