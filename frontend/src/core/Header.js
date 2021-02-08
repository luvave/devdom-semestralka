import React, { Component } from 'react';
import {NavLink, withRouter} from 'react-router-dom'

import styles from '../styles/Main.module.scss';

import logo from '../images/main-logo.png';

//Header show on every page
class Header extends Component {

    constructor() {
      super();
      this.state = {
        checked: false
      };
      this.handleCheckboxChange = this.handleCheckboxChange.bind(this); 
    }

    //If clicking logout, remove usertoken
    logOut(e){
        e.preventDefault()
        localStorage.removeItem('usertoken')
        this.props.history.push('/')
    }
  

    handleCheckboxChange () {
      this.setState({checked: !this.state.checked});
    }

    
    render() {

        //Login and registration links
        const loginLink = (
            <NavLink to="/login" className={styles.main_menu_item} activeClassName={styles.main_menu_item_active}>
            Login
          </NavLink>
      )
      const regLink = (
            <NavLink to="/register" className={styles.main_menu_item} activeClassName={styles.main_menu_item_active}>
            Register
          </NavLink>
      )
    
      //Profile or logout links
      const logoutLink = (
            <NavLink to="/" onClick={this.logOut.bind(this)} className={styles.main_menu_item}>
              Logout
            </NavLink>
      )

      const newpostLink = (
        <NavLink to="/newpost" className={styles.main_menu_item} activeClassName={styles.main_menu_item_active}>
          Create Post
        </NavLink>
      )

      //New Category
      const addcategoryLink = (
        <NavLink to="/categories" className={styles.main_menu_item} activeClassName={styles.main_menu_item_active}>
          Categories
        </NavLink>
      )

      //Add question
      const addQuestion = (
        <NavLink to="/newquestion" className={styles.main_menu_item} activeClassName={styles.main_menu_item_active}>
          Questions
        </NavLink>
      )

      //Blog
      const blogLink = (
        <NavLink to="/blog" className={styles.main_menu_item} activeClassName={styles.main_menu_item_active}>
        Blog
        </NavLink>
      )

      //Home
      const homeLink = (
        <NavLink to="/" className={styles.main_menu_item} exact activeClassName={styles.main_menu_item_active}>
        Home
        </NavLink>
      )

      return(
        
        <header>
          <label className={styles.menu_icon}>
           &#9776;
          <input
            type="checkbox" 
            onChange={this.handleCheckboxChange}
            className={styles.menu_checkbox}
            />
          </label>
          <nav className={(this.state.checked ? styles.main_menu_checked : styles.main_menu)}>
            <div className={styles.logo}>
              <NavLink to="/" className={styles.logo_image}>
                <img alt="Devdom logo" className={styles.logo_image} src={logo}/>
              </NavLink>
              <div className={styles.logo_text}><p>Kingdom of Development</p></div>
            </div>
            {localStorage.usertoken ? [homeLink,blogLink,logoutLink,addQuestion,newpostLink,addcategoryLink] : [homeLink,blogLink,loginLink,regLink]}
            <label className={styles.hide_menu}>
              X
            <input
              type="checkbox" 
              onChange={this.handleCheckboxChange}
              className={styles.menu_checkbox}
              />
            </label>
          </nav>
        </header>
      )
    }
  }
  export default withRouter(Header); 