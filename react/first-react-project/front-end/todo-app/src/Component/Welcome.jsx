import { Link } from "react-router-dom"

export default function welcome() {
    return (
        <div>
            <h1>Welcome to this Page !</h1>
            Manage Todo - <Link to={"/todos"}>Go To ToDO</Link> // 리다이렉션
        </div>
    )
}