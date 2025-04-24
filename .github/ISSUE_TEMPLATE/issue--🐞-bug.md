---
name: "Issue: \U0001F41E Bug"
about: 버그 제보
title: "[BUG]"
labels: ''
assignees: ''

---

name: "🐞 Bug"
description: "버그 수정 요청"
labels: ["bug"]
body:

type: textarea
attributes:
label: 🐛 버그 설명
description: 발견한 버그에 대해 자세히 설명해주세요.
placeholder: 어떤 상황에서, 어떤 문제가 발생했는지 구체적으로 적어주세요.
validations:
required: true
type: textarea
attributes:
label: 📌 재현 방법
description: 버그를 재현할 수 있는 단계가 있다면 작성해주세요.
placeholder: |
1. 어떤 페이지로 이동
2. 어떤 버튼 클릭
3. 에러 발생!
type: textarea
attributes:
label: ✅ 예상되는 해결 작업
description: 버그를 해결하기 위해 해야 할 작업을 체크박스 형식으로 적어주세요.
placeholder: |
- [ ] 문제 원인 파악
- [ ] 관련 코드 수정
- [ ] 테스트 진행
type: textarea
attributes:
label: 🙋🏻 참고 자료
description: 관련 문서나 스크린샷이 있다면 첨부해주세요.
