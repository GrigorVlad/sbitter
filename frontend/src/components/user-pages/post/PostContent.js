import React from "react";
import PostWriter from "./PostWriter";
import PostsPanel from "./PostsPanel";

import "./PostPage.css"

    function PostContent({userIds, disableWrite}) {

    return (
        <div className="post-content">
            {
                !disableWrite &&
                <div className="post-writer-panel">
                    <PostWriter/>
                </div>
            }
            <div className="all posts">
                <PostsPanel
                />
            </div>
        </div>
    )
}

export default PostContent