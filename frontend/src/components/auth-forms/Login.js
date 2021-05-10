import React from "react";
import {Form, Col, Button} from "react-bootstrap";
import {Link} from "react-router-dom";
import {authUserService} from "../../services/userService";
import {UserContext} from "../../MainPage";
import "./Form.css"
import "./Login.css"

function Login() {

    const [credentials, setCredentials] = React.useState({});
    const [error, setError] = React.useState({});

    const {onUserAuthChange} = React.useContext(UserContext);

    const onCredentialsChange = (event, credentialsName) => {
        setCredentials({
            ...credentials,
            [credentialsName]: event.target.value
        });
        setError({
            ...error,
            [credentialsName]: null,
        })
    }

    const errorClassName = (inputName) => {
        return error[inputName] ? "is-invalid" : "";
    }

    const onSignInClick = () => {
        const errors = {...error};
        errors.username = (!credentials.username) ? "Username must be filled" : null;
        errors.password = (!credentials.password) ? "Password must be filled" : null;

        if (errors.username || errors.password) {
            setError(errors);
            return;
        }

        console.debug("Sending credentials:", credentials);
        authUserService.login(credentials,
            (res) => {
                console.debug(res);
                console.debug(res.data);
                onUserAuthChange(res.data);
            },
            (err) => {
                if (err && err.response && err.response.status === 403) {
                    setError({
                        username: "User with this username is not exist"
                    })
                } else {
                    console.error(err)
                }
            }
        );
    }

    return (
        <div className="log-in-form">
            <h1 className="log-in-title">Login</h1>

            <Form className="log-in-form" id="log-in-form-id">
                <Form.Row id="username-row-id">
                    <Col>
                        <Form.Label>Username</Form.Label>
                        <Form.Control id="username-input-id"
                                      size="sm" type="text"
                                      className={errorClassName("username")}
                                      value={credentials.username}
                                      onChange={(event) => {
                                          onCredentialsChange(event, "username")
                                      }}
                        />
                        <Form.Control.Feedback type="invalid">{error.username}</Form.Control.Feedback>
                    </Col>
                </Form.Row>

                <Form.Row id="password-row-id">
                    <Col>
                        <Form.Label>Password</Form.Label>
                        <Form.Control id="password-input-id"
                                      size="sm" type="text"
                                      className={errorClassName("password")}
                                      value={credentials.password}
                                      onChange={(event) => {
                                          onCredentialsChange(event, "password")
                                      }}
                        />
                        <Form.Control.Feedback type="invalid">{error.password}</Form.Control.Feedback>
                    </Col>
                </Form.Row>
            </Form>

            <div className="sing-buttons-group">
                <Link to="/register">
                    <Button id="sign-up-btn-id"
                            variant="secondary"
                            size="sm"
                    >
                        Sign up
                    </Button>
                </Link>
                <Button id="sign-in-btn-id"
                        variant="success"
                        size="sm"
                        onClick={() => onSignInClick()}
                >
                    Sign in
                </Button>
            </div>
        </div>
    )
}

export default Login;