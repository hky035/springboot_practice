import { createContext, useContext, useState } from "react"
import { excuteBasicAuthenticationService } from "../api/HelloWorldApiService"

export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)

export default function AuthProvider({ children }) {

    const [isAuthenticated, setIsAuthenticated] = useState(false)
    const [username, setUsername] = useState('')

    const [token, setToken] = useState(null)

    async function login(username, password) {
        const baToken = 'basic ' + window.btoa(username + ":" + password)

        try {
            const response = await excuteBasicAuthenticationService(baToken)

            if (response.status == 200) {
                setIsAuthenticated(true)
                setUsername(username)
                setToken(baToken)
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