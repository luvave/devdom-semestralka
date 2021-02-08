import React, { Component } from 'react';

import styles from '../../styles/Main.module.scss';

import { withRouter} from 'react-router-dom'

import { getAllPosts } from './PostRequests'

//Preview of each post to display in /blog
class PostPreview extends Component {

    constructor(props){
        super();
        this.state = {
            id: props.id,
            title: props.title,
            content: props.content,
            author: props.author,
            categories: props.categories
        };

        this.routeChange = this.routeChange.bind(this)
    }

    //When clicking on blog navigate to its detail
    routeChange(){ 
      var path = `/blog/` + this.state.id; 
      this.props.history.push(path);
    }

    //Html
    render() {

      return(
        <article className={styles.post_detail} onClick={this.routeChange}>
            <h2 className={styles.post_title}>{this.state.title}</h2>
            <p className={styles.post_content}>{this.state.content}</p>
            {/*}
            <p className={styles.post_author}>Author of the post: {this.state.author.nickname}</p>
            <p className={styles.post_author}>Post categories: 
            {this.state.categories===undefined ? '' : Object.keys(this.state.categories).map(key => (
              <a> {this.state.categories[key].categoryName} </a>
            ))}
            </p>
            */}
        </article>
      )
    }
  }

  export default withRouter(PostPreview); 