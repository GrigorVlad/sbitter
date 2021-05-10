import React from "react";
import PropTypes from "prop-types"
import {Link} from "react-router-dom";
import {objUtils} from "../../../utils/ObjUtils";
import "./PostEntry.css"

function PostEntry({post}) {
    const author = objUtils.getValue(post, "author");
    return (
        <div className="post-entry">
            {
                (post != null) &&
                <>
                    <div className="author-title">
                        <Link to={"/info/" + author.id}>
                            <p className="author-credentials">
                                {
                                    author.firstName
                                    +
                                    ((author.middleName) ? (" " + author.middleName) : "")
                                    +
                                    ((author.lastName) ? (" " + author.lastName) : "")
                                }
                            </p>
                        </Link>
                        <div>
                            <p className="post-created">
                                {new Date(post.created).toLocaleDateString()}
                            </p>
                        </div>
                    </div>
                    <div className="post-text">
                        <p className="post-text">
                            {post.text}
                        </p>
                    </div>
                </>

            }
        </div>

    )
}

PostEntry.propTypes = {
    post: PropTypes.object,
}

export default PostEntry;