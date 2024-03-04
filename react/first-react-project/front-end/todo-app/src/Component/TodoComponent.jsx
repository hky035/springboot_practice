import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { useAuth } from "./security/AuthContext";
import { retrieveTodoApi } from "./api/TodoRestApiService";
import { Field, Formik, Form, ErrorMessage } from "formik";

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
                    // console.log("response : ", response)
                    setDescription(response.data.description)
                    setTargetDate(response.data.targetDate)

                    // console.log(description) // 현재는 한 렌더링 안의 상태이다. 따라서 description을 출력하면 초기값 ''이 나온다.

                }
            )
    }

    function onSubmit() {
        console.log('btn clicked')
    }

    function validate(values) {
        let errors = {
            //description : 'Enter valid description'
            //targetDate : 'Enter valid targetDate'
            // 다음과 같이 errors 객체의 멤버들의 값을 미리 할당해주면 자동적으로 errors 메시지가 정해졌다고 인식되어 <ErrorComponent>가 실행
        }

        if (values.description.length < 5) {
            errors.description = 'Enter at least 5 characters'
        }
        if (values.targetDate == null) {
            errors.targetDate = 'Enter a target date'
        }
        // console.log('valited')
        console.log(values) // values에 description과 targetDate 입력값이 들어간 객체임을 알 수 있다.
        return errors
    }
    return (
        <div className="container">
            <h1>Enter Todo Page</h1>

            <Formik
                initialValues={{ description, targetDate }}
                enableReinitialize={true}
                onSubmit={onSubmit}
                validate={validate}
                validateOnChange={false}
                validateOnBlur={false}
            >
                {
                    (props) => (
                        <Form>
                            <ErrorMessage
                                name="description"
                                component="div"
                                className="alert alert-warning"
                            />
                            <ErrorMessage
                                name="targetDate"
                                component="div"
                                className="alert alert-warning"
                            />
                            <fieldset className="form-group">
                                <label>Description</label>
                                <Field type="text" className="form-control" name="description" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>targetDate</label>
                                <Field type="date" className="form-control" name="targetDate" />
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