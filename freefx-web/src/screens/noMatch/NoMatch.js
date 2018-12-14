import React from "react";

import "./NoMatch.css";

export default class NoMatch extends React.Component {

    componentDidMount() {
    }

    render() {
        return (
            <div className="no-match-container">
                <div id="particles-js" className="particles" style={{    height: 'calc(100vh - 170px)'}}></div>
                <div className="no-match-404-container">
                  <span className="no-match-404-title">Page not found</span>
                    <br/><br/>
                  <span className="no-match-404-detail" style={{marginTop: 8}}>Integer in ornare ante. Aenean sit amet augue bibendum, venenatis nunc quis, porttitor risus.</span>
                    <br/>
                  <span className="no-match-404-detail">Proin est urna, interdum ut condimentum nec, laoreet at justo. Vivamus at venenatis magna.</span>
                </div>
            </div>
        )
    }
}
