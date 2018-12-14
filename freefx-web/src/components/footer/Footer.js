import React from 'react';
import './Footer.css';

class Footer extends React.Component {

    render() {
        return (
            <div className="footer">
                <footer id="myFooter">
                    <div className="container">
                        <div className="row">
                            <div className="col-sm-3 myCols">
                                <a href="/about-us"><h5>Privacy Policy</h5></a>
                            </div>
                            <div className="col-sm-3 myCols">
                                <a href="/user-policy"><h5>Terms of Use</h5></a>
                            </div>
                            <div className="col-sm-3 myCols">
                                <a href="/user-policy"><h5>Legal</h5></a>
                            </div>
                            <div className="col-sm-3 myCols">
                                <a href="/faq"><h5>FAQ</h5></a>
                            </div>
                        </div>
                    </div>

                    <div className="social-networks">
                        <a href="https://twitter.com/freefx" target="_blank" className="twitter"><i className="fa fa-twitter font-twitter"></i></a>
                        <a href="https://www.facebook.com/freefx" target="_blank" className="facebook"><i className="fa fa-facebook-official font-facebook"></i></a>
                    </div>

                    <div className="footer-copyright">
                        <p>Freefx © 2018</p>
                    </div>
                </footer>
            </div>
        );
    }
}

export default Footer;
