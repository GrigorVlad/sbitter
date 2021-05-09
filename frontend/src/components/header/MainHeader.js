import React from "react"
import {UserContext} from "../../MainPage";
import sberLogo from "../../sber.jpg"
import {Link} from "react-router-dom";
import {Button} from "react-bootstrap";
import "./MainHeader.css"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

function MainHeader() {
    const {userAuth, onUserAuthChange} = React.useContext(UserContext);

    return (
        <div className="main-header">
            <div className="sber-logo">
                <img
                    src={sberLogo}
                    alt="Sber logo"
                    width={150}
                    height={50}
                />
            </div>

            <div className="log-out">
                {
                    (userAuth != null) &&
                     <Link to="/login">
                        <Button
                            id="log-out-btn-id"
                            variant="secondary"
                            className="log-out-btn"
                            size="sm"
                            onClick={() => onUserAuthChange(null)}
                        >
                            <span>
                                <FontAwesomeIcon icon={"sign-out-alt"}/>
                            </span>
                            Log out
                        </Button>
                     </Link>
                }
            </div>
        </div>
    )
}

export default MainHeader;