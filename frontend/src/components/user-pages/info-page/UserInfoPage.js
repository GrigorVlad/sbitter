import React from "react"
import {UserContext} from "../../../MainPage";
import NavVerticalTab from "../../nav-tab/NavVerticalTab";
import "../CommonStyles.css"
import "./UserInfoPage.css"
import {userInfoService} from "../../../services/userService";

function UserInfoPage(props) {
    const {userAuth} = React.useContext(UserContext);
    const userPageId = props.match.params.id;
    console.debug("RENDER UserInfoPage. UserAuth:", userAuth, "UserPageId:", userPageId);

    React.useEffect(() => {
        userInfoService.askUserInfo(userAuth.id, userPageId, userAuth.token,
            (res) => {console.debug("User request res:", res)},
            (err) => {console.error(err)})
    }, [userAuth, userPageId])

    return (
        <div className="page-entity">
            <div className="right-nav-column">
                <NavVerticalTab/>
            </div>

            <div className="left-entry-column">
                <div className="entity-page-entity">
                    <div className="user-info-page-entity">
                        <p>{"Here will be UserPage with id: " + userAuth.id}</p>
                    </div>
                </div>
            </div>
        </div>
    )

}

export default UserInfoPage;