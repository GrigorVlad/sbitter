import React from "react"
import {UserContext} from "../../../MainPage";
import NavVerticalTab from "../../nav-tab/NavVerticalTab";
import "../CommonStyles.css"
import "./UserInfoPage.css"
import {userService} from "../../../services/userService";
import UserInfoForm from "./UserInfoForm";
import {Button} from "react-bootstrap";
import PostsContent from "../post/PostsContent";

function UserInfoPage(props) {
    const {userAuth, onUserAuthChange} = React.useContext(UserContext);
    const userPageId = parseInt(props.match.params.id);
    console.debug("RENDER UserInfoPage. UserAuth:", userAuth, "UserPageId:", userPageId);

    const [userOnPage, setUserOnPage] = React.useState(null);

    React.useEffect(() => {
        userService.getUserInfo(userAuth.id, userPageId, userAuth.token,
            (res) => {
                console.debug("User request res:", res);
                setUserOnPage(res.data);
            },
            (err) => {
                console.error(err)
            })
    }, [userAuth, userPageId])

    return (
        <div className="page-entity">
            <div className="right-nav-column">
                <NavVerticalTab/>
            </div>

            <div className="left-entry-column">
                <div className="entity-page-entity">
                    <>
                        <div className="user-info-content">
                            <div className="info-buttons">
                                {
                                    (userOnPage && userOnPage.following === false) &&
                                    <Button id="follow-btn-id"
                                            variant="secondary"
                                            className="user-info-btn follow-btn"
                                            size="sm"
                                    >
                                        Follow
                                    </Button>
                                }

                                {
                                    userPageId === userAuth.id &&
                                    <Button id="edit-btn-id"
                                            variant="secondary"
                                            className="user-info-btn edit-btn"
                                            size="sm"
                                            disabled={true}
                                    >
                                        Edit
                                    </Button>
                                }
                            </div>

                            <UserInfoForm
                                userInfo={userOnPage}
                                editMode={false} //first iteration without edit
                            />
                        </div>

                        <PostsContent
                            postsType="own"
                            userId={userPageId}
                            disableWrite={userPageId !== userAuth.id}
                        />
                    </>
                </div>
            </div>
        </div>
    )

}

export default UserInfoPage;