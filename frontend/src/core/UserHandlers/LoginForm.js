import React, { Component } from 'react'
import { login } from './UserRequests'

import styles from '../../styles/Main.module.scss';

//Login form 
class Login extends Component {
    //Current state
    constructor() {
        super()
        this.state = {
            nickname: '',
            password: '',
            error: '' 
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
    }

    //If something is changed save to state
    onChange(e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    //On submit post data to server
    onSubmit(e) {
        e.preventDefault()

        const user = {
            nickname: this.state.nickname,
            password: this.state.password
        }
        this.setState({ password: '' })

        login(user).then(res => {
            if (res) {
                this.props.history.push(`/`)
                console.log("Login successful")
            }
        }).catch(error => {
            this.setState({ error: error.response.data.message })
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
                            Login to your account
                        </h1>
                        <div className={styles.form_field}>
                            <label htmlFor="nickname">Nickname</label>
                            <input
                                type="text"
                                name="nickname"
                                className={styles.form_text_input}
                                placeholder="Enter your nickname"
                                value={this.state.nickname}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className={styles.form_field}>
                            <label htmlFor="password">Password</label>
                            <input
                                type="password"
                                name="password"
                                className={styles.form_text_input}
                                placeholder="Enter your password"
                                value={this.state.password}
                                onChange={this.onChange}
                            />
                        </div>
                        <button
                            type="submit"
                            className={styles.form_field+ ' ' + styles.form_submit_button}
                        >
                            Sign in
                        </button>
                    </form>
                </section>
        )
    }
}

export default Login
