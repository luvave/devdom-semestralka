import React, { Component } from 'react';

import styles from '../styles/Main.module.scss';

import { getAllPosts } from './PostHandlers/PostRequests'

import PostPreview from './PostHandlers/PostPreview'

//Blog page
class Blog extends Component {

  constructor() {
    super();
    this.state = {
      posts: [{
        id: 0,
        title: "No blog posts avaliable",
        content: "There was an error in getting blog posts from our backend.",
        author: { nickname: "admin" }
      }]
    };
  }

  //Get all avaliable posts from backend
  componentDidMount() {
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

  //Html... render PostPreview for each post
    render() {

      return(
        <section className={styles.page}>
            <h1 className={styles.page_header}>This is blog....</h1>
            {Object.keys(this.state.posts).map(key => (
              <PostPreview id={this.state.posts[key].id} 
              title={this.state.posts[key].title} 
              content={this.state.posts[key].content} 
              author={this.state.posts[key].author}
              categories={this.state.posts[key].postCategories}
              />
            ))}
        </section>
      )
    }
  }
  export default Blog; 