---
name: "Issue: \U0001F527 Refactor"
about: 리팩토링
title: "[REFACTOR]"
labels: ''
assignees: ''

---

name: "🔧 Refactor"
description: "코드 리팩토링"
labels: ["refactor"]
body:

type: textarea
attributes:
label: 🧹 리팩토링 설명
description: 리팩토링이 필요한 이유와 방향을 설명해주세요.
placeholder: 불필요한 중복 제거, 코드 가독성 향상 등
validations:
required: true
type: textarea
attributes:
label: ✅ 작업할 내용
description: 리팩토링해야 할 부분을 체크리스트 형식으로 작성해주세요.
placeholder: |
- [ ] 함수 이름 변경
- [ ] 중복 로직 분리
- [ ] 파일 구조 정리
validations:
required: true
type: textarea
attributes:
label: 🙋🏻 참고 자료
description: 참고한 문서나 베스트 프랙티스가 있다면 작성해주세요.
