# 프로젝트명 : RNN과 NLP를 이용한 수면 보조 스마트 무드등

## <b>○ 프로젝트소개 및 제안배경
### 프로젝트 소개 
- Sleep Well 무드등이란 사용자의 수면을 분석하고 수면 문제를 파악 및 개선 할 수 있도록 하는 장치.
- 스마트폰이나 스마트 기기로 수집된 데이터를 분석하고 스마트 폰에서 시각화하여 수면 상태를 확인하고 조명과 수면 음악으로 수면의 질을 개선할 수 있도록 함.
- 일기의 분석된 부분을 통해 요약기능을 제공하고, 수면 리포트에서 수면 추천기능 사용가능.

### 제안 배경
- 필립스가 2021년 3월 19일 발표한 "코로나 19 이후 글로벌 수면 동향"에서 한국인의 62%가 수면 문제를 경험하고 있다는 결과 발표
- 적절한 수면시간 및 수면 습관은 신체/정신적 스트레스를 해소하고 극복하는 것에 큰 도움을 줄 수 있다는 것을 의미함.
<br>

## <b>○ 주요기능
- 스마트폰 자이로센서와 가속도 센서 값을 분석하여 스마트폰에 출력
- 무드등에서 실내 조도, 온습도를 측정하여 최적의 상태를 사용자에게 알려줌.
- 원하는 색과 밝기의 조명으로 수면 음악을 사용하여 수면의 질 개선
- NLP를 통해 무드등에서 음성 인식을 구현하고 이를 활용해 일정 확인이나 일기 작성에 사용함.
- 일기 요약 기능, 수면 추천 기능
<br><br>

## <b>○ 사용기술
| 분류      | 기술                               |
| --------- | ---------------------------------- |
| 개발환경  | AWS EC2 Ubuntu 20.04 LTS            |
| DB        | mysql 8.0.22, Mybatis              |
| Front-end | Android Native(java)               |
| Back-end  | Apache, Spring boot 2.x, Flask ,Docker|
| Logging & Monitoring  | ELK, Prometheus, Grafana  |
| api       | 기상청, 한국환경공단, Google Speech API|
| UI        | Figma                              |
<br>

## <b>○ 아키텍처
<img src = "https://user-images.githubusercontent.com/20091175/136898016-8cdc15b1-e2b1-44b5-a997-69d8f20ca798.png" width = "600" height ="350">
<br>
- Github에서 Gitlab 미러링을 한 이유는 프로젝트는 GitLab에 올려야 공모전에 참여 가능하기 때문
<br><br><br>

## <b>○ 무드등 이미지
<img src = "https://user-images.githubusercontent.com/20091175/136902307-1b617699-baee-4aae-b6ee-80754b7f64bc.png" width = "400" height= "250">
<img src = "https://user-images.githubusercontent.com/20091175/131856903-0819c410-a1fe-4ddc-9239-aaebaba84c75.png" width = "400" height="250">
<br><br>

## <b>○ 시연영상
<a href="https://youtu.be/OYgD6OucnrM">Youtube 시연영상</a>

## <b>○ 참여자
최인호, 장호민, 황석주 
