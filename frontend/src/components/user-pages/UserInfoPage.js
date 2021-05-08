import React from "react"
import {UserContext} from "../../MainPage";
import NavVerticalTab from "../nav-tab/NavVerticalTab";
import "./CommonStyles.css"

function UserInfoPage() {
    const {userAuth} = React.useContext(UserContext);
    console.debug("RENDER UserInfoPage. UserAuth:", userAuth);

    return (
        <div className="page-entity">
            <div className="right-nav-column">
                <NavVerticalTab/>
            </div>

            <div className="left-entry-column">
                <p>Here will be user info</p>
            </div>
        </div>
    )

}

export default UserInfoPage;