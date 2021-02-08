import React, { Component } from 'react'
import { addNewQuestion, getAllQuestionCategories } from './QuestionRequests'

import styles from '../../styles/Main.module.scss';

//Form for getting adding new Question
class NewQuestion extends Component {
    //Current state
    constructor() {
        super()
        this.state = {
            text: '',
            nextQuestionID: '',
            categories: [],
            selectedCategories: [],
            error: ''
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
        this.onChangeCheckbox = this.onChangeCheckbox.bind(this)
    }

    //Get all avaliable question categories from backend
    componentDidMount(){
        getAllQuestionCategories().then(res => {
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

        const newQ = {
            questionText: this.state.text,
            questionCategories: this.state.selectedCategories
        }

        addNewQuestion(newQ).then(res => {
            if (res) {
                this.setState({ error: res.errors })
                if(res.errors.length===0){
                    //this.props.history.push(`/newquestion`)
                    this.setState({ text: '' })
                    console.log("New question submited succesfully")
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
                            Add new question
                        </h1>
                        <div className={styles.form_field}>
                            <label htmlFor="title">Text</label>
                            <input
                                type="text"
                                name="text"
                                className={styles.form_text_input}
                                placeholder="Type text of the question"
                                value={this.state.text}
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
                            Submit new question
                        </button>
                    </form>
                </section>
        )
    }

}

export default NewQuestion