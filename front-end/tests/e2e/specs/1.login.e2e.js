const data = require('../data/user')

module.exports = {
  // 'login page renders elements': function (browser) {
  //   const loginPage = browser.page.LoginPage()
  //   loginPage
  //     .navigate()
  //     .waitForElementVisible('@app', 500)
  //     .assert.visible('@usernameInput')
  //     .assert.visible('@passwordInput')
  //     .assert.visible('@submitButton')
  //     .assert.hidden('@formError')
  //   browser.end()
  // },
  // 'login with invalid credentials': function (browser) {
  //   const loginPage = browser.page.LoginPage()
  //   loginPage
  //     .navigate()
  //     .login('not-exist', 'incorrect')
  //   browser.pause(500)

  //   loginPage
  //     .assert.visible('@formError')
  //     .assert.containsText('@formError', 'Request Failed with status code 400') // error메세지가 왜 이렇게 생성되는지 모르겠다

  //   browser
  //     .assert.urlEquals(browser.launchUrl + 'login')
  // },
  // 'login with username': function (browser) {
  //   const loginPage = browser.page.LoginPage()
  //   const homePage = browser.page.HomePage()

  //   loginPage
  //     .navigate()
  //     .login(data.emailAddress, data.password)
  //   browser.pause(2000)

  //   homePage
  //     .navigate()
  //     .expect.element('@pageTitle').text.to.contain('HomePage')

  //   browser.end()
  // },
  // 'login with email address': function (browser) {
  //   const loginPage = browser.page.LoginPage()
  //   const homePage = browser.page.HomePage()

  //   loginPage
  //     .navigate()
  //     .login(data.emailAddress, data.password)
  //   browser.pause(2000)

  //   homePage
  //     .navigate()
  //     .expect.element('@pageTitle').text.to.contain('HomePage')

  //   browser.end()
  // }
}
