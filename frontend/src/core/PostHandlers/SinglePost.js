import React, { Component } from 'react';

import styles from '../../styles/Main.module.scss';

import { getAllPosts } from './PostRequests'

//Detail of the Post
class SinglePost extends Component {

    constructor(props){
        super();
        this.state = {
            id: props.match.params.postId,
            title: '',
            content: '',
            author: '',
            categories: '',
            posts: []
        };
    }

    //Because i am lazy i didnt implement get post by Id to backend... so i am getting all posts.. yeah i will change it in future
    componentDidMount() {
        getAllPosts().then(res => {
          if(res[0].id!=undefined){
          this.setState({
            posts: []
          })
          this.setState({
            posts: res
          })
          var curPost = this.state.posts.find(x => x.id == this.props.match.params.postId);
          if(curPost){
            this.setState({
               title: curPost.title,
               content: curPost.content,
               author: curPost.author,
               categories: curPost.postCategories
            })
          }
        }
        }).catch(err =>{
          console.log(err);
        });
    }
    
    //Html
    render() {

      return(
        <section className={styles.page}>
            <h1 className={styles.page_header}>{this.state.title}</h1>
            <p className={styles.post_content}>{this.state.content}</p>
            <p className={styles.post_author}>Author of the post: {this.state.author.nickname}</p>
            <p className={styles.post_author}>Post categories: 
            {this.state.categories===undefined ? '' : Object.keys(this.state.categories).map(key => (
              <a> {this.state.categories[key].categoryName} </a>
            ))}
            </p>
        </section>
      )
    }
  }
  export default SinglePost; 