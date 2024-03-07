import { apiClient } from "./ApiClient"

// 기본 인증 url의 토큰은 남겨두어야 함, 당연히 로그인되기 전이니 공통으로 apiClinet에 삽입될 토큰이 없는 상태이니까
export const excuteBasicAuthenticationService =
    (token) => apiClient.get(`/basicauth`
        , {
            headers: {
                Authorization: token
            }
        }
    )

export const excuteJwtAuthenticationService =
    (username, password) => apiClient.post(`/authenticate`
        , {
            username,
            password
        }
    )

// "eyJraWQiOiI5OWU1Y2MyNy1lMTY3LTRhZjYtODQyMC01MGFiM2U4ZjIwMWEiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiaW4yOG1pbnV0ZXMiLCJleHAiOjE3MDk3NDY5NDIsImlhdCI6MTcwOTc0MTU0Miwic2NvcGUiOiJST0xFX1VTRVIifQ.gai5cvV4GTuOaC87D2tH75p47szcUYPV7RkE0EzF6BGh-xpYrXiS_5DoQ0kz5CQXg_uz38qfQf3mbR-jOX8y23YJ5UlCJItuzF_vQbqAOM9SfqmGa2IZOQsTzxCRipLUMhlpidKbL2FOYOuYFeSGoEOM9DcfF0qWqDvb5sQ0RAxdhKg-T_oW2pXdmNTY7oGxOtlwkbg1-vCXZ7C0Eb-OZjrwXBJ88IHZti4UbLUe_8n4V-vQ_C1fxqzFiDpz3pyYfqynX5zLNPlrtah9COcEm24XwdRcgL4X1fqpya0RDrFXJ-qQj0-36g1xJBBOTuKb_q1WaivhtfiF2-vp19mcKQ"
