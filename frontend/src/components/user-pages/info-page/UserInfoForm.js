import React from "react";
import {Col, Form} from "react-bootstrap";
import {objUtils} from "../../../utils/ObjUtils";
import "./UserInfoPage.css"

function UserInfoForm({userInfo, editMode}) {
    const labelColumnSm = 4;
    const [userInfoOnForm, setUserInfoOnForm] = React.useState(userInfo);
    React.useEffect(() => {
        setUserInfoOnForm(userInfo);
    }, [userInfo, editMode])

    const onUserInfoParamChange = (event, paramId) => {
        setUserInfoOnForm({
            ...userInfoOnForm,
            [paramId]: event.target.value,
        })
    }

    return (
        <Form className="user-info-form">
            <Form.Row>
                <Col sm={labelColumnSm}>
                    <Form.Label column="sm">First name:</Form.Label>
                </Col>
                <Col>
                    <Form.Control id="user-info-form-first-name-input-id"
                                  size="sm" type="text"
                                  disabled={!editMode}
                                  value={objUtils.getValue(userInfoOnForm, "firstName")}
                                  onChange={(event) => {
                                      onUserInfoParamChange(event, "firstName")
                                  }}
                    />
                </Col>
            </Form.Row>
            <Form.Row>
                <Col sm={labelColumnSm}>
                    <Form.Label column="sm">Middle name:</Form.Label>
                </Col>
                <Col>
                    <Form.Control id="user-info-form-middle-name-input-id"
                                  size="sm" type="text"
                                  disabled={!editMode}
                                  value={objUtils.getValue(userInfoOnForm, "middleName")}
                                  onChange={(event) => {
                                      onUserInfoParamChange(event, "middleName")
                                  }}
                    />
                </Col>
            </Form.Row>

            <Form.Row>
                <Col sm={labelColumnSm}>
                    <Form.Label column="sm">Last name:</Form.Label>
                </Col>
                <Col>
                    <Form.Control id="user-info-form-last-name-input-id"
                                  size="sm" type="text"
                                  disabled={!editMode}
                                  value={objUtils.getValue(userInfoOnForm, "lastName")}
                                  onChange={(event) => {
                                      onUserInfoParamChange(event, "lastName")
                                  }}
                    />
                </Col>
            </Form.Row>

            <Form.Row>
                <Col sm={labelColumnSm}>
                    <Form.Label column="sm">E-mail:</Form.Label>
                </Col>
                <Col>
                    <Form.Control id="user-info-form-email-input-id"
                                  size="sm" type="text"
                                  disabled={!editMode}
                                  value={objUtils.getValue(userInfoOnForm, "email")}
                                  onChange={(event) => {
                                      onUserInfoParamChange(event, "email")
                                  }}
                    />
                </Col>
            </Form.Row>
        </Form>
    )
}

export default UserInfoForm;