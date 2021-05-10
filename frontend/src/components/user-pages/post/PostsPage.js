import React from "react";
import NavVerticalTab from "../../nav-tab/NavVerticalTab";
import PostContent from "./PostContent";

function PostsPage() {


    return (
        <div className="page-entity">
            <div className="right-nav-column">
                <NavVerticalTab/>
            </div>

            <div className="left-entry-column">
                <div className="entity-page-entity">
                    <PostContent
                        userIds={[]}
                        disableWrite={false}
                    />
                </div>
            </div>
        </div>
    )
}

export default PostsPage