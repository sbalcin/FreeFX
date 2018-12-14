import React from "react";
import ReactDOM from "react-dom";
import configureStore from "./store/configureStore";
import {Provider} from "react-redux";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Header from "./components/header/Header";
import Home from "./screens/home/Home";
import ContactUs from './screens/contactUs/ContactUs';
import AboutUs from './screens/aboutUs/AboutUs';
import UserPolicy from './screens/userPolicy/UserPolicy';
import Faq from './screens/faq/Faq';
import NoMatch from './screens/noMatch/NoMatch';
import Footer from "./components/footer/Footer";

const store = configureStore();

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <div>
                <Header/>
                <Switch>
                    <Route exact path="/" component={Home}/>
                    <Route path="/user-policy" component={UserPolicy}/>
                    <Route path="/faq" component={Faq}/>
                    <Route path="/contact-us" component={ContactUs}/>
                    <Route path="/about-us" component={AboutUs}/>
                    <Route component={NoMatch}/>
                </Switch>
                <Footer/>
            </div>
        </Router>
    </Provider>,
    document.getElementById('root')
);

