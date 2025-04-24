---
name: "Issue: \U0001F680 Deploy"
about: 배포 관련 작업
title: "[DEPLOY]"
labels: ''
assignees: ''

---

name: "🚀 Deploy"
description: "배포 작업"
labels: ["deploy"]
body:

type: textarea
attributes:
label: 📦 배포 내용
description: 이번 배포에 포함되는 내용을 작성해주세요.
placeholder: 주요 변경사항이나 기능을 요약해주세요.
validations:
required: true
type: textarea
attributes:
label: ✅ 배포 전 체크리스트
description: 배포 전 확인해야 할 항목을 체크박스로 작성해주세요.
placeholder: |
- [ ] 기능 테스트 완료
- [ ] 버전 정보 확인
- [ ] README 및 문서 업데이트
validations:
required: true
type: textarea
attributes:
label: 📝 기타 메모
description: 전달사항이나 메모할 게 있다면 작성해주세요.
