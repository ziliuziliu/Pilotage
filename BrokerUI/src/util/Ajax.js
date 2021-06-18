import axios from 'axios';

let server = "http://202.120.40.8:";
export function axiosPost(port, method, json, callback) {
    let url = server + port + method;
    console.log(url);
    axios.post(url, json).then((response) => {
        console.log(response);
        callback(response.data);
    })
}

export function axiosParam(port, method, json, callback) {
    let url = server + method;
    axios.get(url, {
        params: json
    }).then((response) => {
        console.log(response.data);
        callback(response.data);
    })
}


export default {
    axiosPost
}
