import React, { Component } from 'react';
import ReactDOM from 'react-dom'

//This is just experimenting and testing file.. I never acctualy used react-dom but I tried to do one homework in it.

var state = {
  todos: [
      { text: 'Hi', completed: true  },
      { text: 'Hello', completed: false },
      { text: 'Hi there!', completed: true },
  ],
  filter: 'all'
};

function createHtmlWithCreateElement (todos, targetEl) {
  targetEl.innerHTML = ''

  for (var i = 0; i < todos.length; i++) {
      var todo = todos[i];

      // Prepare elements
      var inputEl = document.createElement('input');
      inputEl.classList.add('toggle');
      inputEl.type = 'checkbox';
      inputEl.checked = todo.completed;

      var labelEl = document.createElement('label');
      labelEl.textContent = todo.text;
      
      // Append these elements to the todo wrapper element
      var todoEl = document.createElement('li');
      todoEl.appendChild(inputEl);
      todoEl.appendChild(labelEl);

      // Append todo to the page
      targetEl.appendChild(todoEl);
  }
}

class Test extends Component {
      
    componentDidMount(){
      const node = ReactDOM.findDOMNode(this);
      if (node instanceof HTMLElement) {
        var todoListEl = document.querySelector('.todo-list');
        console.log('The element is ' + todoListEl);
        createHtmlWithCreateElement(state.todos, todoListEl);
      }
    }

    render() {
      return(
        <p className="todo-list">Yo, this is dashboard.</p>
      )
    }
  }