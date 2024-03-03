export default function TodoList() {

    const today = new Date()
    const targetDate = new Date(today.getFullYear() + 12, today.getMonth(), today.getDay())
    const todos = [
        { id: 1, description: "Learn Java", done: false, targetDate: targetDate },
        { id: 2, description: "Learn Spring", done: false, targetDate: targetDate },
        { id: 3, description: "Learn DevOps", done: false, targetDate: targetDate }
    ]

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
                                        <td>{todo.targetDate.toDateString()}</td>
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