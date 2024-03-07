import { apiClient } from "./ApiClient";


export const retrieveHelloWorldBean = () => apiClient.get('/hello-world-bean')
export const retrieveHelloWorldPathVariable =
    (username) => apiClient.get(`/hello-world/path-variable/${username}`
        /* ,{
            headers: {
                Authorization: token
            }
        } */
    )

// 기본 인증 url의 토큰은 남겨두어야 함, 당연히 로그인되기 전이니 공통으로 apiClinet에 삽입될 토큰이 없는 상태이니까
export const excuteBasicAuthenticationService =
    (token) => apiClient.get(`/basicauth`
        , {
            headers: {
                Authorization: token
            }
        }
    )