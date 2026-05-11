# 🐦 Bird Raising Project (새 키우기 프로젝트)
- Spring Boot 기반 웹 서비스
- OpenAI API 연동 AI 대화 기능 구현

## 📌 프로젝트 소개

간단한 **새 키우기 웹 서비스**입니다.
사용자는 새를 뽑고, 먹이를 주거나 쓰다듬으며 성장시키는 과정을 경험할 수 있습니다.

---

## 🚀 주요 기능

### 1. 새 뽑기

* 새로운 새를 생성
* 기본 상태: **알**
* 초기 친밀도: 0

### 2. 새 목록 조회

* 내가 보유한 모든 새 확인
* 이름 / 성장 단계 / 친밀도 표시

### 3. 새 상세 보기

* 개별 새의 상세 정보 확인
* 행동(먹이주기, 쓰다듬기) 가능

### 4. 상호작용 (핵심 기능)

* 먹이주기: 친밀도 +1
* 쓰다듬기: 친밀도 +2

### 5. 성장 시스템

* 0 ~ 9 → 🥚 알
* 10 ~ 19 → 🐥 아기 새
* 20 이상 → 🐔 어른 새

### 6. 이미지 변화

* 성장 단계에 따라 이미지 변경

  * 알 → egg.jpg
  * 아기 새 → chick.jpg
  * 어른 새 → bird.jpg

### 7. 예외 처리

* 존재하지 않는 새 접근 시 목록으로 리다이렉트
* 친밀도 최대값 100 제한

---

## 🛠️ 기술 스택

* **Backend**: Java, Spring Boot
* **Database**: H2 (또는 MySQL)
* **ORM**: Spring Data JPA
* **Frontend**: Thymeleaf
* **Build Tool**: Gradle
* **Version Control**: Git / GitHub

---

## 📂 프로젝트 구조

```text
src
 ├─ controller
 │   └─ HomeController.java
 ├─ entity
 │   └─ Bird.java
 ├─ repository
 │   └─ BirdRepository.java
 └─ resources
     ├─ templates
     │   ├─ birds.html
     │   └─ bird-detail.html
     └─ static
         └─ images
             └─ birds
                 ├─ egg.jpg
                 ├─ chick.jpg
                 └─ bird.jpg
```

---

## ▶️ 실행 방법

```bash
git clone <your-repository-url>
cd bird
./gradlew bootRun
```

브라우저 접속:

```text
http://localhost:8080/birds
```

---

## 📸 화면 예시

(여기에 스크린샷 추가 예정)

---

## 📈 향후 개선 기능

* 🎨 알 랜덤 색상 / 패턴 시스템
* 🧬 성장 결과 다양화 (랜덤 진화)
* 🌐 외부 API 연동 (날씨 / AI 등)
* 🎮 게임 요소 추가 (경험치, 아이템 등)

---

## 💡 느낀 점

Spring Boot 기반 CRUD 흐름과
Thymeleaf를 활용한 화면 렌더링,
그리고 상태 변화(성장 시스템)를 구현하면서
기초적인 웹 서비스 구조를 이해할 수 있었습니다.

## 주요 기능

- 새 랜덤 뽑기 기능
- COMMON / RARE / EPIC / LEGENDARY 희귀도 시스템
- 희귀도별 알 이미지 및 성장 이미지 분기
- 친밀도 기반 성장 단계 변화
- 먹이주기 / 쓰다듬기 상호작용
- OpenAI API 기반 새와 대화 기능
- Thymeleaf 기반 카드형 UI