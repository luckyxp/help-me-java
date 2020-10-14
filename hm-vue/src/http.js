import axios from 'axios';


// 创建axios实例
var instance = axios.create({ timeout: 1000 * 12 });
instance.interceptors.request.use(
    config => {
        if (localStorage.getItem('Authorization')) {
            config.headers.Authorization = localStorage.getItem('Authorization');
        }
        return config
    },
    error => {
        return Promise.reject(error)

    })

//异步请求后，判断token是否过期
instance.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    localStorage.removeItem('Authorization');
                    this.$router.push('/login');
                    this.$message({
                        showClose: true,
                        message: "请重新登录",
                        type: "error",
                      });
            }
        }
    }
)
export default instance;