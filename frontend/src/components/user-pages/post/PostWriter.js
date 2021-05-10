import React from "react";
import PropTypes from "prop-types"
import {Form, Button} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

function PostWriter({onPublishPost}) {

    const [postEntity, setPostEntity] = React.useState(null);

    return (
        <div className="post-writer-form">
            <Form>
                <Form.Control id="new-post-write-input-id"
                              className="new-post-write-input"
                              size="sm" type="text"
                              as="textarea" rows="3"
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
                        onClick={() => {onPublishPost(postEntity)}}
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
    onPublishPost: PropTypes.func.isRequired,
}

export default PostWriter