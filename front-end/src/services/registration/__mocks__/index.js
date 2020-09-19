// 이 파일의 존재 이유는 Vue파일의 테스트 시 submit()이 services에 의존하기 때문에 목으로 만들어 준 것이다.
export default {
  register (detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'sunny@taskagile.com'
        ? resolve({ result: 'success' })
        : reject(new Error('User already exists'))
    })
  }
}
