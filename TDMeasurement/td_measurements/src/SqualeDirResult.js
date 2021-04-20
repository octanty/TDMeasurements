import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';

class SqualeDirResult extends Component {

   constructor(props) {
      super(props);
      this.state = {dirResults: [], isLoading: true};
   }

   componentDidMount() {
       this.setState({isLoading: true});
       fetch('/squale/listResult')
         .then(response => response.json())
         .then(data => this.setState({dirResults: data, isLoading: false}));
    }

  render() {
   const {dirResults, isLoading} = this.state;
        if (isLoading) {
          return <p>Loading...</p>;
        }

    const dirResultList = dirResults.map(dirResult => {
      return <tr>
             <td>{dirResult.dirName}</td>
             <td>{dirResult.imID}</td>
             <td>{dirResult.imEC}</td>
             <td>{dirResult.imAC}</td>
             <td>{dirResult.imSAK}</td>
             <td>{dirResult.imCC}</td>
             <td>{dirResult.imMS}</td>
             <td>{dirResult.imNOM}</td>
             <td>{dirResult.imSC}</td>
             <td>{dirResult.imSOCRate}</td>
             <td>{dirResult.imDup}</td>
             <td>{dirResult.imDistance}</td>
             <td>{dirResult.imDC}</td>
             <td>{dirResult.wimID}</td>
             <td>{dirResult.wimEC}</td>
             <td>{dirResult.wimAC}</td>
             <td>{dirResult.wimSAK}</td>
             <td>{dirResult.wimCC}</td>
             <td>{dirResult.wimMS}</td>
             <td>{dirResult.wimNOM}</td>
             <td>{dirResult.wimSC}</td>
             <td>{dirResult.wimSOCRate}</td>
             <td>{dirResult.wimDup}</td>
             <td>{dirResult.wimDistance}</td>
             <td>{dirResult.wimDC}</td>
      </tr>
    });

    return (
      <div>
        <Container fluid>
          <h3>File results In Directory</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th>Path</th>
              <th>IM Inheritance Dept</th>
              <th>IM Efferent Coupling</th>
              <th>IM Afferent coupling</th>
              <th>IM Swiss Army Knife</th>
              <th>IM Class Cohesion</th>
              <th>IM Method size</th>
              <th>IM Number of Methods</th>
              <th>IM Spaghety Code</th>
              <th>IM SOC Rate</th>
              <th>IM Copy Paste</th>
              <th>IM Distance</th>
              <th>IM Dependency Cycle</th>
              <th>Weight IM Inheritance Dept</th>
              <th>Weight IM Efferent Coupling</th>
              <th>Weight IM Afferent coupling</th>
              <th>Weight IM Swiss Army Knife</th>
              <th>Weight IM Class Cohesion</th>
              <th>Weight IM Method size</th>
              <th>Weight IM Number of Methods</th>
              <th>Weight IM Spaghety Code</th>
              <th>Weight IM SOC Rate</th>
              <th>Weight IM Copy Paste</th>
              <th>Weight IM Distance</th>
              <th>Weight IM Dependency Cycle</th>
            </tr>
            </thead>
            <tbody>
            {dirResultList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default SqualeDirResult;