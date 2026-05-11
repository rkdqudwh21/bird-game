package com.example.bird.controller;

import com.example.bird.entity.Bird;
import com.example.bird.repository.BirdRepository;
import com.example.bird.service.BirdChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/birds")
public class BirdApiController {

    private final BirdRepository birdRepository;
    private final BirdChatService birdChatService;

    @GetMapping
    public List<Bird> getBirds() {
        return birdRepository.findAll();
    }

    @GetMapping("/{id}")
    public Bird getBird(@PathVariable Long id) {
        return birdRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("새를 찾을 수 없습니다."));
    }

    @PostMapping("/{id}/chat")
    public String chat(
            @PathVariable Long id,
            @RequestBody ChatRequest request
    ) {
        Bird bird = birdRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("새를 찾을 수 없습니다."));

        return birdChatService.chat(
                bird.getName(),
                bird.getStage(),
                bird.getAffection(),
                bird.getPersonality(),
                request.message()
        );
    }

    public record ChatRequest(String message) {
    }
}