import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { Button } from 'reactstrap';

class App extends Component {
  state = {
    isLoading: true,
    directories: []
  };

  async componentDidMount() {
    const response = await fetch('/directories/listDirectoryInDB');
    const body = await response.json();
    this.setState({ directories: body, isLoading: false });
  }


  render() {
    const {directories, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const directoryList = directories.map(directory => {
      return <tr key={directory.id}>
        <td style={{whiteSpace: 'nowrap'}}>{directory.path}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/directory/" + group.id}>Edit</Button>
          </ButtonGroup>
        </td>
      </tr>
    });
export default App;