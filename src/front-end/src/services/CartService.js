import axios from "axios"

const BASE_URL = "http://localhost:8080"


const instance = axios.create({
    baseURL: `${process.env.REACT_APP_API_URL}`,
    headers: {
    }
  })
  
  function GET(route, id = "") {
    return async function (id, token) {
      // const token = await getAccessToken()
      //const token = null
      let resp;
      if (token) {
        resp = await instance.get(id ? route + id : route, {
          headers: {
            Authorization: `Bearer ${token ? token : null}`,
          },
        });
      } else {
        resp = await instance.get(id ? route + id : route);
      }
  
      return resp.data;
    };
  }
  
  
  function POST(route) {
    return async function (json, token) {
      let resp;
      if (token) {
        resp = await instance.post(route, json, {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: `Bearer ${token ? token : null}`,
          },
        });
      } else {
        resp = await instance.post(route, json);
      }
  
      return resp.data;
    };
  }

  function PUT(route) {
    return async function (json, token) {
      let resp;
      if (token) {
        resp = await instance.put(route, json, {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: `Bearer ${token ? token : null}`,
          },
        });
      } else {
        resp = await instance.put(route, json);
      }
  
      return resp.data;
    };
  }
  
 
  
  export const ServiceCart = {
    Get_Cart: GET('cart'),
  }
  