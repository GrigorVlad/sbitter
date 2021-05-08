import React from "react"
import {Button, Col, Form} from "react-bootstrap";
import "./Form.css"
import "./Register.css"
import {authUserService} from "../../services/userService";
import {UserContext} from "../../MainPage";
import {Link} from "react-router-dom";

function Register() {

    const [registerObj, setRegisterObj] = React.useState({});
    const [password, setPassword] = React.useState("");
    const [approvePassword, setApprovePassword] = React.useState("");
    const [error, setError] = React.useState({});

    const {onUserAuthChange} = React.useContext(UserContext);

    const onRegisterObjChange = (event, registerParam) => {
        setRegisterObj({
            ...registerObj,
            [registerParam]: event.target.value,
        });
        setError({
            ...error,
            [registerParam]: null,
        })
    }

    const errorClassName = (inputName) => {
        return error[inputName] ? "is-invalid" : "";
    }

    const onRegisterClick = () => {
        let errors = {...error};
        errors.username = (!registerObj.username) ? "Username must be filled" : null;
        if (password) {
            errors.password = null;
            errors.approvePassword = (password !== approvePassword)
                ? "Approved password doesn't match to password"
                : null;
        } else {
            errors.password = "Password must be field";
        }

        errors.firstName = (!registerObj.firstName) ? "First name must be filled": null;
        if (errors.username || errors.password || errors.approvePassword || errors.firstName) {
            setError(errors);
            return
        }

        let registrationDto = {...registerObj};
        registrationDto.password = password;
        console.log("Register user: ", registrationDto);

        authUserService.register(registrationDto,
            (res) => {
                console.log(res);
                const serverErrors = res.data.messages.errors;
                if (serverErrors.username || serverErrors.email) {
                    setError(serverErrors);
                    return;
                }
                onUserAuthChange(res.data.data);
            },
            (err) => {console.error(err)}
        );
    }

    return (
        <div className="register-form">
            <h1 className="register-title">Register</h1>

            <Form className="register-form" id="register-form-id">
                <Form.Row id="username-row-id">
                    <Col>
                        <Form.Label>Username</Form.Label>
                        <Form.Control id="username-input-id"
                                      size="sm" type="text"
                                      className={errorClassName("username")}
                                      value={registerObj.username}
                                      onChange={(event) => {
                                          onRegisterObjChange(event, "username")
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
                                      value={password}
                                      onChange={(event) => {
                                          setPassword(event.target.value);
                                          setError({
                                              ...error,
                                              password: null,
                                          });
                                      }}
                        />
                        <Form.Control.Feedback type="invalid">{error.password}</Form.Control.Feedback>
                    </Col>
                </Form.Row>

                <Form.Row id="approve-password-row-id">
                    <Col>
                        <Form.Label>Approve password</Form.Label>
                        <Form.Control id="password-input-id"
                                      size="sm" type="text"
                                      className={errorClassName("approvePassword")}
                                      value={approvePassword}
                                      onChange={(event) => {
                                          setApprovePassword(event.target.value);
                                          setError({
                                              ...error,
                                              approvePassword: null,
                                          });
                                      }}
                        />
                        <Form.Control.Feedback type="invalid">{error.approvePassword}</Form.Control.Feedback>
                    </Col>
                </Form.Row>

                <Form.Row id="first-name-row-id">
                    <Col>
                        <Form.Label>First name</Form.Label>
                        <Form.Control id="first-name-input-id"
                                      size="sm" type="text"
                                      className={errorClassName("firstName")}
                                      value={registerObj.firstName}
                                      onChange={(event) => {
                                          onRegisterObjChange(event, "firstName")
                                      }}
                        />
                        <Form.Control.Feedback type="invalid">{error.firstName}</Form.Control.Feedback>
                    </Col>
                </Form.Row>

                <Form.Row id="middle-name-row-id">
                    <Col>
                        <Form.Label>Middle name</Form.Label>
                        <Form.Control id="middle-name-input-id"
                                      size="sm" type="text"
                                      className={errorClassName("middleName")}
                                      value={registerObj.middleName}
                                      onChange={(event) => {
                                          onRegisterObjChange(event, "middleName")
                                      }}
                        />
                        <Form.Control.Feedback type="invalid">{error.middleName}</Form.Control.Feedback>
                    </Col>
                </Form.Row>

                <Form.Row id="last-name-row-id">
                    <Col>
                        <Form.Label>Last name</Form.Label>
                        <Form.Control id="last-name-input-id"
                                      size="sm" type="text"
                                      className={errorClassName("lastName")}
                                      value={registerObj.lastName}
                                      onChange={(event) => {
                                          onRegisterObjChange(event, "lastName")
                                      }}
                        />
                        <Form.Control.Feedback type="invalid">{error.lastName}</Form.Control.Feedback>
                    </Col>
                </Form.Row>

                <Form.Row id="email-row-id">
                    <Col>
                        <Form.Label>Email</Form.Label>
                        <Form.Control id="email-input-id"
                                      size="sm" type="text"
                                      className={errorClassName("email")}
                                      value={registerObj.email}
                                      onChange={(event) => {
                                          onRegisterObjChange(event, "email")
                                      }}
                        />
                        <Form.Control.Feedback type="invalid">{error.email}</Form.Control.Feedback>
                    </Col>
                </Form.Row>
            </Form>

            <div className="register-buttons-group">
                <Link to="/login">
                    <Button id="back-to-log-in-btn-id"
                            variant="secondary" size="sm"
                    >
                        Back
                    </Button>
                </Link>

                <Button id="register-btn-id"
                        variant="success" size="sm"
                        onClick={() => onRegisterClick()}
                >
                    Register
                </Button>
            </div>
        </div>

    )
}

export default Register;