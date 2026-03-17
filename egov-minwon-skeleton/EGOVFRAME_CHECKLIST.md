# eGovFrame 정합성 체크리스트 (민원 프로젝트)

## 목표
현재 Spring MVC + MyBatis MVP를 eGovFrame 스타일/관례에 더 가깝게 맞춘다.

## 체크리스트
- [x] `.do` URL 패턴 사용 (`web.xml`)
- [x] Controller / Service / Mapper 계층 분리
- [x] eGovFrame RTE 의존성 추가 (`fdl.cmmn`, `psl.dataaccess`)
- [x] ServiceImpl이 `EgovAbstractServiceImpl` 상속
- [x] Mapper 인터페이스에 eGov `@Mapper` 적용
- [x] WAR 빌드 성공 검증

## 다음 단계(선택)
- [x] 공통컴포넌트(기본 로그인/세션 인터셉터) 적용
- [x] 전역 예외 처리(`@ControllerAdvice`) 보강
- [x] 운영 DB(MySQL) 기준 프로파일 분리(H2/mysql)
- [ ] 표준 로깅/감사 로그 정책 반영

## 메모
- 현재 프로젝트는 “완전한 eGovFrame 표준 템플릿”이라기보다,
  **eGovFrame 스타일을 반영한 경량 민원 처리 MVP**에 가깝다.
