import axios from 'axios'

//Get all posts
export const getAllPosts = async () => {
    return await axios
    .get(process.env.REACT_APP_API_URL + '/post/allposts')
    .then(res => { 
        return res.data
    })
    .catch(err => {
        return err
    })
}

//Submit answer as logged in user
export const submitNewPost = newPost => {
    return axios
    .post(process.env.REACT_APP_API_URL + '/post/newpost', newPost, {
        headers: {'Authorization': `${localStorage.usertoken}`}
    })
    .then(res => {
        return res.data
    })
    .catch(err => {
        return err
    })
}

//Get all post categories
export const getAllPostCategories = async () => {
    return await axios
    .get(process.env.REACT_APP_API_URL + '/post/allpostcategories', {
        headers: {'Authorization': `${localStorage.usertoken}`}
    })
    .then(res => { 
        return res.data
    })
    .catch(err => {
        return err
    })
}

//Submit new category as logged in user
export const newPostCategory = postCategory => {
    return axios
    .post(process.env.REACT_APP_API_URL + '/post/newpostcategory', postCategory, {
        headers: {'Authorization': `${localStorage.usertoken}`}
    })
    .then(res => {
        return res.data
    })
    .catch(err => {
        return err
    })
}
