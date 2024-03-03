import { useEffect, useState } from "react"
import { retrieveAllTodosForUsername } from "./api/TodoRestApiService"

export default function TodoList() {

    const today = new Date()
    const targetDate = new Date(today.getFullYear() + 12, today.getMonth(), today.getDay())

    const [todos, setTodos] = useState([]) // 기본 상태는 빈 문자열

    useEffect( // 렌더링 시마다 refreshTodos() 실행하도록
        () => refreshTodos(), []
    )

    function refreshTodos() {
        retrieveAllTodosForUsername('kim')
            .then(response => setTodos(response.data))
            .catch((error) => console.log(error))
            .finally(console.log('clean up'))
    }

    return (
        <div className="container">
            <h1>this is Todolist</h1>
            <div>
                <table className="table">
                    <thead>
                        <td>ID</td>
                        <td>DESCRIPTION</td>
                        <td>TARGET DATE</td>
                        <td>DONE</td>
                    </thead>
                    <tbody>
                        {
                            todos.map(
                                todo => (
                                    <tr key={todo.id}>
                                        <td>{todo.id}</td>
                                        <td>{todo.description}</td>
                                        {/* <td>{todo.targetDate.toDateString()}</td> */}
                                        <td>{todo.targetDate.toString()}</td>
                                        <td>{todo.done.toString()}</td>
                                    </tr>
                                )
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}