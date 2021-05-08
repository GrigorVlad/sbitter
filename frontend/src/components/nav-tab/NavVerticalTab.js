import React from "react"
import NavEntity from "./NavEntity";

function NavVerticalTab() {


    return (
        <div className="nav-vertical-tab">
            <NavEntity
                navLink={"/home"}
                navIcon={"home"}
                navText={"My Page"}
            />

            <NavEntity
                navLink={"/followers"}
                navIcon={"users"}
                navText={"Followers"}
            />

            <NavEntity
                navLink={"/followings"}
                navIcon={"user-friends"}
                navText={"Followings"}
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