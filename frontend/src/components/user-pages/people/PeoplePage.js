import React from "react";
import NavVerticalTab from "../../nav-tab/NavVerticalTab";
import "./PeoplePage.css"
import {UserContext} from "../../../MainPage";
import {userService} from "../../../services/userService";
import PersonEntry from "./PersonEntry";

function PeoplePage() {
    const {userAuth, onUserAuthChange} = React.useContext(UserContext);
    const[people, setPeople] = React.useState([]);

    React.useEffect(() => {
        userService.getAllUsers(userAuth.id, userAuth.token,
            (res) => {
                console.debug("All users request result", res);
                setPeople(res.data)
            },
            (err) => console.error(err));
    }, []);


    return (
        <div className="page-entity">
            <div className="right-nav-column">
                <NavVerticalTab/>
            </div>

            <div className="left-entry-column">
                <div className="entity-page-entity">
                    <div className="people-entity">
                        {
                            people.map(person => (
                                <PersonEntry
                                    key={person.id}
                                    person={person}
                                />
                            ))
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PeoplePage;