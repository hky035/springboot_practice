import { createContext, useContext, useState } from "react"
import { excuteBasicAuthenticationService } from "../api/HelloWorldApiService"

export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)

export default function AuthProvider({ children }) {

    const [isAuthenticated, setIsAuthenticated] = useState(false)
    const [username, setUsername] = useState('')

    function login(username, password) {
        const baToken = 'basic ' + window.btoa(username + ":" + password)

        excuteBasicAuthenticationService(baToken)
            .then(response => console.log(response))
            .catch(error => console.log(error))

        setIsAuthenticated(false)

        // if (username === "kim" && password === "this") {
        //     setIsAuthenticated(true)
        //     setUsername(username)
        //     return true;
        // }
        // else {
        //     setIsAuthenticated(false)
        //     setUsername('')
        //     return false;
        // }
    }

    function logout() {
        setIsAuthenticated(false)
        setUsername('')
    }

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, username }}>
            {children}
        </AuthContext.Provider>
    )
}