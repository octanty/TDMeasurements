import React, { Component } from 'react';
import './App.css';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import ApiService from './ApiService';


class Home extends Component {

    onFileChange = event => {
      // Update the state
      this.setState({ selectedFile: event.target.files[0] });
    };

    onFileUpload = event => { /*
          const formData = new FormData();

             // Update the formData object
             formData.append(
               "file",
               this.state.selectedFile,
               this.state.selectedFile.name
             );
            //TODO: change this function, not using axios
            ApiService.upload(formData)
                .then(res => {
                        console.log(res.data);
                        alert("File uploaded successfully.")
                })
        };*/

   const formData = new FormData();
     formData.append(
          "file",
           this.state.selectedFile,
           this.state.selectedFile.name
      );
   fetch('http://localhost:8004/directories/upload', {
       method: 'POST',
       body: formData
     })
      .then(success => console.log(success))
      .catch(error => console.log(error));
      };

  render() {
    return (
     <div className="App-intro">
        Upload Directory
        <Container fluid>
            <input type="file" className="form-control" onChange={this.onFileChange}/>
            <Button color="success" onClick={this.onFileUpload}>Upload</Button>
            <Button color="link"><Link to="/listDirectory">List Directory</Link></Button>
        </Container>
      </div>
    );
  }
}



export default Home;