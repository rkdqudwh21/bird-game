package com.example.bird.service;

import com.example.bird.entity.Personality;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BirdChatService {

    @Value("${app.openai.api-key}")
    private String openAiApiKey;

    public String chat(String birdName, String stage, int affection, Personality personality, String userMessage) { {

        System.out.println("API KEY = " + openAiApiKey);

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(openAiApiKey)
                .build();

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model("gpt-4o-mini")
                .addSystemMessage(
        "너는 사용자가 키우는 새 캐릭터야. " +
        "한국어로 1~2문장으로 대답해. " +
        "반드시 성격 설명에 맞는 말투를 우선 적용해. " +
        "너무 비슷한 귀여운 말투만 반복하지 마. "+
        "현재 성장 단계에 맞게 말투도 변화시켜."+
        "현재 새 이름은 " + birdName +
        ", 성장 단계는 " + stage +
        ", 친밀도는 " + affection +
        ", 성격은 " + personality.getLabel() +
        "이야. " +
        "성격 설명: " + personality.getPromptDescription()
)
                .addUserMessage(userMessage)
                .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);

        return chatCompletion.choices()
                .get(0)
                .message()
                .content()
                .orElse("짹짹... 지금은 대답하기 어려워요!");
    }
}
}