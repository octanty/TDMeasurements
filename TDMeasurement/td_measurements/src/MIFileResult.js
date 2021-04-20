import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';

class MIFileResult extends Component {

   constructor(props) {
      super(props);
      this.state = {fileResults: [], isLoading: true};
   }

   componentDidMount() {
       this.setState({isLoading: true});
       fetch('mi/dirResult/findFileResultByDirectoryID/1')
         .then(response => response.json())
         .then(data => this.setState({fileResults: data, isLoading: false}));
    }

  render() {
   const {fileResults, isLoading} = this.state;
        if (isLoading) {
          return <p>Loading...</p>;
        }

    const fileResultList = fileResults.map(fileResult => {
      return <tr>
             <td>{fileResult.fileName}</td>
             <td>{fileResult.numComment}</td>
             <td>{fileResult.lineOfCode}</td>
             <td>{fileResult.numOperator}</td>
             <td>{fileResult.numOperand}</td>
             <td>{fileResult.uniqueOperator}</td>
             <td>{fileResult.uniqueOperand}</td>
             <td>{fileResult.programVocabulary}</td>
             <td>{fileResult.programVolume}</td>
             <td>{fileResult.programLength}</td>
             <td>{fileResult.difficulty}</td>
             <td>{fileResult.effort}</td>
             <td>{fileResult.numOfDelBugs}</td>
             <td>{fileResult.estimateProgLength}</td>
             <td>{fileResult.timeReqToProg}</td>
             <td>{fileResult.ccvalue}</td>
             <td>{fileResult.ccrate}</td>
             <td>{fileResult.maintainabilityIndex}</td>
             <td>{fileResult.mirate}</td>
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
              <th>Comment</th>
              <th>Line Of Code</th>
              <th>Operator</th>
              <th>Operand</th>
              <th>Unique Operator</th>
              <th>Unique Operand</th>
              <th>Vocabulary</th>
              <th>Volume</th>
              <th>Program Length</th>
              <th>Difficulty</th>
              <th>Effort</th>
              <th>Number of Delivered Bugs</th>
              <th>Estimate Program Length</th>
              <th>Total Time Required To Program</th>
              <th>Cyclomatic Complexity</th>
              <th>Cyclomatic Complexity Rate</th>
              <th>Maintainability Index</th>
              <th>Maintainability Index Rate</th>
            </tr>
            </thead>
            <tbody>
            {fileResultList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default MIFileResult;