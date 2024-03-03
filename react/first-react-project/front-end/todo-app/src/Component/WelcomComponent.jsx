import { Link, useParams } from "react-router-dom";

export default function WelcomeComponent() {

    const { username } = useParams();

    return (
        <div>
            <h1> Hello, {`${username}`}! Welcome this Page</h1>
            <div>GO TO your <Link to="/todos">To do</Link></div>
        </div>
    )
}