package com.example.bird.controller;

import com.example.bird.service.BirdChatService;

import java.util.Random;

import com.example.bird.entity.Bird;
import com.example.bird.entity.Personality;
import com.example.bird.repository.BirdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class HomeController {

    private final BirdRepository birdRepository;
    private final BirdChatService birdChatService;
    private Personality getRandomPersonality() {
    Personality[] personalities = Personality.values();
    int index = new Random().nextInt(personalities.length);
    return personalities[index];
}

    // 👉 생성자 주입 (Spring이 자동으로 넣어줌)
    public HomeController(BirdRepository birdRepository, BirdChatService birdChatService) {
        this.birdRepository = birdRepository;
        this.birdChatService = birdChatService;
    }

    // 🔥 내 새 목록 조회
@GetMapping("/birds")
public String birds(Model model) {

    // 👉 DB에 저장된 모든 새를 가져온다.
    // 👉 새 뽑기를 여러 번 하면 여러 마리가 리스트로 조회된다.
    model.addAttribute("birds", birdRepository.findAll());

    return "birds";
}
// 🔥 새 뽑기
@PostMapping("/birds/draw")
public String drawBird(RedirectAttributes redirectAttributes) {

    // 👉 새로운 새 객체 생성
    Bird bird = new Bird();

    // 👉 처음 뽑힌 새는 항상 알 상태로 시작
    bird.setName("새로운 알");
    bird.setAffection(0);
    bird.setStage("알");

   int random = new Random().nextInt(100);

   if(random < 60){
    bird.setRarity("COMMON");
    bird.setEggPattern("기본 알");
    bird.setHatchRate(70);
   }else if(random < 85){
    bird.setRarity("RARE");
    bird.setEggPattern("파란 알");
    bird.setHatchRate(50);
   }else if(random < 95){
    bird.setRarity("EPIC");
    bird.setEggPattern("황금 알");
    bird.setHatchRate(30);
   }else{
    bird.setRarity("LEGENDARY");
    bird.setEggPattern("전설 알");    
    bird.setHatchRate(10);
   }
   bird.setImageUrl(getImageUrlByStageAndRarity(bird.getStage(), bird.getRarity()));
   bird.setPersonality(getRandomPersonality());
   birdRepository.save(bird);



    // 👉 목록 페이지에서 보여줄 1회성 메시지
    redirectAttributes.addFlashAttribute("message", "새로운 알을 뽑았습니다!");

    return "redirect:/birds";
}

    // 🔥 상세 페이지
    @GetMapping("/birds/{id}")
    public String birdDetail(@PathVariable Long id, Model model,RedirectAttributes redirectAttributes) {

        // 👉 DB에서 새 조회
        Bird bird = birdRepository.findById(id).orElse(null);

        if (bird == null){
            redirectAttributes.addFlashAttribute("message","존재하지 않는 새입니다.");
            return "redirect:/birds";
        }

        model.addAttribute("bird", bird);
        return "bird-detail";
    }

    // 🔥 먹이주기
    @PostMapping("/birds/{id}/feed")
    public String feed(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        Bird bird = birdRepository.findById(id).orElse(null);

        if(bird==null){
            redirectAttributes.addFlashAttribute("message", "존재하지 않는 새입니다.");
            return "redirect:/birds";
        }

        int newAffection = Math.min(bird.getAffection()+1,100);
        bird.setAffection(newAffection);

        // 👉 성장 단계 변경
        if (bird.getAffection() >= 20) {
            bird.setStage("어른 새");
            bird.setName(bird.getRarity() + " 어른 새");
        } else if (bird.getAffection() >= 10) {
            bird.setStage("아기 새");
            bird.setName(bird.getRarity() + " 아기 새");
}

bird.setImageUrl(getImageUrlByStageAndRarity(bird.getStage(), bird.getRarity()));
        birdRepository.save(bird); // 👉 변경 내용 DB 저장

        redirectAttributes.addFlashAttribute("message", "먹이를 줬습니다! +1");
        return "redirect:/birds/" + id;
    }

    // 🔥 쓰다듬기
    @PostMapping("/birds/{id}/pet")
    public String pet(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        Bird bird = birdRepository.findById(id).orElse(null);

          if(bird==null){
            redirectAttributes.addFlashAttribute("message", "존재하지 않는 새입니다.");
            return "redirect:/birds";
        }

        int newAffection = Math.min(bird.getAffection()+2,100);
        bird.setAffection(newAffection);

         if (bird.getAffection() >= 20) {
            bird.setStage("어른 새");
            bird.setName(bird.getRarity() + " 어른 새");
        } else if (bird.getAffection() >= 10) {
            bird.setStage("아기 새");
        bird.setName(bird.getRarity() + " 아기 새");
}

        bird.setImageUrl(getImageUrlByStageAndRarity(bird.getStage(), bird.getRarity()));
        birdRepository.save(bird);

        redirectAttributes.addFlashAttribute("message", "쓰다듬었습니다! +2");
        return "redirect:/birds/" + id;
    }
    // 새와 대화하기
    @PostMapping("/birds/{id}/chat")
    public String chatWithBird(
        @PathVariable Long id,
        @RequestParam String message,
        RedirectAttributes redirectAttributes
    ){
        Bird bird= birdRepository.findById(id).orElse(null);

        if(bird == null){
            redirectAttributes.addFlashAttribute("message","존재하지 않는 새입니다.");
            return "redirect:/birds";
        }

        String response = birdChatService.chat(
        bird.getName(),
        bird.getStage(),
        bird.getAffection(),
        bird.getPersonality(),
        message
);
            
        redirectAttributes.addFlashAttribute("chatResponse", response);

        return "redirect:/birds/"+id;
        }
    private String getImageUrlByStageAndRarity(String stage, String rarity){
        if("알".equals(stage)){
        if("RARE".equals(rarity)){
            return "/images/birds/blue-egg.jpg";
        }else if("EPIC".equals(rarity)){
            return "/images/birds/golden-egg.jpg";
        }else if("LEGENDARY".equals(rarity)){
            return "/images/birds/legendary-egg.jpg";
        }else{
            return "/images/birds/egg.jpg";
        }
    }
        if("아기 새".equals(stage)){
            if  ("RARE".equals(rarity)){
                return "/images/birds/rare-chick.png";
            }else if("EPIC".equals(rarity)){
                return "/images/birds/epic-chick.png"; 
            }else if("LEGENDARY".equals(rarity)){
                return "/images/birds/legendary-chick.png";
            }else{
                return "/images/birds/common-chick.png";
            }
        }else if("어른 새".equals(stage)){
            if("RARE".equals(rarity)){
                return "/images/birds/rare-bird.png";
            }else if("EPIC".equals(rarity)){
                return "/images/birds/epic-bird.png";
            }else if("LEGENDARY".equals(rarity)){
                return "/images/birds/legendary-bird.png";
            }else{
                return "/images/birds/common-bird.png";
            }
        }
        return "/images/birds/egg.jpg"; // 기본 이미지
    }
}

    
