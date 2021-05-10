import React from "react";
import PropTypes from "prop-types"
import {Link} from "react-router-dom";
import "./PersonEntry.css"
import {Button} from "react-bootstrap";

function PersonEntry({person}) {

    return (
        <div className="person-entry">
            {
                person != null &&
                <>
                    <div className="person-title">
                        <Link to={"/info/" + person.id}>
                            <p className="person-credentials">
                                {
                                    person.firstName
                                    +
                                    ((person.middleName) ? (" " + person.middleName) : "")
                                    +
                                    ((person.lastName) ? (" " + person.lastName) : "")
                                }
                            </p>
                        </Link>
                    </div>

                    <div className="follow-buttons">

                    </div>
                </>
            }
        </div>
    )
}

PersonEntry.propTypes = {
    person: PropTypes.object,
}

export default PersonEntry;