import React from "react";
import NavVerticalTab from "../../nav-tab/NavVerticalTab";
import PostsContent from "./PostsContent";
import {UserContext} from "../../../MainPage";

function PostsPage() {
    const {userAuth} = React.useContext(UserContext);

    return (
        <div className="page-entity">
            <div className="right-nav-column">
                <NavVerticalTab/>
            </div>

            <div className="left-entry-column">
                <div className="entity-page-entity">
                    <PostsContent
                        postsType="followings"
                        userId={userAuth.id}
                        disableWrite={false}
                    />
                </div>
            </div>
        </div>
    )
}

export default PostsPage