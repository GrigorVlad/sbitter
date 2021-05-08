export default class URLHelper {

    static getServerURL() {
        return "http://localhost:8080/api/";
    }

    static getAuthURL() {
        return this.getServerURL() + "auth";
    }

    static getUsersUrl() {
        return this.getServerURL() + "users";
    }
}