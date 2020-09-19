import axios from 'axios'

export default {
  authenticate (detail) {
    return new Promise((resolve, reject) => {
      // data 말고 ({ data })로 표현해야하는듯
      axios.post('/authentications', detail).then(({ data }) => {
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  }
}
