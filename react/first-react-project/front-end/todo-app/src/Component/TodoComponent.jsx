import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { useAuth } from "./security/AuthContext";
import { retrieveTodoApi } from "./api/TodoRestApiService";
import { Field, Formik, Form } from "formik";

export default function TodoComponent() {

    const { id } = useParams()
    const authContext = useAuth();
    const username = authContext.username;
    const [description, setDescription] = useState('')
    const [targetDate, setTargetDate] = useState('')

    useEffect(
        () => retrieveTodo(), [id]
    )

    function retrieveTodo() {
        retrieveTodoApi(username, id)
            .then(
                (response) => {
                    console.log("response : ", response)
                    setDescription(response.data.description)
                    setTargetDate(response.data.targetDate)

                    // console.log(description) // 현재는 한 렌더링 안의 상태이다. 따라서 description을 출력하면 초기값 ''이 나온다.

                }
            )
    }

    function onSubmit() {
        console.log('btn clicked')
    }

    return (
        <div className="container">
            <h1>Enter Todo Page</h1>

            <Formik>
                {
                    (props) => (
                        <Form>
                            <fieldset className="form-group">
                                <label>Description</label>
                                <Field type="text" className="form-control" name="description" value={description} />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>targetDate</label>
                                <Field type="date" className="form-control" name="targetDate" value={targetDate} />
                            </fieldset>
                            <div>
                                <button className="btn btn-success">SAVE</button>
                            </div>
                        </Form>
                    )
                }
            </Formik>
        </div>
    )
}