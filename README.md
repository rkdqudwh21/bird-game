# 🐦 Bird Raising Project (새 키우기 프로젝트)

Spring Boot 기반으로 상태 변화와 랜덤 요소를 포함한  
수집형 캐릭터 성장 웹서비스를 구현한 개인 프로젝트입니다.

사용자는 새를 뽑고 상호작용을 통해 친밀도를 높이며,  
희귀도 및 성장 단계에 따라 변화하는 캐릭터 시스템을 경험할 수 있습니다.

또한 OpenAI API를 연동하여  
새와 자연어 대화가 가능한 AI 기능을 구현했습니다.

---

github: https://github.com/rkdqudwh21/bird-game

---

# ⭐ 핵심 기능 요약

- COMMON / RARE / EPIC / LEGENDARY 희귀도 시스템
- 랜덤 확률 기반 새 뽑기 기능
- 성장 단계 기반 상태 변화 시스템
- 희귀도 + 성장 단계 기반 이미지 자동 변경
- OpenAI API 기반 AI 대화 기능
- Thymeleaf 기반 카드형 UI 구현

---

# 🛠️ 사용 기술

```text
Backend: Spring Boot, Spring Data JPA
Frontend: Thymeleaf
Database: H2 (개발용)
Build Tool: Gradle
Language: Java
📂 프로젝트 구조
Controller → 요청 처리
Entity → DB 테이블 구조
Repository → DB 접근
Template → 화면 (Thymeleaf)
🚀 구현 기능
1️⃣ 홈 및 새 목록
/ : 메인 화면
/birds : 새 목록 조회
여러 마리 새 생성 가능
2️⃣ 새 상세 페이지
/birds/{id}
새 이름, 성장 단계, 친밀도 표시
희귀도 및 이미지 확인 가능
3️⃣ 랜덤 뽑기 시스템
Random 클래스를 활용한 확률 기반 희귀도 분기 구현
COMMON      60%
RARE         25%
EPIC         10%
LEGENDARY     5%
희귀도에 따라 다른 알 이미지 적용
4️⃣ 상호작용 기능
🍞 먹이주기
친밀도 +1 증가
상태에 따라 성장 단계 변경
🤲 쓰다듬기
친밀도 +2 증가
상태 기반 반응 메시지 출력
5️⃣ 성장 단계 로직
0 ~ 9   → 알
10 ~ 19 → 아기 새
20 이상 → 어른 새
친밀도에 따라 자동으로 성장 단계 변경
단계별 이름 자동 변경

예시:

COMMON 아기 새
RARE 어른 새
LEGENDARY 어른 새
6️⃣ 🖼️ 희귀도 및 이미지 시스템
COMMON / RARE / EPIC / LEGENDARY 희귀도 시스템 구현
성장 단계와 희귀도에 따라 이미지 자동 변경
imageUrl 값을 DB에 저장 후 Thymeleaf에서 동적 렌더링

예시:

COMMON 알 → egg.jpg
RARE 알 → blue-egg.jpg
EPIC 어른 새 → epic-bird.png
LEGENDARY 어른 새 → legendary-bird.png
## 7️⃣ 상태 변화 시스템

- 친밀도에 따라 성장 단계 자동 변경
- 성장 단계에 따라 이름 및 이미지 자동 변경
- 먹이주기 / 쓰다듬기 시 상태 변화 반영

👉 동일 행동이라도 상태에 따라 다른 반응 구현

8️⃣ DB 연동
Bird 엔티티 생성
JPA Repository 사용
데이터 DB 저장 및 조회 구현
서버 재시작 후에도 데이터 유지 가능
9️⃣ AI 대화 기능
OpenAI API를 활용하여 새와 자연어 대화 기능 구현
사용자 입력에 따라 동적으로 응답 생성
새의 상태(이름, 성장 단계, 친밀도)를 기반으로 캐릭터 설정

예시:

"안녕"
→ "나는 햇빛과 따뜻한 곳이 좋아! 😊"
💡 핵심 구현 포인트
JPA를 활용한 CRUD 구조 이해
Controller → View → DB 흐름 구현
상태 기반 로직 처리 (if 조건 기반 게임 로직)
Random 클래스를 활용한 확률 기반 희귀도 분기 구현
이미지 경로를 DB에 저장하여 상태별 이미지 동적 변경 구현
Thymeleaf th:src를 활용한 이미지 렌더링 처리
정적 리소스(static) 경로 구조 이해 및 적용
OpenAI API 기반 AI 대화 기능 구현
🔥 트러블슈팅
이미지가 출력되지 않던 문제
문제
성장 후 이미지가 출력되지 않는 문제 발생
원인
실제 파일은 .png인데 코드에서 .jpg로 접근
상대경로 사용으로 인해 잘못된 URL 접근 발생
해결
getImageUrlByStageAndRarity() 메서드 수정
이미지 경로를 /images/... 형태로 통일
Thymeleaf th:src="@{${bird.imageUrl}}" 적용
희귀도 알 이미지가 적용되지 않던 문제
문제
RARE 알인데 기본 알 이미지가 출력됨
원인
"알" 상태에 대한 rarity 분기 누락
해결
stage == "알" 조건 추가
rarity 기반 알 이미지 분기 구현
▶️ 실행 방법
git clone https://github.com/rkdqudwh21/bird-game.git
cd bird-game
./gradlew bootRun

브라우저 접속:

http://localhost:8080/birds
📸 화면 예시
새 목록 화면
새 상세 페이지
희귀도별 이미지 시스템
AI 대화 기능

<img width="1920" height="1080" alt="d" src="https://github.com/user-attachments/assets/3a9806c1-1865-4acf-80cb-4e69c8311fbe" />
<img width="1920" height="1080" alt="c" src="https://github.com/user-attachments/assets/32723563-0063-4122-bd50-59f06675cd84" />
<img width="1920" height="1080" alt="b" src="https://github.com/user-attachments/assets/8e893323-d108-4363-96f6-c8021476e6e2" />
<img width="1920" height="1080" alt="a" src="https://github.com/user-attachments/assets/95eaae09-84f9-4a94-9b8d-01282555fedd" />





📌 향후 개선 계획
로그인 기능 추가
캐릭터 성격 시스템
날씨 API 연동
경험치 및 아이템 시스템
성장 결과 다양화 (랜덤 진화)
알 랜덤 색상 / 패턴 시스템
🔥 느낀 점

단순 CRUD 구현을 넘어,
상태 변화와 랜덤 요소가 포함된 로직을 직접 설계하고 구현할 수 있었습니다.

또한 Thymeleaf 기반 동적 이미지 처리,
JPA를 활용한 데이터 저장 구조,
OpenAI API 연동을 경험하며
웹 서비스의 전체 흐름에 대한 이해를 높일 수 있었습니다.

💬 한 줄 요약

Spring Boot 기반으로 상태 변화와 랜덤 요소를 포함한 수집형 캐릭터 성장 웹서비스를 구현하고,
희귀도 시스템 / 이미지 동적 변경 / OpenAI API 기반 AI 대화 기능을 구현했습니다.
