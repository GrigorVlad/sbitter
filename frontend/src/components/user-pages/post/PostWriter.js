import React from "react";
import PropTypes from "prop-types"
import {Form, Button} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

function PostWriter({postCount, onPublishPost}) {

    const [postEntity, setPostEntity] = React.useState(null);

    console.debug("PostWriter.render", postCount);
    return (
        <div className="post-writer-form">
            <Form>
                <Form.Control id="new-post-write-input-id"
                              className="new-post-write-input"
                              size="sm" type="text"
                              as="textarea" rows="2"
                              placeholder="Write new post..."
                              value={postEntity}
                              onChange={event => setPostEntity(event.target.value)}
                />
            </Form>

            <div className="publish-buttons">
                <Button id="publish-btn-id"
                        variant="success"
                        className="publish-btn"
                        size="sm"
                        onClick={() => {
                            onPublishPost(postEntity);
                            setPostEntity("");
                        }}
                >
                    <span>
                        <FontAwesomeIcon icon={"upload"}/>
                    </span>
                    Publish
                </Button>
            </div>
        </div>
    )
}

PostWriter.propTypes = {
    postCount: PropTypes.number,
    onPublishPost: PropTypes.func.isRequired,
}

export default PostWriter