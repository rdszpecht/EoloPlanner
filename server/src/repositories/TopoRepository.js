import axios from 'axios';

export async function getTopographicDetails(city) {
    try {
        var response = await axios.get('http://localhost:8080/api/topographicdetails/' + city)
        return response.data;
    } catch (error) {
        throw(error)
    }
}