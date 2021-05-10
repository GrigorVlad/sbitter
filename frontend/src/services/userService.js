import axios from "axios";
import URLHelper from "../utils/URLHelper";

export const authUserService = {
    login,
    register,
};

export const userService = {
    getUserInfo,
    getUserPosts,
    getUserFollowingsPosts,
    publishUserPost,
    getAllUsers,
    createFollowing,
}

function login(authRqDto, onSuccess, onFail) {
    axios.post(URLHelper.getAuthURL() + "login", authRqDto)
        .then(res => onSuccess(res))
        .catch(err => onFail(err))
}

function register(regRqDto, onSuccess, onFail) {
    axios.post(URLHelper.getAuthURL() + "register", regRqDto)
        .then(res => onSuccess(res))
        .catch(err => onFail(err))
}

function getUserInfo(userId, personId, token, onSuccess, onFail) {
    axios.get(URLHelper.getUsersUrl() + personId + "/info", {
        headers: {"Authorization": token,},
        params: {userId}
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}

function getUserPosts(userId, token, onSuccess, onFail) {
    axios.get(URLHelper.getPostsUrl() + userId + "/own", {
        headers: {"Authorization": token,}
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}

function getUserFollowingsPosts(userId, token, onSuccess, onFail) {
    axios.get(URLHelper.getPostsUrl() + userId + "/followings", {
        headers: {"Authorization": token,}
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}

function publishUserPost(userId, token, post, onSuccess, onFail) {
    axios.post(URLHelper.getPostsUrl() + userId + "/publish", post, {
        headers: {"Authorization": token,}
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}

function getAllUsers(userId, token, onSuccess, onFail) {
    axios.get(URLHelper.getUsersUrl(), {
        headers: {"Authorization": token,},
        params: {userId}
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}

function createFollowing() {

}