import axios from "axios";
import URLHelper from "../utils/URLHelper";

export const authUserService = {
    login,
    register,
};

function login(authRqDto, onSuccess, onFail) {
    axios.post(URLHelper.getAuthURL() + "/login", authRqDto)
        .then(res => onSuccess(res))
        .catch(err => onFail(err))
}

function register(regRqDto, onSuccess, onFail) {
    axios.post(URLHelper.getAuthURL() + "/register", regRqDto)
        .then(res => onSuccess(res))
        .catch(err => onFail(err))
}