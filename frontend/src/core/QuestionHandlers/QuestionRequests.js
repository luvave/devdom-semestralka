import axios from 'axios'

//Add new question
export const addNewQuestion = newQuestion => {
    return axios
    .post(process.env.REACT_APP_API_URL + '/question/newquestion', newQuestion, {
        headers: {'Authorization': `${localStorage.usertoken}`}
    })
    .then(res => {
        return res.data
    })
    .catch(err => {
        return err
    })
}

//Get all question categories
export const getAllQuestionCategories = async () => {
    return await axios
    .get(process.env.REACT_APP_API_URL + '/question/allquestioncategories', {
        headers: {'Authorization': `${localStorage.usertoken}`}
    })
    .then(res => { 
        return res.data
    })
    .catch(err => {
        return err
    })
}

//Get random question
export const getRandQuestion = async () => {
    return await axios
    .get(process.env.REACT_APP_API_URL + '/question/randomquestion'
    /*
    , {
        headers: {'Authorization': `${localStorage.usertoken}`}
    }
    */
    )
    .then(res => { 
        return res.data
    })
    .catch(err => {
        return err
    })
}