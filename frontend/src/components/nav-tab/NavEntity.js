import React from "react"
import {Link} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Button} from "react-bootstrap";
import "./NavTabs.css"

function NavEntity({navLink, navIcon, navText}) {

    return (
        <div className="nav-entity">
            <Link to={navLink}>
                <Button size="sm" className="nav-button">
                    <span className="icon">
                        <FontAwesomeIcon icon={navIcon}/>
                    </span>
                    <span className="text">{navText}</span>
                </Button>
            </Link>
        </div>
    )
}

export default NavEntity;