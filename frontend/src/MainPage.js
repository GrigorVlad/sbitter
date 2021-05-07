import React from "react"
import sberLogo from "./sber.jpg"

function MainPage() {
  return (
      <>
        <h1> Sbitter frontend application</h1>
        <img src={sberLogo} className="sber-logo" alt="sber-logo"/>
      </>

  );
}

export default MainPage;
