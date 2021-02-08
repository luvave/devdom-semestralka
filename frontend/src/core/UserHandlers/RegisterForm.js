import React, { Component } from 'react'
import { register,getIpAddress } from './UserRequests'

import styles from '../../styles/Main.module.scss';

//Registration form
class Register extends Component {
    //Current state
    constructor() {
        super()
        this.state = {
            nickname: '',
            email: '',
            password: '',
            errors: []
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
    }

    //On change set to state
    onChange (e) {
        this.setState({ [e.target.name]: e.target.value })
    }

    //On submit post data to server
    async onSubmit (e) {
        e.preventDefault()

        //Because people might not get on dashboard first, I get Ip address here also
        getIpAddress().then(res =>
            {
                const newUser = {
                    visitor: {
                        ipAddress : res
                    },
                    nickname: this.state.nickname,
                    email: this.state.email,
                    password: this.state.password
                }
                this.setState({ password: '' })
                
                register(newUser).then(res => {
                    this.setState({ errors: res.errors })
                    if(res.errors.length===0){
                        console.log("true")
                        this.props.history.push(`/login`)
                    }
                }).catch(err => {
                    console.log(err);
                })
            });
    }

    //html
    render () {
        //Display errors if they appear
        const style = Object.keys(this.state.errors).length===0 ? {display: 'none'} : {};
        //Render register form
        return (
                <section className={styles.page}>
                    <form noValidate onSubmit={this.onSubmit}>
                        <aside role="alert" className={styles.page_error}>
                        {Object.keys(this.state.errors).map((val) => <div>- {this.state.errors[val]}</div>)}
                        </aside>
                        <h1 className={styles.page_header}>
                            Register
                        </h1>
                        <div className={styles.form_field}>
                            <label htmlFor="nickname">Nickname</label>
                            <input
                                type="text"
                                name="nickname"
                                className={styles.form_text_input}
                                placeholder="Enter your nickname/username"
                                value={this.state.nickname}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className={styles.form_field}>
                            <label htmlFor="email">Email address</label>
                            <input
                                type="email"
                                name="email"
                                className={styles.form_text_input}
                                placeholder="Enter email"
                                value={this.state.email}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className={styles.form_field}>
                            <label htmlFor="password">Password</label>
                            <input
                                type="password"
                                name="password"
                                className={styles.form_text_input}
                                placeholder="Password"
                                value={this.state.password}
                                onChange={this.onChange}
                            />
                        </div>
                        <button
                            type="submit"
                            className={styles.form_field+ ' ' + styles.form_submit_button}
                        >
                            Register!
                        </button>
                    </form>
                </section>
        )
    }
}

export default Register
