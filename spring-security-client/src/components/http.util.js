import axios from 'axios';
axios.interceptors.response.use(function (response) {
  return response;
}, function (error) {
  if (error.response.status === 502) {
    location.href = '/#/underMaintenance';
  }
  else {
    return Promise.reject(error);
  }
});

export const get = async (url, params, token) => await axios.get(url, {
  params: {
    ...params,
  },
});

export const download = async (url, params) => await axios({
  url: url,
  method: 'GET',
  responseType: 'blob',
  params: params,
});

export const post = async (url, params) => await axios.request({

  url: url,
  method: 'POST',
  params: {
    ...params,
  },
});

export const put = async (url, params) => await axios.request({
  url: url,
  method: 'put',
  data: params,
});

export const deleteRequest = async (url, params) => await axios.request({
  url: url,
  method: 'delete',
  data: params,
});
