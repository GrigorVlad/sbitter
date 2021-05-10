import React from "react";
import PropTypes from "prop-types"
import PostWriter from "./PostWriter";

import "./PostPage.css"
import {userService} from "../../../services/userService";
import {UserContext} from "../../../MainPage";
import PostEntry from "./PostEntry";

function PostsContent({postsType, userId, disableWrite}) {
    const {userAuth, onUserAuthChange} = React.useContext(UserContext);
    const [posts, setPosts] = React.useState([]);

    React.useEffect(() => {
        if (postsType === "own") {
            userService.getUserPosts(userId, userAuth.token,
                (res) => setPosts(res.data),
                (err) => console.error(err));
        } else if (postsType === "followings") {
            userService.getUserFollowingsPosts(userId, userAuth.token,
                (res) => setPosts(res.data),
                (err) => console.error(err));
        }
    }, [postsType, userId, disableWrite]);

    const onPostPublish = (postText) => {
        let post = {
            text: postText,
        }
        console.debug("Publishing post:", postText);
        userService.publishUserPost(userAuth.id, userAuth.token, post,
            (res) => {
                console.log("Result of post publish:", res);
                let newPosts = [res.data].concat(posts);
                setPosts(newPosts);
            },
            (err) => console.error(err))
    }

    return (
        <div className="post-content">
            {
                !disableWrite &&
                <div className="post-writer-panel">
                    <PostWriter
                        postCount={posts.length}
                        onPublishPost={(postText) => onPostPublish(postText)}
                    />
                </div>
            }
            <div className="all-posts">
                {
                    posts.map(post => (
                        <PostEntry
                            key={post.id}
                            post={post}
                        />
                    ))
                }
            </div>
        </div>
    )
}

PostsContent.propTypes = {
    postsType: PropTypes.string.isRequired,
    userId: PropTypes.number.isRequired,
    disableWrite: PropTypes.bool.isRequired,
}

export default PostsContent