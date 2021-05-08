import React from "react"
import {BrowserRouter} from "react-router-dom";
import SbitterRouter from "./components/router/SbitterRouter";
import MainHeader from "./components/header/MainHeader";
import "./MainPage.css"

export const UserContext = React.createContext({
    userAuth: null,
    onUserAuthChange: () => {},
});

function MainPage() {
    const [userAuth, setUserAuth] = React.useState(null);

    console.log("RENDER MainPage. UserAuth:", userAuth);
    return (
        <div className="main-page">
            <UserContext.Provider value={{
                userAuth: userAuth,
                onUserAuthChange: (newUserAuth) => {setUserAuth(newUserAuth)}
            }}>
                <BrowserRouter>
                    <MainHeader/>
                    <SbitterRouter/>
                </BrowserRouter>
            </UserContext.Provider>
        </div>
    );
}

export default MainPage;
