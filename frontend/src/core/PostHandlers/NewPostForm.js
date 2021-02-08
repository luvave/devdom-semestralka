import React, { Component } from 'react'
import { submitNewPost, getAllPostCategories } from './PostRequests'

import styles from '../../styles/Main.module.scss';

//Form for adding new Post
class NewPostForm extends Component {
    //Current state
    constructor() {
        super()
        this.state = {
            title: '',
            content: '',
            categories: [],
            selectedCategories: [],
            error: ''
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
        this.onChangeCheckbox = this.onChangeCheckbox.bind(this)
    }

    //On mount get all post categories from backend and save to array
    componentDidMount(){
        getAllPostCategories().then(res => {
            this.setState({ categories: res })
          }).catch(err =>{
            console.log(err);
          });
    }

    //If something is changed save to state
    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    //If checkbox is changed save to state
    onChangeCheckbox(e) {
        if(e.target.checked===true){
            this.setState({ 
                selectedCategories: [...this.state.selectedCategories, {
                    id : e.target.value
                }]
            })
        }
        else{
            this.setState({ 
                selectedCategories: this.state.selectedCategories.filter((x)=>(x.id !== e.target.value))
            })
        }
    }

    //On submit post data to server
    onSubmit(e) {
        e.preventDefault()

        const newPost = {
            title: this.state.title,
            content: this.state.content,
            postCategories: this.state.selectedCategories
        }
        //Submit new post to backend
        submitNewPost(newPost).then(res => {
            if (res) {
                this.setState({ error: res.errors })
                if(res.errors.length===0){
                    this.props.history.push(`/blog`)
                    console.log("New post succesful")
                }
            }
        }).catch(err => {
            console.log("POST ERROR",err)
            this.setState({ error: err.message })
        })
    }

    //Render form
    render() {
        //Display errors if they appear
        const style = this.state.error ? {} : {display: 'none'};
        return (
                <section className={styles.page}>
                    <form noValidate onSubmit={this.onSubmit}>
                        <aside role="alert" className={styles.page_error}>
                            {this.state.error}
                        </aside>
                        <h1 className={styles.page_header}>
                            Create new post
                        </h1>
                        <div className={styles.form_field}>
                            <label htmlFor="title">Title</label>
                            <input
                                type="text"
                                name="title"
                                className={styles.form_text_input}
                                placeholder="Type title"
                                value={this.state.title}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className={styles.form_field}>
                            <label htmlFor="content">Content</label>
                            <textarea
                                name="content"
                                className={styles.form_content_input}
                                placeholder="Type content"
                                value={this.state.content}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className={styles.form_field}>
                            Select categories:
                        </div>
                        {Object.keys(this.state.categories).map(key => (
                            <div className={styles.form_field}>
                                <label htmlFor="categories">{this.state.categories[key].categoryName}</label>
                                <input
                                    name={this.state.categories[key].categoryName}
                                    type="checkbox"
                                    value={this.state.categories[key].id}
                                    onChange={this.onChangeCheckbox}
                                />
                            </div>
                        ))}
                        <button
                            type="submit"
                            className={styles.form_field+ ' ' + styles.form_submit_button}
                        >
                            Submit new post
                        </button>
                    </form>
                </section>
        )
    }
}

export default NewPostForm
