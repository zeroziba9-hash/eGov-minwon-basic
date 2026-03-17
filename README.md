# egov-minwon-basic

전자정부프레임워크(eGovFrame) 스타일을 반영한 **민원 처리 MVP** 프로젝트입니다.  
(현재 로컬 폴더명은 `egov-minwon-skeleton`이지만, GitHub 레포명은 `egov-minwon-basic` 권장)

---

## 1) 프로젝트 개요
- 목적: 간단한 민원 등록/조회/상태변경 흐름 구현
- 구조: Spring MVC + MyBatis + JSP + eGovFrame RTE 일부 반영
- 산출물: WAR (`target/egov-minwon-skeleton-0.0.1-SNAPSHOT.war`)

## 2) 주요 기능
- 민원 목록 조회
- 민원 등록
- 민원 상태 변경 (접수 / 처리중 / 완료)
- 로그인/로그아웃 (세션 기반)
- 전역 예외 처리 페이지
- 감사 로그(Audit) 기록

## 3) 기술 스택
- Java 8
- Spring MVC
- MyBatis
- eGovFrame RTE (`fdl.cmmn`, `psl.dataaccess`)
- JSP / JSTL
- H2 (기본), MySQL (프로파일 분리)
- Maven (WAR 패키징)

## 4) 패키지 구조
- `egov.minwon.web` : Controller
- `egov.minwon.service` : Service / VO
- `egov.minwon.service.impl` : ServiceImpl / Mapper
- `resources/mappers` : MyBatis XML
- `WEB-INF/jsp` : JSP View

## 5) 실행 방법
### 5-1. 빌드
```bash
mvn clean package
```

### 5-2. 기본 프로파일(H2)
- 기본값: `h2`
- 설정 위치: `WEB-INF/web.xml` (`spring.profiles.default`)

### 5-3. MySQL 프로파일 사용
1. `web.xml`에서 `spring.profiles.default`를 `mysql`로 변경
2. `src/main/resources/spring/context-datasource-mysql.xml`의 접속정보 수정

## 6) 로그인 정보 (테스트)
- ID: `admin`
- PW: `1234`

## 7) eGovFrame 반영 체크
- `EgovAbstractServiceImpl` 적용
- eGov `@Mapper` 적용
- `.do` URL 패턴 적용
- 상세 문서: `EGOVFRAME_CHECKLIST.md`

## 8) 로그/감사 정책
- 설정 파일: `src/main/resources/logback.xml`
- 감사 인터셉터: `egov.minwon.web.common.AuditInterceptor`
  - 요청 사용자/메서드/URI/IP
  - 응답 상태
  - 예외 발생 정보

## 9) 참고
- DB 스키마는 `schema.sql`로 초기화됩니다(H2 기준).
- 첫 화면은 `index.jsp`에서 `/minwon/list.do`로 리다이렉트됩니다.
