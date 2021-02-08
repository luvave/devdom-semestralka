import React from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom'

import styles from './styles/Main.module.scss';

import Dashboard from './core/Dashboard.js';
import Header from './core/Header.js';
import Blog from './core/Blog.js';
import RegisterForm from './core/UserHandlers/RegisterForm';
import LoginForm from './core/UserHandlers/LoginForm';
import NewPostForm from './core/PostHandlers/NewPostForm';
import NewCategories from './core/PostHandlers/NewCategories.js';
import SinglePost from './core/PostHandlers/SinglePost.js';
import NewQuestion from './core/QuestionHandlers/NewQuestion.js';
import About from './core/About';

//File that connects all other important files and prepares routing
function App() {
  //.env files in root are for enviroment changes, on localhost it expects backend on localhost, in build production it expects on devdom.org
  require('dotenv').config()
  
  return (
    //Route for navigation
    //Generating the code from additional file
    <Router>    
      <div className={styles.App}>
        <Header/>
        <div>
          <Route exact path="/" component={Dashboard}/>
          <Route exact path="/about" component={About}/>
          <Route exact path="/blog" component={Blog}/>
          <Route exact path="/register" component={RegisterForm}/>
          <Route exact path="/login" component={LoginForm}/>
          <Route exact path="/newpost" component={NewPostForm}/>
          <Route exact path="/categories" component={NewCategories}/>
          <Route exact path="/newquestion" component={NewQuestion}/>
          <Route exact path="/blog/:postId" component={SinglePost}/>
        </div>
      </div>
    </Router>
  );
}

export default App;
