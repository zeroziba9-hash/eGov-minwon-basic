# 📨 egov-minwon-basic

전자정부프레임워크(eGovFrame) 스타일을 반영한 **민원 처리 MVP** 프로젝트입니다.  
간단한 CRUD를 넘어, **로그인/권한(세션), 전역 예외 처리, 감사 로그, DB 프로파일 분리**까지 포함해 실무형 구조로 구성했습니다.

---

## ✨ 프로젝트 한 줄 소개
> 민원 등록 → 조회 → 상태변경 흐름을 eGov 관례에 가깝게 구현한 Spring MVC 기반 웹 애플리케이션

---

## ✅ 주요 기능
- 민원 목록 조회
- 민원 등록
- 민원 상태 변경 (접수 / 처리중 / 완료)
- 로그인 / 로그아웃 (세션 기반)
- 인증 인터셉터 기반 접근 제어 (`/minwon/**`)
- 전역 예외 처리 (`@ControllerAdvice`)
- 감사 로그(Audit) 기록 (요청/응답/예외)

---

## 🛠️ 개발 도구
- **Eclipse IDE** 기반으로 개발
- Maven 프로젝트(`war`) 형태로 구성하여 Eclipse + Tomcat 워크플로우에 맞춰 작성

---

## 🧰 기술 스택 + 선택 이유
- **Java 8**
  - 공공/레거시 환경 호환성이 높고 eGov 프로젝트에서 많이 사용
- **Spring MVC**
  - Controller-Service 구조를 명확하게 분리하기 좋고 eGov 스타일과 궁합이 좋음
- **MyBatis**
  - SQL을 직접 제어하기 쉬워 CRUD/조회 로직을 명확히 관리 가능
- **eGovFrame RTE (`fdl.cmmn`, `psl.dataaccess`)**
  - `EgovAbstractServiceImpl`, eGov Mapper 등 표준 관례 반영 가능
- **JSP / JSTL**
  - 전통적인 eGov 웹 화면 구성 방식과 일치
- **H2 + MySQL Profile 분리**
  - H2로 빠른 로컬 개발, MySQL로 운영 전환 용이
- **Maven (WAR Packaging)**
  - 톰캣 등 WAS 배포형 프로젝트에 적합한 표준 빌드 도구
- **Logback**
  - 요청/응답/예외 로그 정책을 일관되게 관리

---

## 🗂️ 패키지 구조
- `egov.minwon.web` : Controller
- `egov.minwon.web.auth` : 로그인/인증 인터셉터
- `egov.minwon.web.common` : 공통 예외/감사 처리
- `egov.minwon.service` : Service / VO
- `egov.minwon.service.impl` : ServiceImpl / Mapper
- `src/main/resources/mappers` : MyBatis XML
- `src/main/webapp/WEB-INF/jsp` : JSP View

---

## ⚙️ 작동 방식 (요청 흐름)
1. 사용자가 `/minwon/list.do` 접근
2. `LoginInterceptor`가 세션의 `LOGIN_USER` 체크
3. 인증되면 `MinwonController` 진입
4. `MinwonServiceImpl` → `MinwonMapper` → `MinwonMapper.xml` SQL 실행
5. 조회 결과를 JSP(`minwon/list.jsp`)에 바인딩
6. 전 과정에서 `AuditInterceptor`가 요청/응답 로그 기록
7. 예외 발생 시 `GlobalExceptionHandler`가 `common/error.jsp`로 처리

---

## 🧩 주요 코드

### 1) eGov 스타일 Service 구현
```java
@Service("minwonService")
public class MinwonServiceImpl extends EgovAbstractServiceImpl implements MinwonService {
    private final MinwonMapper minwonMapper;

    public MinwonServiceImpl(MinwonMapper minwonMapper) {
        this.minwonMapper = minwonMapper;
    }
}
```

### 2) eGov Mapper 적용
```java
@Mapper("minwonMapper")
public interface MinwonMapper {
    List<MinwonVO> selectMinwonList();
}
```

### 3) 인증 인터셉터 등록
```xml
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/minwon/**"/>
        <ref bean="loginInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```

### 4) 전역 예외 처리
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "common/error";
    }
}
```

---

## 🚀 실행 방법
### 1) 빌드
```bash
mvn clean package
```

### 2) 기본 프로파일(H2)
- 기본값: `h2`
- 설정 위치: `src/main/webapp/WEB-INF/web.xml`
- 키: `spring.profiles.default`

### 3) MySQL 프로파일 사용
1. `spring.profiles.default`를 `mysql`로 변경
2. `src/main/resources/spring/context-datasource-mysql.xml` 접속정보 수정

---

## 🔐 테스트 계정
- ID: `admin`
- PW: `1234`

---

## 🗃️ SQL 테이블 설정 방법
### 1) H2 (기본)
- `h2` 프로파일에서는 애플리케이션 시작 시 아래 파일로 자동 생성됩니다.
- 파일: `src/main/resources/schema.sql`

```sql
CREATE TABLE IF NOT EXISTS minwon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content CLOB,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

### 2) MySQL (수동 생성)
`mysql` 프로파일로 실행할 경우, DB/테이블을 먼저 만들어 주세요.

```sql
CREATE DATABASE IF NOT EXISTS minwon
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

USE minwon;

CREATE TABLE IF NOT EXISTS minwon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

> MySQL 접속 정보는 `src/main/resources/spring/context-datasource-mysql.xml`에서 수정합니다.

---

## 📌 참고
- DB 스키마: `src/main/resources/schema.sql` (H2 시작 시 초기화)
- 감사 로그 설정: `src/main/resources/logback.xml`
- 상세 체크리스트: `EGOVFRAME_CHECKLIST.md`
