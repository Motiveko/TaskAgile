import moxios from 'moxios'
import authenticationService from '@/services/authentication'

describe('services/authentication', () => {
  beforeEach(() => {
    moxios.install()
  })

  afterEach(() => {
    moxios.uninstall()
  })

  // /authentiacions API 호출 여부만 Test
  it('should call /authentiacions API', () => {
    expect.assertions(1) // assertion은 expect.어쩌구 같은 assert 관련애들이 몇번 나오는지 테스트한다.
    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request.url).toEqual('/authentications')
      // JUnit의 given같은것. moxios에 대해 결과값을 설정해준다.
      request.respondWith({
        status: 200,
        response: {
          result: 'success'
        }
      })
    })
    return authenticationService.authenticate()
  })

  it('should pass the response to caller when request succeded', () => {
    expect.assertions(2)
    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request.url).toEqual('/authentications')
      request.respondWith({
        status: 200,
        response: {
          result: 'success'
        }
      })
    })
    return authenticationService.authenticate().then(data => {
      expect(data.result).toEqual('success')
    })
  })
  it('should propagate the error to caller when request failed', () => {
    expect.assertions(2)
    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request).toBeTruthy() // 존재하는지 여부 테스트
      request.reject({
        status: 400,
        response: {
          message: 'Bad request'
        }
      })
    })
    return authenticationService.authenticate().catch(error => {
      expect(error.response.message).toEqual('Bad request')
    })
  })
})
