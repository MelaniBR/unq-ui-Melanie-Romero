import React from 'react';

export class Timeline extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: '',
    }
    this.user = this.props.location.state && this.props.location.state.user;
    if(!this.user) {
      this.props.history.push('/');
    }
  }

  componentDidMount() {
    getPost()
      .then(posts => this.setState({ posts }))
      .catch(error => this.setState({ error }))
  }

  renderPost() {
    const { posts } = this.state;
    if(posts.length === 0) {
      return <div>Loading!!</div>
    }
    return (
      <div>
      </div>
    );
  }

  render() {
    const { name } = this.user || {};
    return (
      <div className="container">
        <h1>Hello {name}!!</h1>
      </div>
    );
  }
}