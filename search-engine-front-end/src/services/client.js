import axios from "axios";

const baseURL = "http://localhost:8097/api";

const client = axios.create({ baseURL });

export default client;
