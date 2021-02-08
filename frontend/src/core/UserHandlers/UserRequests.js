import axios from 'axios'

//Get user IP
export const getIpAddress = async () => {
    return await axios
    .get('https://cors-anywhere.herokuapp.com/http://api.ipify.org/?format=text')
    .then(res => { 
        return res.data
    })
    .catch(err => {
        return err
    })
}

//Post function on register
export const register = newUser => {
    return axios
    .post(process.env.REACT_APP_API_URL + '/user/register', newUser, {
        headers: {'Content-Type': 'application/json'}
    })
    .then(res => {
        return res.data;
    })
}

//Post function on login
export const login = user => {
    return axios
    .post(process.env.REACT_APP_API_URL + '/authenticate', {
        nickname: user.nickname,
        password: user.password
    }, {
        headers: {'Content-Type': 'application/json'}
    })
    .then(res => {
        console.log(res)
        localStorage.setItem('usertoken',res.data.token)
        return res.data.token;
    })
}


//Post function on register
export const visitor = visitor => {
    return axios
    .post(process.env.REACT_APP_API_URL + '/user/visitor', visitor, {
        headers: {'Content-Type': 'application/json'}
    })
    .then(res => {
        console.log(res)
    })
}