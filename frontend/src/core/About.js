import React, { Component } from 'react';

import sound from '../media/welcome.mp3'

import styles from '../styles/Main.module.scss';

//Static page just for some words and audio file
class About extends Component {
    constructor() {
        super();

        this.audio = new Audio(sound)
        this.playWelcome = this.playWelcome.bind(this);
    }

    componentDidMount() {
        this.audio.addEventListener('ended', () => this.setState({ play: false }));
      }
    
      componentWillUnmount() {
        this.audio.removeEventListener('ended', () => this.setState({ play: false }));  
      }
    
    //For some reason React just have to makes thing more complicated... and Chrome doesnt allow autoplay
    playWelcome() {
        this.audio.play()
    }

    //Html
    render() {

        return(
            <section className={styles.page}>
                <h1 className={styles.page_header}>About this site</h1>
                <p className={styles.post_content}>
                    It was created as a school project for multiple subjects.
                </p>
                <p className={styles.post_content}>
                    <b>It is still in development.</b> The idea of this project is that anyone can answer questions regarding tools for software developers.
                    <br/>Based on your answers, system should be able to recommend you post on tool, that might help you with your job.
                </p>
                <p className={styles.post_content}>
                    Posts are simple informations about tools, libraries, frameworks, softwares etc.. Each post can be at the same time a review.
                    <br/>Each posts has categories, so you should be able to filter categroy that interests you.
                </p>
                <p className={styles.post_content}>
                    If you want to add content, all you have to do is create account and login. You will see all avaliable forms.
                    <br/>Passwords are encrypted so you dont have to worry about security.
                </p>
                <p className={styles.post_content}>
                    Created frontend in React, with some extra libraries. Backend is in Java, using Spring framework. Database is basic PostgreSQL.
                </p>
                <button className={styles.question_button} onClick={this.playWelcome}>Play welcome sound</button>
            </section>
        )
    }
}
export default About; 