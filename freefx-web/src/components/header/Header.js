import React from "react";

import "./Header.css";
import Responsive from "react-responsive";
import Footer from "../footer/Footer";


const Mobile = props => <Responsive {...props} maxWidth={767}/>;
const Default = props => <Responsive {...props} minWidth={768}/>;

class Header extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount = () => {
    }

    componentWillUpdate = () => {
    }

    render() {

        return (
            <nav className="navbar fixed-top navbar-dark navbar-expand-lg navigation-container">

                <Default>
                    <a href="/" className="navbar-brand navigation-logo"><img src={require("../../assets/images/logo/logo.png")}
                                                                              style={{height: '24px', marginTop: '-4px', marginLeft: '80px'}}/></a>
                </Default>
                <Mobile>
                    <a href="/" className="navbar-brand"><img src={require("../../assets/images/logo/logo.png")}
                                                              style={{height: '24px', marginTop: '-10px'}}/></a>
                </Mobile>

                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation"><span className="navbar-toggler-icon"></span></button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item"><a className="nav-link" href="/">FreeFX</a></li>
                        <li className="nav-item"><a className="nav-link" href="/about-us">About Us</a></li>
                        <li className="nav-item"><a className="nav-link" href="/about-us">Services</a></li>
                        <li className="nav-item"><a className="nav-link" href="/contact-us">Contact</a></li>
                    </ul>
                    <Default>

                        <ul className="navbar-nav justify-content-end navigation-item-right">
                            <li className="nav-item"><a className="nav-link">Login</a></li>
                            <li className="nav-item"><a className="nav-link">Sign Up</a></li>
                        </ul>
                    </Default>
                </div>

            </nav>
        )
    }
}

export default Header;


