import React from "react"
import {UserContext} from "../../MainPage";
import {BrowserRouter, Switch, Route, Redirect} from "react-router-dom";
import Login from "../auth-forms/Login";
import Register from "../auth-forms/Register";

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
                <Switch>
                    {/*<Route path="/:id/main-page" component={(props) =>*/}
                    {/*    <*/}
                    {/*}/>*/}
                </Switch>
            }
        </BrowserRouter>
    )
}

export default SbitterRouter;