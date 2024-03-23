# 2024_BEOTKKOTTHON_TEAM_29_BE
🍅 당신만의 집중력 도우미 [tomaDo] 백엔드 레포지토리

![image](https://github.com/goormthon-Univ/2024_BEOTKKOTTHON_TEAM_29_BE/assets/83761128/9666d28f-6c21-467a-b7b1-0c71a289b910)

| 항목 | 내용 |
| --- | --- |
| 프로젝트 소개 | 당신만의 집중력 도우미 [tomaDo] |
| 개발 인원 | 총 6명 (PM 1명 + UI/UX 1명 + FE 2명 + BE 2명) |
| 개발 기간 | 총 11일 (2024. 03. 13 ~ 2024 03. 24) |

| 기술 | 사용 |
| --- | --- |
| Language | <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> |
| Framework |  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> |
| Database | <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&s&logoColor=white"> |
| Deploy | <img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&s&logoColor=white"> <img src="https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&s&logoColor=white"> <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&s&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&s&logoColor=white">|
| API | <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&s&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&s&logoColor=white"> <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&s&logoColor=white"> |
| Cooperative tool | <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> |
| IDE | <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> |

### ERD

![image](https://github.com/goormthon-Univ/2024_BEOTKKOTTHON_TEAM_29_BE/assets/83761128/d920ee46-ec29-4eed-b44f-0b74f45369db)

---

## 주요 기능

### 1. 회원 가입 로그인

// 회원 가입, 로그인 사진

- 회원 가입 : 아이디, 비밀번호, 닉네임으로 가입
  - 아이디, 비밀번호 유효성 검사
  - 중복 아이디 검사
- 로그인 : 아이디, 비밀번호 입력

### 2. 타이머 기능

// 타이머 사진

- 모드 설정 가능
  - 이지 : 시간 설정 가능, 획득 토마량 개수 : 1 뽀모도로 당 1개
  - 하드 : 시간 설정 불가능, 획득 토마량 개수 : 1 하드 뽀모도로(3 뽀모도로) 당 6개
- 중지 시 '토마토를 완성하고 싶다면 1분내로 시작해주세요'를 띄우고 대기
- 카테고리 선택 가능
- 할 일 작성 가능
- 긴급 메모 작성 가능
- 토마 획득

### 3. 사용자 편의 기능

// 메뉴 페이지 사진

- 회원 정보 수정
  - 사진(프로필) 변경 - 구입한 토마두 중 선택
  - 닉네임, 아이디, 비밀번호 변경
- 모아보기 : 월간 달력 페이지
- 토마클럽 : 클럽 페이지
- 토마도감 : 보유 토마두 도감 페이지
  - 획득 토마두 확인 가능
  - 최근 얻은 토마두 3개 확인 가능
- 토마상점 : 토마두 상점 페이지
- 긴급메모 : 작성한 긴급 메모 확인 페이지

### 4. 월간 달력 보기 기능

// 달력 사진

- 카테고리 당 획득한 토마량 일별 확인 가능

### 5. 클럽 기능

// 클럽 사진

- 다른 사용자와 함께 클럽에 가입해 목표 토마량을 채울 수 있음
- 클럽 생성 : 이름, 기간, 정원, 목표 토마량, 메모를 작성 가능
- 클럽 수정 : 이름, 인원, 기간, 목표 토마량, 메모 수정 가능
- 클럽을 생성 시, 클럽과 연결되는 카테고리 생성
- 클럽 이름 변경 시, 클럽에 연결된 카테고리 이름도 변경
- 현재 모은 토마량 확인 가능 - 멤버마다 몇 개 모았는지 확인 가능
- 클럽에 가입한 다른 사용자의 프로필 사진 확인 가능

### 6. 상점 기능

// 상점 기능

- 타이머에서 뽀모도로를 진행해 얻은 토마로 토마두 구입 가능
- 구입한 토마두는 색 변경
