export default class URLHelper {

    static getServerURLV1() {
        return "http://localhost:8080/api/v1/";
    }

    static getAuthURL() {
        return this.getServerURLV1() + "auth";
    }

    static getUsersUrl() {
        return this.getServerURLV1() + "users";
    }
}