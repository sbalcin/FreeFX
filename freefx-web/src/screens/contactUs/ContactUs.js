import React from 'react';


import './ContactUs.css';
import LaddaButton from "react-ladda";

export default class ContactUs extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount = () => {
        window.showGoogleMaps();
    }

    render() {
        return (
            <div className="contact-us-main-container">
                <section className="">

                    <p className="section-description pb-4">Integer in ornare ante. Aenean sit amet augue bibendum, venenatis nunc quis, porttitor risus.</p>

                    <div className="row">

                        <div className="col-lg-5 mb-4">

                            <div className="card">

                                <div className="card-body">
                                    <form>
                                        <div className="form-header contact-us-main-color">
                                            <h3 style={{color: 'white'}}><i className="fa fa-envelope" style={{color: 'white'}}></i> Request a call back</h3>
                                        </div>


                                        <div className="md-form">
                                            <i className="fa fa-user prefix grey-text"></i>
                                            <input type="text"></input>
                                            <label for="form-name">Name</label>
                                        </div>

                                        <div className="md-form">
                                            <i className="fa fa-envelope prefix grey-text"></i>
                                            <input type="text"></input>
                                            <label for="form-email">Email</label>
                                        </div>

                                        <div className="md-form">
                                            <i className="fa fa-tag prefix grey-text"></i>
                                            <input type="text"></input>
                                            <label for="form-Subject">Subject</label>
                                        </div>

                                        <div className="md-form">
                                            <i className="fa fa-pencil prefix grey-text"></i>
                                            <textarea type="text"></textarea>
                                            <label for="form-text">Message</label>
                                        </div>

                                        <div className="text-center">
                                            <LaddaButton style={{fontSize: 'large'}} className="btn btn-lg contact-us-main-color" data-style='zoom-out'>Send</LaddaButton>
                                        </div>
                                    </form>
                                </div>

                            </div>

                        </div>

                        <div className="col-lg-7">

                            <div id="map-container-freefx" className="z-depth-1-half map-container" style={{height: '400px'}}></div>

                            <br/>
                            <div className="row text-center">
                                <div className="col-md-6">
                                    <a className="btn-floating contact-us-main-color"><i className="fa fa-map-marker"></i></a>
                                    <p>Lorem Ipsum Sit Amet  Dummy Pin Dummy place</p>
                                    <p>Dummy /Address</p>
                                </div>

                                <div className="col-md-6">
                                    <a className="btn-floating contact-us-main-color"><i className="fa fa-envelope"></i></a>
                                    <p>support@freefx.com</p>
                                    <p>info@freefx.com</p>
                                </div>
                            </div>

                        </div>

                    </div>

                </section>
            </div>
        );
    }
}
