import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import ListDirectory from './ListDirectory';
import MIFileResult from './MIFileResult';
import MIDirectoryResult from './MIDirectoryResult';
import SqualeDirResult from './SqualeDirResult';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
            <Route path='/' exact={true} component={Home} />
            <Route path='/listDirectory' exact={true} component={ListDirectory} />
            <Route path='/miDirectoryResult' exact={true} component={MIDirectoryResult} />
            <Route path='/miFileResult'  exact={true} component={MIFileResult}/>
            //TODO: change this routename
            <Route path='/squaleDirResult'  exact={true} component={SqualeDirResult}/>
        </Switch>
       </Router>
    );
  }
}

export default App;