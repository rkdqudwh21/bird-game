package com.example.bird.controller;

import com.example.bird.entity.Bird;
import com.example.bird.repository.BirdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final BirdRepository birdRepository;

    // 👉 생성자 주입 (Spring이 자동으로 넣어줌)
    public HomeController(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    // 🔥 최초 새 생성 (없으면 하나 만들어줌)
    @GetMapping("/birds")
    public String birds(Model model) {

        // 👉 DB에서 첫 번째 새 가져오기
        Bird bird = birdRepository.findAll().stream().findFirst().orElse(null);

        // 👉 없으면 새로 생성
        if (bird == null) {
            bird = new Bird();
            bird.setName("첫번째 알");
            bird.setAffection(0);
            bird.setStage("알");

            birdRepository.save(bird); // 👉 DB에 저장
        }

        model.addAttribute("bird", bird);
        return "birds";
    }

    // 🔥 상세 페이지
    @GetMapping("/birds/{id}")
    public String birdDetail(@PathVariable Long id, Model model) {

        // 👉 DB에서 새 조회
        Bird bird = birdRepository.findById(id).orElseThrow();

        model.addAttribute("bird", bird);
        return "bird-detail";
    }

    // 🔥 먹이주기
    @PostMapping("/birds/{id}/feed")
    public String feed(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        Bird bird = birdRepository.findById(id).orElseThrow();

        // 👉 친밀도 증가
        bird.setAffection(bird.getAffection() + 1);

        // 👉 성장 단계 변경
        if (bird.getAffection() >= 20) {
            bird.setStage("닭");
            bird.setName("첫번째 닭");
        } else if (bird.getAffection() >= 10) {
            bird.setStage("병아리");
            bird.setName("첫번째 병아리");
        }

        birdRepository.save(bird); // 👉 변경 내용 DB 저장

        redirectAttributes.addFlashAttribute("messages", "먹이를 줬습니다! +1");
        return "redirect:/birds/" + id;
    }

    // 🔥 쓰다듬기
    @PostMapping("/birds/{id}/pet")
    public String pet(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        Bird bird = birdRepository.findById(id).orElseThrow();

        // 👉 친밀도 증가
        bird.setAffection(bird.getAffection() + 2);

        birdRepository.save(bird);

        redirectAttributes.addFlashAttribute("messages", "쓰다듬었습니다! +2");
        return "redirect:/birds/" + id;
    }
}
    
