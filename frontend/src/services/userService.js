import axios from "axios";
import URLHelper from "../utils/URLHelper";

export const authUserService = {
    login,
    register,
};

export const userInfoService = {
    getUserInfo,
    getUserPosts,
    getUserFollowingsPosts,
    publishUserPost,
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
    axios.get(URLHelper.getUsersUrl() + userId + "/info/" + personId, {
        headers: {
            "Authorization": token,
        }
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}

function getUserPosts(userId, token, onSuccess, onFail) {
    axios.get(URLHelper.getPostsUrl() + userId + "/own", {
        headers: {
            "Authorization": token,
        }
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}

function getUserFollowingsPosts(userId, token, onSuccess, onFail) {
    axios.get(URLHelper.getPostsUrl() + userId + "/followings", {
        headers: {
            "Authorization": token,
        }
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}

function publishUserPost(userId, token, post, onSuccess, onFail) {
    axios.post(URLHelper.getPostsUrl() + userId + "/publish", post, {
        headers: {
            "Authorization": token,
        }
    }).then(res => onSuccess(res))
        .catch(err => onFail(err));
}