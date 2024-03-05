import { useState } from "react"
import { useNavigate } from "react-router-dom";
import { useAuth } from "./security/AuthContext"

export default function LoginComponent() {

    // 로그인 검사
    // function validateLogin(username, password) {

    // }

    const [username, setUsername] = useState("kim");
    const [password, setPassword] = useState("");
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const navigate = useNavigate();

    // Context
    const authContext = useAuth();

    function handleUsernameChange(event) {
        setUsername(event.target.value)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    // 제출 시
    function handleSubmit() {
        // 유저네임, 패스워드 일치하는 지 검증하는 컴포넌트 필요
        if (authContext.login(username, password)) {
            setShowErrorMessage(false);
            navigate(`/welcome/${username}`);
        }
        else {
            setShowErrorMessage(true);
        }
    }
    return (
        <div className="Login">
            <h1>Time to Login !</h1>
            {showErrorMessage && <div className="errorMessage">Try Again. Please check your credentials</div>}
            <div lassName="LoginForm">
                <div>
                    <label>username</label>
                    <input type="text" id="username" name="username" value={username} onChange={handleUsernameChange} />
                </div>
                <div>
                    <label>password</label>
                    <input type="password" id="password" name="password" value={password} onChange={handlePasswordChange} />
                </div>
                <button type="button" name="login" onClick={handleSubmit}>Login</button>
                {/* 폼이 아니니까 onClick으로 */}
            </div>
        </div>
    )
}