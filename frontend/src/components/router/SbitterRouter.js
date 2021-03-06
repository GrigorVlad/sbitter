import React from "react"
import {UserContext} from "../../MainPage";
import {BrowserRouter, Switch, Route, Redirect} from "react-router-dom";
import Login from "../auth-forms/Login";
import Register from "../auth-forms/Register";
import UserInfoPage from "../user-pages/info-page/UserInfoPage";
import PeoplePage from "../user-pages/people/PeoplePage";
import PostsPage from "../user-pages/post/PostsPage";

function SbitterRouter() {
    const {userAuth} = React.useContext(UserContext);

    console.debug("RENDER SbitterRouter. UserAuth:", userAuth);
    return (
        <BrowserRouter>
            {
                (userAuth == null) &&
                <Switch>
                    <Route path="/login" component={Login}/>
                    <Route path="/register" component={Register}/>
                    <Redirect to="/login"/>
                </Switch>
            }
            {
                (userAuth != null) &&
                <>
                    <Switch>
                        <Route path="/info/:id" component={(props) => (
                            <UserInfoPage
                                {...props}
                            />
                        )}/>
                        <Route path="/people" component={PeoplePage}/>
                        <Route path="/posts" component={PostsPage}/>
                        <Redirect to={"/info/" + userAuth.id}/>
                    </Switch>
                </>
            }
        </BrowserRouter>
    )
}

export default SbitterRouter;