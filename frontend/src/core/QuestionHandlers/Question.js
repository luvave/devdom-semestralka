import React, { Component } from 'react'
import { getRandQuestion, getAllQuestionCategories } from './QuestionRequests'

import QuestionSVG from './QuestionSVG'

import { getAllPosts } from '../PostHandlers/PostRequests'

import styles from '../../styles/Main.module.scss';

import PostPreview from '../PostHandlers/PostPreview'

//Show question with answer button.. on home /
class Question extends Component {

    constructor(props){
        super();
        this.state = {
            id: '',
            text: '',
            nextQuestion: {},
            answer: '',
            posts: [],
            resultPost: null
        };
        this.count = 0;

        this.svgElement = React.createRef();

        this.handleSubmitAnswer = this.handleSubmitAnswer.bind(this);
        this.getRandomQuestion = this.getRandomQuestion.bind(this);
    }

    //Show random question on load and get posts
    componentDidMount() {
      this.getRandomQuestion();
      getAllPosts().then(res => {
        if(res[0].id!=undefined){
        this.setState({
          posts: []
        })
        this.setState({
          posts: res
        })
      }
      }).catch(err =>{
        console.log(err);
      });
    }
    
    //Since backend doesnt support anything better for now, lets get random question
    getRandomQuestion(){
      getRandQuestion().then(res => {
        this.setState({
          text: res.questionText
        })
      }).catch(err =>{
        console.log(err);
      });
    }

    //Submiting answer will be implemented in backend in future, for now only it does is it changes SVG smileface
    handleSubmitAnswer(e){
      this.getRandomQuestion();
      this.svgElement.current.changeCurrentSvg();
      this.count++;
      if(this.count>3 && e=="yes"){
        this.setState({resultPost: Math.floor(Math.random() * this.state.posts.length)})
      }
    }

    //Html
    render() {
      const Question = (
        <div>
            <h1>Question:</h1><h1 className={styles.question_text}>{this.state.text}</h1>
            <button className={styles.question_button} type="button" onClick={() => { this.handleSubmitAnswer("yes") }}>YES</button>
            <button className={styles.question_button} type="button" onClick={() => { this.handleSubmitAnswer("no") }}>NO</button>
            <div className={styles.form_field}>
            <label htmlFor="title" className={styles.question_label}>Another answer:</label>
            <input
                type="text"
                name="answer"
                placeholder="Type answer"
                className={styles.question_input}
                value={this.state.title}
            />
            </div>
            <button
                type="submit"
                className={styles.question_button}
                onClick={() => { this.handleSubmitAnswer("submit") }}
            >
                Submit answer
            </button>
        </div>
      )

      const Result = (
        <div className={styles.question_result}>
          <h1 className={styles.page_header}>I think you should be interested in:</h1>
          {this.state.resultPost &&
              <PostPreview id={this.state.posts[this.state.resultPost].id} 
              title={this.state.posts[this.state.resultPost].title} 
              content={this.state.posts[this.state.resultPost].content} 
              author={this.state.posts[this.state.resultPost].author}
              categories={this.state.posts[this.state.resultPost].postCategories}
              />
          }
        </div>
      )

      return(
        <section className={styles.question}>
            <QuestionSVG ref={this.svgElement} />
            {this.state.resultPost ? Result : Question}
        </section>
      )
    }
  }
  export default Question; 