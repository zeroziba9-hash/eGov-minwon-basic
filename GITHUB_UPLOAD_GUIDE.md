# GitHub 업로드 가이드

## 권장 레포명
- `egov-minwon-basic`

## 1) 현재 프로젝트 폴더로 이동
```bash
cd C:\Users\ASUS\.openclaw\workspace\egov-minwon-skeleton
```

## 2) 원격 레포 연결
```bash
git remote -v
```
- 원격이 없으면:
```bash
git remote add origin https://github.com/<YOUR_ID>/egov-minwon-basic.git
```
- 기존 원격 URL을 바꾸려면:
```bash
git remote set-url origin https://github.com/<YOUR_ID>/egov-minwon-basic.git
```

## 3) 푸시
```bash
git branch -M main
git push -u origin main
```

## 4) 업로드 후 체크
- README 렌더링 확인
- 기본 브랜치(main) 확인
- 커밋 3개 이상 반영 확인
  - MVP 구현
  - eGov 정합성 리팩토링
  - 로그인/예외/DB프로파일/감사로그 반영
