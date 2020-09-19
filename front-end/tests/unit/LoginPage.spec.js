import { mount, createLocalVue } from '@vue/test-utils'
import Vuelidate from 'vuelidate'
import VueRouter from 'vue-router'
import LoginPage from '@/views/LoginPage'
import authenticationService from '@/services/authentication'

/*
  로그인 폼을 랜더링해야한다.
  초깃값을 갖는 데이터 모델을 가져야한다.
  데이터 모델과 연결된 폼 입력을 가져야한다.
  submitForm 폼 제출 핸들러를 가져야한다.
  자격이 유효하면 성공해야 한다.
  자격이 유효하지 않으면 실패해야 한다.
  유효하지 않은 데이터 제출을 방지하기 위한 검증이 필요하다.
*/
const localVue = createLocalVue()
localVue.use(Vuelidate)
localVue.use(VueRouter)
const router = new VueRouter()

jest.mock('@/services/authentication') //

describe('LoginPage.vue', () => {

  let wrapper
  let fieldUsername
  let fieldPassword
  let buttonSubmit
  let authenticateSpy

  beforeEach(() => {
    wrapper = mount(LoginPage, {
      localVue,
      router
    })
    fieldUsername = wrapper.find('#username')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
    authenticateSpy = jest.spyOn(authenticationService, 'authenticate')
  })
  // 메 test(==it) 후 mock function 초기화
  afterEach(() => {
    authenticateSpy.mockReset()
    authenticateSpy.mockRestore()
  })

  afterAll(() => {
    jest.restoreAllMocks()
  })

  it('should render login form', () => {
    // .logo, .tagline, 초기값 두개, Submit버튼(='sign in'), .link-sign-up(->/register), .link-forgot-password
    expect(wrapper.find('.logo').attributes().src).toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Open source task management tool')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Sign in')
    expect(wrapper.find('.link-sign-up').attributes().href).toEqual('/register')
    expect(wrapper.find('.link-forgot-password')).toBeTruthy()
  })

  it('should contain data model with initial values', () => {
    // data가 function으로 return form:{username, password} 형식이라 이런듯  
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('should have form inputs bound with data model', () => {
    let username = 'sunny' // const?
    let password = 'VueJsRocks'

    wrapper.vm.form.username = username
    wrapper.vm.form.password = password
    expect(fieldUsername.element.value).toEqual(username)
    expect(fieldPassword.element.value).toEqual(password)
  })

  it('should have form submit event handler `submitForm`', () => {
    let stub = jest.fn() // const?
    wrapper.setMethods({ submitForm: stub })
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('should succeed when credentials are valid', async () => {
    // async, await으로 $nextTick을 처리한 이유는 validation등의 이유로 authenticate()가 호출되지 않았을 경우 promise반환이 없기때문에 실행될 $nextTick이 없고,
    // 비동기로 redirect 발생여부를 검사하지 않게 되기 때문이다. 그것은 치명적인 test문제를 야기할지도 모른다!!
    expect.assertions(2)
    const stub = jest.fn()
    wrapper.vm.$router.push = stub // push()를 의미없는 mock function으로 대체
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.password = 'JestRocks!'
    wrapper.vm.submitForm()
    expect(authenticateSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(stub).toHaveBeenCalledWith({ name: 'HomePage' })
  })

  it('should fail when credentials are invalid', async () => {
    expect.assertions(3)
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.password = 'MyPassword'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    expect(authenticateSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(wrapper.find('.failed').isVisible()).toBe(true)
  })

  it('should have validation to prevent invalid data submit', () => {
    // Empty form
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()
    // Only usernmae is valid
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()
    // Only email is valid ??
    wrapper.vm.form.username = 'sunny@taskagile.com'
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toBeCalled()
    // Only password is valid
    wrapper.vm.form.password = 'MyPassword!!'
    wrapper.vm.form.username = ''
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toBeCalled()
  })
})
