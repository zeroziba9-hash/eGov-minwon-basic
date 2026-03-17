# egov-minwon-skeleton

전자정부프레임워크 스타일의 민원 관리 기본 골격 프로젝트입니다.

## 포함된 구성
- Controller: `egov.minwon.web.MinwonController`
- Service: `egov.minwon.service.MinwonService`, `MinwonServiceImpl`
- VO: `egov.minwon.service.MinwonVO`
- Mapper: `egov.minwon.service.impl.MinwonMapper`, `resources/mappers/MinwonMapper.xml`
- Spring 설정: `WEB-INF/config/springmvc/action-servlet.xml`, `resources/spring/context-common.xml`
- View: `WEB-INF/jsp/minwon/list.jsp`

## 기능 (MVP)
- 민원 목록 조회
- 민원 등록
- 민원 상태 변경 (접수/처리중/완료)

## 실행 전 준비
1. JDK 8+
2. Maven 3.8+
3. Tomcat 9+ (WAR 배포)

## 빌드
```bash
mvn clean package
```

## 참고
- DB는 H2 in-memory를 사용하며, 앱 시작 시 `schema.sql`로 테이블을 자동 생성합니다.
- 첫 화면은 `index.jsp`에서 `/minwon/list.do`로 리다이렉트합니다.
