package com.example.bird.entity;

public enum Personality {
    
    SHY("소심함","소심하고 조심스럽게 말합니다."),
    ACTIVE("활발함","활발하고 에너지가 넘치는 말투입니다."),
    TSUNDERE("츤데레","처음에는 차갑지만 점점 다정해지는 말투입니다."),
    GENTLE("온화함","부드럽고 친절한 말투입니다."),
    PROUD("자존심 강함","자신감 넘치고 자존심이 강한 말투입니다.");

    private final String label;
    private final String  promptDescription;

    Personality(String label, String promptDescription) {
        this.label = label;
        this.promptDescription = promptDescription;
    }
    public String getLabel() {
        return label;
    }
    public String getPromptDescription() {
        return promptDescription;
    }
}
