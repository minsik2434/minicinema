# minicinema

## 📢 프로젝트 소개

#### 영화 좌석 예매 사이트

## 개요
Spring MVC를 사용해 개발한 영화 예약 사이트 입니다 Spring boot와 thymeleaf를 이용하여 MVC 패턴으로 웹 사이트를 구축하였고 MySQL을 사용해 데이터베이스 모델링 및 구축 하였습니다
- 영화관을 이용하는 고객들이 편리하게 영화관을 이용할 수 있게 영화관 데이터 및 정보를 관리 유지한다
- 영화 데이터를 체계적으로 관리
- 고객의 좌석 예약 검색 및 편의성 제공
- 영화 평점을 저장관리하여 고객들에게 영화에 대한 정보를 제공한다

## 사용 기술
<div>
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=black">
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=black">
<img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge">
<img src="https://img.shields.io/badge/jpa-b0a271?style=for-the-badge">
<img src="https://img.shields.io/badge/mysql-1777c0?style=for-the-badge&logo=mysql&logoColor=black">
<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">
</div>

## 시스템 기능
  1. 사용자의 회원가입
  2. 영화 예매기능
  3. 좌석 선택기능
  4. 예약 조회 기능
  5. 검색 기능
  6. 회원의 등급을 이용해서 예약 할인기능
 
## ERD

<img width="1000" src="https://github.com/user-attachments/assets/33a7bbf8-3fef-4a0a-8f33-02159e7fae5c"/>

## 화면설계

<details>
  <summary>회원가입</summary>

  <br>
  <img width="1440" alt="회원가입 페이지" src="https://github.com/user-attachments/assets/3425b1d4-f72b-4c97-b73c-53fc582fbc93"/>


  <hr>

  * 회원가입 페이지
  * 사용자 정보를 입력받아 데이터베이스에 저장


</details>

<details>
  <summary>로그인</summary>

  <br>
  <img width="1440" alt="회원가입 페이지" src="https://github.com/user-attachments/assets/e89e865a-5f44-44b3-ab1c-0625da4394b3"/>


  <hr>

  * 로그인 페이지
  * 데이터베이스의 회원정보 조회, 비교 후 세션방식으로 로그인


</details>

<details>
  <summary>메인화면</summary>

  <br>
  <img width="1440" alt="메인화면" src="https://github.com/user-attachments/assets/21ec9062-60e2-40c1-b853-938627dc2bc2">

  <hr>

  * 메인페이지
  * 최신 영화, 인기 영화 리스트


</details>

<details>
  <summary>영화 정보</summary>

  <br>
  - 영화 상세 페이지
  <img width="1440" alt="영화 상세페이지" src="https://github.com/user-attachments/assets/d39b85a3-4b3d-4043-8ca2-acd22d6667fe">
  - 장르별 영화 페이지
  <img width="1440" alt="장르별 영화페이지" src="https://github.com/user-attachments/assets/a7fd40c6-6ee3-4536-9b18-a682844bfbf0">

  <hr>

  * 영화 정보 페이지
  * 장르별 영화 조회
  * 영화 평점정보, 줄거리, 장르, 개봉일 정보 표기


</details>

<details>
  <summary>혜택 정보</summary>

  <br>
  <img width="1440" alt="혜택 정보 페이지" src="https://github.com/user-attachments/assets/d3fab2ff-44d2-4215-b160-8a389c0dd96b">

  <hr>

  * 멤버쉽 혜택 페이지
  * 등급별 혜택 정보 표기

</details>
<details>
  <summary>마이페이지</summary>

  <br>
  - 결제 내역 페이지
  <img width="1440" alt="결제내역" src="https://github.com/user-attachments/assets/c331773b-9e0e-45d5-928a-9aaaca02b0f4">
  - 회원 정보 조회,변경 페이지
  <img width="1440" alt="프로필" src="https://github.com/user-attachments/assets/49c303e3-b0e6-4d05-ba47-c28d3411a27f">
  - 회원 멤버쉽 정보
  <img width="1440" alt="멤버쉽" src="https://github.com/user-attachments/assets/f2497913-7527-4296-9468-2ac502315f79">


  <hr>

  * 회원이 예매한 영화 정보
  * 회원 정보 표기와 회원 정보 수정
  * 회원 멤버쉽 정보 표기

</details>

<details>
  <summary>예매 페이지</summary>

  <br>
  - 영화 선택 및 시간, 상영관 선택 페이지
  <img width="1438" alt="영화선택및 시간 선택 페이지" src="https://github.com/user-attachments/assets/5c02bfa7-cd75-4ac5-8c56-e50daac1eb7f">
  - 좌석 선택 페이지
  <img width="1440" alt="좌석 선택 페이지" src="https://github.com/user-attachments/assets/8bb872f3-c2e7-403e-a0e3-4826becf2f12">
  - 결제 페이지
  <img width="1439" alt="결제페이지" src="https://github.com/user-attachments/assets/127e3c26-24f4-4d73-b8be-ea00bc31de0f">


  <hr>

  * 예매 가능 영화와 영화 상영시간, 상영관 선택 
  * 좌석 예매 이미 예매되어있는 좌석은 선택할 수 없음
  * 영화 예약 결제, 회원 멤버쉽에 따라 할인율 적용

</details>
<details>
  <summary>검색 페이지</summary>

  <br>
  <img width="1440" alt="스크린샷 2024-07-24 오후 1 31 42" src="https://github.com/user-attachments/assets/82464a60-bf8e-4fdb-84b6-b4fa740cd558">


  <hr>

  * 영화 검색페이지
  * 제목으로 영화 검색

</details>
