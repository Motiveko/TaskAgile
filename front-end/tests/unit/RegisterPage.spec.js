import {
  mount,
  createLocalVue
} from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage'
import VueRouter from 'vue-router'

// vm.$router에 접근할 수 있도록 테스트에 Vue Router 추가
const localVue = createLocalVue()
localVue.use(VueRouter)
const router = new VueRouter()

// registrationService의 mock
jest.mock('@/services/registration')

describe('RegisterPage.vue', () => {
  // it('should render correct contents', () => {
  //     const Constructor = Vue.extend(RegisterPage)
  //     const vm = new Constructor().$mount()
  //     expect(vm.$el.querySelector('.logo').getAttribute('src')).toEqual('/static/images/logo.png')
  //     expect(vm.$el.querySelector('.tagline').textContent).toEqual('Open source task management tool')
  //     expect(vm.$el.querySelector('#username').value).toEqual('')
  //     expect(vm.$el.querySelector('#eamilAddress').value).toEqual('')
  //     expect(vm.$el.querySelector('#password').value).toEqual('')
  //     expect(vm.$el.querySelector('form button[type="submit"]').textContent).toEqual('Create account')
  // })
  let wrapper
  let fieldUsername
  let fieldEmailAddress
  let fieldPassword
  let buttonSubmit

  beforeEach(() => {
    wrapper = mount(RegisterPage, {
      localVue,
      router
    })
    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#eamilAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
  })

  afterAll(() => {
    jest.restoreAllMocks()
  })

  // template이 잘 rendering 됬는지와 form의 초기값 검증 등
  it('should render registration form', () => {
    expect(wrapper.find('.logo').attributes().src).toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Open source task management tool')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmailAddress.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create account')
  })
  // '데이터 모델'의 초기값 검증 테스트( 이거는 Wrapper객체 내의 Vue instance의 데이터를 검증한다.)
  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })
  // 폼의 입력과 데이터 모델의 바인딩 검증 테스트
  it('should have form inputs bound with data model', () => {
    const username = 'sunny'
    const emailAddress = 'sunny@local'
    const password = 'VueJsRocks!'
    // 데이터 모델의 세 필드에 새로운 값을 할당 , .vm은 vue instance로 해당 instance의 모든 properties와 method로 access가 가능하다. data도 포함
    wrapper.vm.form.username = username
    wrapper.vm.form.emailAddress = emailAddress
    wrapper.vm.form.password = password
    // 데이터 모델과 잘 바인딩 되어 있다면 폼의 value값도 데이터 모델의 값과 같을것이다.
    expect(fieldUsername.element.value).toEqual(username)
    expect(fieldEmailAddress.element.value).toEqual(emailAddress)
    expect(fieldPassword.element.value).toEqual(password)
  })
  // 핸들러의 존재 여부 테스트, 기능에 대한건 모르고 그냥 submit 누르면 이벤트 동작하는지 판단한다.
  it('should have form submit event handler `submitForm`', () => {
    const stub = jest.fn()
    wrapper.setMethods({
      submitForm: stub
    })
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('should register when it is a new user', () => {
    const stub = jest.fn()
    wrapper.vm.$router.push = stub
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.emailAddress = 'sunny@local'
    wrapper.vm.form.password = 'Jest!'
    wrapper.vm.submitForm()
    wrapper.vm.$nextTick(() => {
      expect(stub).toHaveBeenCalledWith({
        name: 'LoginPage'
      })
    })
  })

  it('should fail it is not a new user', () => {
    wrapper.vm.form.emailAddress = 'ted@local'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    wrapper.vm.$nextTick(null, () => {
      expect(wrapper.find('.failed').isVisible()).toBe(true)
    })
  })
})
