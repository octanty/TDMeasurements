import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';

class MIDirectoryResult extends Component {

  constructor(props) {
       super(props);
       this.state = {results: [], isLoading: true};
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('mi/dirResult')
      .then(response => response.json())
      .then(data => this.setState({results: data, isLoading: false}));
  }


  render() {
    const {results, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const dirResultList = results.map(result => {
      return <tr>
           <td>{result.dirName}</td>
           <td>{result.avgComment}</td>
           <td>{result.avgLineOfCode}</td>
           <td>{result.totalOperator}</td>
           <td>{result.totalOperand}</td>
           <td>{result.totalUniqueOp}</td>
           <td>{result.totalUniqueOpr}</td>
           <td>{result.totalProgVocab}</td>
           <td>{result.avgProgVolume}</td>
           <td>{result.totalProgLength}</td>
           <td>{result.totalEstimateProgLengt}</td>
           <td>{result.totalTimeReqToProg}</td>
           <td>{result.totalDifficulty}</td>
           <td>{result.totalEffort}</td>
           <td>{result.totalNumOfDelBugs}</td>
           <td>{result.avgCC}</td>
           <td>{result.totalCCRate}</td>
           <td>{result.avgMainatainability}</td>
           <td>{result.avgMIRate}</td>
           <td>
               <Button size="sm" color="primary" tag={Link} to={"/miFileResult"}>Details</Button>
            </td>
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
                      <th>Average Comment</th>
                      <th>Average Line Of Code</th>
                      <th>Total Operator</th>
                      <th>Total Operand</th>
                      <th>Total Unique Operator</th>
                      <th>Total Unique Operand</th>
                      <th>Total Vocabulary</th>
                      <th>Average Halstead Volume</th>
                      <th>Total Program Length</th>
                      <th>Estimate Program Length</th>
                      <th>Total Time Required To Program</th>
                      <th>Total Difficulty</th>
                      <th>Total Effort</th>
                      <th>Total Number of Delivered Bugs</th>
                      <th>Average Cyclomatic Complexity</th>
                      <th>Cyclomatic Complexity Rate</th>
                      <th>Average Maintainability Index</th>
                      <th>Average Maintainability Index Rate</th>
                      <th>Action</th>
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

export default MIDirectoryResult;