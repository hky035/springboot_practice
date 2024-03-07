import { createContext, useContext, useState } from "react"
import { excuteBasicAuthenticationService, excuteJwtAuthenticationService } from "../api/AuthenticationApiService"
import { apiClient } from "../api/ApiClient"

export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)

export default function AuthProvider({ children }) {

    const [isAuthenticated, setIsAuthenticated] = useState(false)
    const [username, setUsername] = useState('')

    const [token, setToken] = useState(null)

    /* async function login(username, password) {
        const baToken = 'Basic ' + window.btoa(username + ":" + password)

        try {
            const response = await excuteBasicAuthenticationService(baToken)

            if (response.status == 200) {
                setIsAuthenticated(true)
                setUsername(username)
                setToken(baToken)
                apiClient.interceptors.request.use( // axios 인터셉터 : 모든 api 요청을 인터셉트
                    (config) => {
                        console.log('intercepting and adding a token...')
                        config.headers.Authorization = baToken

                        return config
                    }
                )

                return true
            }
            else {
                // setIsAuthenticated(false)
                //setUsername(null)
                //setToken(null) 
                logout()
                return false
            }
        } catch (error) {
            // logout()과 동일한 로직이므로 logout()으로 대체
            // setIsAuthenticated(false)
            // setUsername(null)
            // setToken(null) 
            logout()
            return false
        }
    } */

    async function login(username, password) {
        try {
            const response = await excuteJwtAuthenticationService(username, password)

            if (response.status == 200) {
                const jwtToken = 'Bearer ' + response.data.token
                setIsAuthenticated(true)
                setUsername(username)
                setToken(jwtToken)
                apiClient.interceptors.request.use( // axios 인터셉터 : 모든 api 요청을 인터셉트
                    (config) => {
                        console.log('intercepting and adding a token...')
                        config.headers.Authorization = jwtToken

                        return config
                    }
                )

                return true
            }
            else {
                /* setIsAuthenticated(false)
                setUsername(null)
                setToken(null) */
                logout()
                return false
            }
        } catch (error) {
            /*  logout()과 동일한 로직이므로 logout()으로 대체
            setIsAuthenticated(false)
            setUsername(null)
            setToken(null) */
            logout()
            return false
        }
    }



    function logout() {
        setIsAuthenticated(false)
        setUsername(null)
        setToken(null)
    }

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, username, token }}>
            {children}
        </AuthContext.Provider>
    )
}