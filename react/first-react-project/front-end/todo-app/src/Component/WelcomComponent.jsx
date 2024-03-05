import { useState } from "react";
import { Link, useParams } from "react-router-dom";
import { retrieveHelloWorldBean, retrieveHelloWorldPathVariable } from "./api/HelloWorldApiService";

export default function WelcomeComponent() {

    const { username } = useParams();
    const [message, setMessage] = useState(null)

    function callHelloWorldRestApi() {
        retrieveHelloWorldPathVariable(username)
            .then((response) => successfulMessage(response))
            .catch(error => errorResponse(error))
            .finally(() => console.log('clean up'))
    }

    function successfulMessage(response) {
        console.log(response)
        setMessage(response.data.message)
    }

    function errorResponse(error) {
        console.log(error)
    }

    return (
        <div>
            <h1> Hello, {`${username}`}! Welcome this Page</h1>
            <div>GO TO your <Link to="/todos">To do</Link></div>
            <button className="btn btn-success" onClick={callHelloWorldRestApi}>CALL HelloWorld API</button>
            <div>{message}</div>
        </div>
    )
}