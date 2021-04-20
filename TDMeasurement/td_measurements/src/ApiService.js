import axios from 'axios';
//TODO: delete this method
class ApiService {
    upload(data) {
        return axios.post('http://localhost:8004/directories/upload', data);
    }
}

export default new ApiService();