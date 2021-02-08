import React, { Component } from 'react'
import styles from '../../styles/Main.module.scss';

import { MorphReplace } from 'react-svg-morph';

//All svg emojis are originaly downloaded from https://openmoji.org/, after that highly edited.

//I call it emoji that knows the answer
class KnowingSvg extends React.Component {
    render() {
        return (
        <svg id="emoji" viewBox="0 0 72 72" xmlns="http://www.w3.org/2000/svg">
        <g id="knowing">
            <path fill="#FCEA2B" d="M36,13c-12.6823,0-23,10.3177-23,23c0,12.6822,10.3177,23,23,23c12.6822,0,23-10.3178,23-23 C59,23.3177,48.6822,13,36,13z"/>
            <circle cx="36" cy="36" r="23" fill="none" stroke="#000000" stroke-width="0.9"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="0.9" d="M22.29,28.7c0.3266-0.4271,1.792-2.245,4.424-2.685c2.135-0.3566,3.794,0.4017,4.352,0.688"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="0.9" d="M41.93,26.77c0.4667-0.25,2.52-1.1,5.042-0.5743c2.118,0.3921,2.421,0.8283,3.73,1.725"/>
            <path d="M32,33c0,1.6568-1.3448,3-3,3c-1.6553,0-3-1.3433-3-3c0-1.6552,1.3447-3,3-3C30.6552,30,32,31.3448,32,33"/>
            <path d="M50,33c0,1.6568-1.3447,3-3,3s-3-1.3433-3-3c0-1.6552,1.3447-3,3-3S50,31.3448,50,33"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="0.9" d="M29.94,46.66c8.505,0.7027,16.67,0.4269,16.98-4.128"/>
        </g>
        </svg>
        );
    }
}

//Kinda getting confused 
class ConfusedSvg extends React.Component {
    render() {
        return (
        <svg id="emoji" viewBox="0 0 72 72" xmlns="http://www.w3.org/2000/svg">
        <g id="confused">
            <path fill="#FCEA2B" d="M36,13c-12.6823,0-23,10.3177-23,23s10.3177,23,23,23s23-10.3178,23-23C59.0001,23.3177,48.6823,13,36,13z"/>
            <circle cx="36" cy="36" r="23" fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.9"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="0.9" d="M23.0708,27.7432c0.4132-0.344,2.2456-1.7915,4.9091-1.6364c2.1608,0.1259,3.61,1.2333,4.0909,1.6364"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="0.9" d="M48.9292,27.0952c-0.4132,0.344-2.2456,1.7915-4.9091,1.6364c-2.1608-0.1259-3.61-1.2333-4.0909-1.6364"/>
            <path d="M30,35c0,1.6568-1.3448,3-3,3c-1.6553,0-3-1.3433-3-3c0-1.6552,1.3447-3,3-3C28.6552,32,30,33.3448,30,35"/>
            <path d="M48,35c0,1.6568-1.3447,3-3,3s-3-1.3433-3-3c0-1.6552,1.3447-3,3-3S48,33.3448,48,35"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.9" d="M29.1321,47.1385 c4.6002-1.4165,9.8284-1.4698,13.7359,0"/>
        </g>
        </svg>
        );
    }
}

//Just a smiley face
class SmileySvg extends React.Component {
    render() {
        return (
        <svg id="emoji" viewBox="0 0 72 72" xmlns="http://www.w3.org/2000/svg">
        <g id="smiley">
            <path fill="#FCEA2B" d="M36,13c-12.6823,0-23,10.3177-23,23s10.3177,23,23,23s23-10.3178,23-23C59.0001,23.3177,48.6823,13,36,13z"/>
            <circle cx="36" cy="36" r="23" fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.9"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="0.9" d="M21.1086,27.2015c0.7207-1.3857,1.9278-2.4541,3.3907-3c1.4052-0.7002,3.0205-0.8486,4.5302-0.4209"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="0.9" d="M50.719,27.2015c-1.582-2.7724-4.8037-4.1699-7.9092-3.4306"/>
            <path d="M30,35c0,1.6568-1.3448,3-3,3c-1.6553,0-3-1.3433-3-3c0-1.6552,1.3447-3,3-3C28.6552,32,30,33.3448,30,35"/>
            <path d="M48,35c0,1.6568-1.3447,3-3,3s-3-1.3433-3-3c0-1.6552,1.3447-3,3-3S48,33.3448,48,35"/>
            <path fill="none" stroke="#000000" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="0.9" d="M41.6308,48.0192c-1.1233,1.2679-3.0497,2.0788-5.7815,2.0788c-2.7113,0-4.6397-0.8017-5.7749-2.0544"/>
        </g>
        </svg>
        );
    }
}

//This is the main SVG component
class QuestionSVG extends Component{
    constructor(props){
        super();
        this.state = {
            cur : 3
        }
        this.changeCurrentSvg = this.changeCurrentSvg.bind(this);
    }

    //Since its endless random questions, here it will also show random emoji
    changeCurrentSvg(){
        var rand = Math.floor(Math.random() * 3) + 1 ;
        this.setState({
            cur: rand
        });
    }

    //Is used in conditional rendering
    renderSwitch(param) {
        switch(param) {
          case 1:
            return <KnowingSvg key="knowing" />;
          case 2:
            return <ConfusedSvg key="smiley" />;
          default:
            return <SmileySvg key="confused" />;
        }
      }

    //Since animation of svg paths is more difficult I used external library for "morphing" svg.
    render() {
        return(
            <div className={styles.question_svg}>
                <MorphReplace width={300} height={300}>
                    {this.renderSwitch(this.state.cur)}
                </MorphReplace>
            </div>
        )
    }
}
export default QuestionSVG; 

