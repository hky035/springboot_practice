import { useEffect, useState } from "react"
import { deleteTodoApi, retrieveAllTodosForUsernameApi } from "./api/TodoRestApiService"
import { useAuth } from "./security/AuthContext"
import { useNavigate } from "react-router-dom"

export default function TodoList() {

    const [todos, setTodos] = useState([]) // 기본 상태는 빈 문자열
    const [message, setMessage] = useState('')

    const authContext = useAuth()
    const username = authContext.username

    const navigate = useNavigate()

    useEffect( // 렌더링 시마다 refreshTodos() 실행하도록
        () => refreshTodos(), []
    )

    function refreshTodos() {
        console.log(username)
        retrieveAllTodosForUsernameApi(username)
            .then(response => setTodos(response.data))
            .catch((error) => console.log(error))
            .finally(console.log('clean up'))
    }

    function deleteTodo(id) {
        deleteTodoApi(username, id)
            .then( // 1.삭제 메시지 출력  2.삭제 후 Todo리스트 업데이트
                setMessage(`Deleted Todo(id : ${id})`),
                () => refreshTodos()
            )
    }

    function updateTodo(id) {
        navigate(`/todo/${id}`)
    }

    function addTodo() {
        navigate('/todo/-1')
    }

    return (
        <div className="container">
            <h1>this is Todolist</h1>
            {message && <div className="alert alert-warning">{message}</div>}
            <div>
                <table className="table">
                    <thead>
                        <th>ID</th>
                        <th>DESCRIPTION</th>
                        <th>TARGET DATE</th>
                        <th>DONE</th>
                        <th>DELETE</th>
                        <th>UPDATE</th>
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
                                        <td> <button className="btn btn-warning" onClick={() => deleteTodo(todo.id)}>DELETE</button></td>
                                        <td> <button className="btn btn-success" onClick={() => updateTodo(todo.id)}>UPDATE</button></td>
                                    </tr>
                                )
                            )
                        }
                    </tbody>
                </table>
                <div>
                    <button className="btn btn-success" onClick={addTodo}>ADD TODO</button>
                </div>
            </div>
        </div>
    )
}