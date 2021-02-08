import React, { Component } from 'react';
import { visitor,getIpAddress } from './UserHandlers/UserRequests'
import {NavLink} from 'react-router-dom'

import styles from '../styles/Main.module.scss';

import Question from './QuestionHandlers/Question';
import About from './About';

//Dashboard page
class Dashboard extends Component{
   
    constructor(props) {
        super(props);
    }
    
    //On page load get Ip address and send it to backend (it will be used in future in recommendation process)
    async componentDidMount() {
      getIpAddress().then(res =>
        {
          var today = new Date();
          var date = today.getFullYear()+'-'+("0" + (today.getMonth() + 1)).slice(-2)+'-'+("0" + today.getDate()).slice(-2);
          var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
          const newVisitor = {
            ipAddress: res,
            last_visit_date: date,
            last_visit_time: time
          }
          visitor(newVisitor).then(res => {
             // console.log(res)
          }).catch(err => {
              console.log(err)
          })
        }
      );
    }

    render(){
      return(
        <section className={styles.page}>
          <h1 className={styles.page_header}>Dashboard</h1>
          <Question/>
          <NavLink to="/about" className={styles.better_link}>
            What is this site about?
          </NavLink>
        </section>
      )
    }
}
export default Dashboard