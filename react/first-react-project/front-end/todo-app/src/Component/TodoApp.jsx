import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import HeaderComponent from "./boilerplate/HeaderComponent";
import FooterComponent from "./boilerplate/FooterComponent";
import LoginComponent from "./LoginComponent";
import WelcomeComponent from "./WelcomComponent";
import ErrorComponent from "./ErrorComponent";
import TodoList from "./TodoList";
import AuthProvider from "./security/AuthContext";
import LogoutComponent from "./boilerplate/LogoutComponent";
import { useAuth } from "./security/AuthContext";
import TodoComponent from "./TodoComponent";

function AuthenticatedRoute({ children }) {
    const authContext = useAuth()
    if (authContext.isAuthenticated)
        return children
    else
        return <Navigate to="/" />
}

export default function TodoApp() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <HeaderComponent />
                <Routes>
                    <Route path="/" element={<LoginComponent />} ></Route>
                    <Route path="/login" element={<LoginComponent />} />
                    <Route path="/logout" element={
                        <AuthenticatedRoute>
                            <LogoutComponent />
                        </AuthenticatedRoute>
                    } />
                    <Route path="/welcome/:username" element={
                        <AuthenticatedRoute>
                            <WelcomeComponent />
                        </AuthenticatedRoute>
                    } />
                    <Route path="/todos" element={
                        <AuthenticatedRoute>
                            <TodoList />
                        </AuthenticatedRoute>
                    } />
                    <Route path="/todo/:id" element={
                        <AuthenticatedRoute>
                            <TodoComponent />
                        </AuthenticatedRoute>
                    } />
                    <Route path="*" element={<ErrorComponent />}></Route>
                </Routes>
                <FooterComponent />
            </BrowserRouter>
        </AuthProvider>
    )
}