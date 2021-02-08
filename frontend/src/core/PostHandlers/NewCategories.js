import React, { Component } from 'react'
import { newPostCategory, getAllPostCategories } from './PostRequests'

import styles from '../../styles/Main.module.scss';

//Form for adding new categories, for now it only supports post categories
class NewCategories extends Component {

    //Current state
    constructor() {
        super()
        this.state = {
            postCategories: [],
            newCategory: '',
            error: [],
            message: ''
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
        this.getAllPostCategories = this.getAllPostCategories.bind(this)
    }

    //On mount get all categories
    componentDidMount(){
        this.getAllPostCategories();
    }

    //Get all current categories from backend and save to array
    getAllPostCategories(){
        getAllPostCategories().then(res => {
            this.setState({ postCategories: res })
          }).catch(err =>{
            console.log(err);
        });
        console.log(this.state.postCategories);
    }

    //If something is changed save to state
    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    //On submit post data to server
    onSubmit(e) {
        e.preventDefault()

        const newCategory = {
            categoryName: this.state.newCategory
        }
        //Submit new category to backend
        newPostCategory(newCategory).then(res => {
            if (res) {
                this.setState({ message: "New category "+ res.categoryName +" submited succesfully" })
                console.log("New category submited succesfully")
            }
            this.getAllPostCategories();
        }).catch(err => {
            console.log("POST ERROR",err)
            this.setState({ error: err.message })
        })
    }


    //Html form
    render() {
        return (
                <section className={styles.page}>
                    <form noValidate onSubmit={this.onSubmit}>
                        <div role="alert" className={styles.page_error}>
                            {this.state.error}
                        </div>
                        <div role="alert" className={styles.page_error}>
                            {this.state.message}
                        </div>
                        <h1 className={styles.page_header}>
                            Create new categories
                        </h1>
                        <div className={styles.form_field}>
                            <label htmlFor="newCategory">New post category name</label>
                            <input
                                type="text"
                                name="newCategory"
                                className={styles.form_text_input}
                                placeholder="Type category name"
                                value={this.state.newCategory}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className={styles.form_field}>
                            Current post categories:
                        </div>
                        {Object.keys(this.state.postCategories).map(key => (
                            <div className={styles.form_field}>- {this.state.postCategories[key].categoryName}</div>
                        ))}
                        <button
                            type="submit"
                            className={styles.form_field+ ' ' + styles.form_submit_button}
                        >
                            Submit new categories
                        </button>
                    </form>
                </section>
        )
    }

}
export default NewCategories