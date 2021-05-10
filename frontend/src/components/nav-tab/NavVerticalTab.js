import React from "react"
import NavEntity from "./NavEntity";

function NavVerticalTab() {
    return (
        <div className="nav-vertical-tab">
            <NavEntity
                navLink={"/info"}
                navIcon={"home"}
                navText={"My Page"}
            />

            <NavEntity
                navLink={"/people"}
                navIcon={"users"}
                navText={"People"}
            />

            <NavEntity
                navLink={"/posts"}
                navIcon={"newspaper"}
                navText={"Posts"}
            />
        </div>
    )

}

export default NavVerticalTab;