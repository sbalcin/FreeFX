import React, {Component} from "react";

import "./Home.css";
import Responsive from "react-responsive";
import LaddaButton from "react-ladda";
import axios from "axios";
import Popup from 'reactjs-popup'
import {exchangeRateSUri, conversionSUri, conversionHistorySUri, moneyRegex} from "../../config/constants";
import NotificationSystem from "react-notification-system";
import moment from "moment/moment";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import CSSTransitionGroup from "react-transition-group/CSSTransitionGroup";


const Mobile = props => <Responsive {...props} maxWidth={767}/>;
const Default = props => <Responsive {...props} minWidth={768}/>;

const charRegex = /[a-z]/i;
const numRegex = /[0-9]/i;


var notiStyle = {
    NotificationItem: {
        DefaultStyle: {
            margin: '2px 2px 2px 2px'
        }
    }
}


export default class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            popUpOpen: false,
            popUpConfirmButtonLoading: false,
            getQuoteButtonLoading: false,
            exchangeRate: 0,
            exchangeRateError: null,
            conversionHistory: null,
            conversionHistoryLoading: false,
            conversionData: {sourceCurrency: '', sellAmount: '', targetCurrency: ''}
        };

        this.openModal = this.openModal.bind(this)
        this.closeModal = this.closeModal.bind(this)

        this.updateConversionHistory();
    }

    openModal() {
        this.setState({popUpOpen: true})
    }

    closeModal() {
        this.setState({popUpOpen: false})
    }

    componentWillMount() {
    }

    componentDidMount() {

        this._notificationSystem = this.refs.notificationSystem;
        window.conversionAnimate();

        if (window.localStorage.getItem('message')) {
            this._notificationSystem.addNotification({
                message: window.localStorage.getItem('message'),
                level: 'warning'
            });
            window.localStorage.removeItem('message');
        }
    }

    addToNotifications(mess, level) {
        this._notificationSystem.addNotification({message: mess, level: level, position: 'tc'});
    }

    formatPrice(price) {
        price = parseFloat(price).toFixed(2).toString().replace(moneyRegex, ',');
        return price;
    }

    sourceCurrencyChange = (e) => {
        this.state.conversionData.sourceCurrency = e.target.value;
        window.fixMaterializeTextOverlap();
        this.forceUpdate();
    }

    sellAmountChange = (e) => {
        this.state.conversionData.sellAmount = e.target.value;
        window.fixMaterializeTextOverlap();
        this.forceUpdate();
    }

    targetCurrenyChange = (e) => {
        this.state.conversionData.targetCurrency = e.target.value;
        window.fixMaterializeTextOverlap();
        this.forceUpdate();
    }


    getQuoteClicked = (e) => {
        try {
            e.preventDefault();
            this.setState({getQuoteButtonLoading: true})

            if (!this.validateData(this.state.conversionData)) {
                this.addToNotifications('Incorrect input !', 'error');
                setTimeout(() => {
                    this.setState({getQuoteButtonLoading: false})
                }, 100)
                return;
            }

            var data = {
                sourceCurreny: this.state.conversionData.sourceCurrency,
                targetCurreny: this.state.conversionData.targetCurrency
            };
            return this.callExchangeRate(data);
        } catch (err) {
            console.log('buyOrderClicked err.. ' + err);
        }
    }

    validateData(data) {
        if (data.sourceCurrency.length == 0 || data.sellAmount.length == 0 || data.targetCurrency.length == 0)
            return false;

        if (data.sourceCurrency.length > 3 || data.targetCurrency.length > 3) {
            this.addToNotifications('please type symbol of currency', 'error');
            return false;
        }

        if (parseFloat(data.sellAmount) == 0)
            return false;

        if (numRegex.test(data.sourceCurrency) || charRegex.test(data.sellAmount) || numRegex.test(data.targetCurrency))
            return false;
        return true;
    }

    callExchangeRate(data) {
        return axios.post(exchangeRateSUri, data).then(response => {
            if (response.data.rate && parseFloat(response.data.rate) > 0) {
                this.setState({exchangeRate: response.data.rate});
                this.setState({exchangeRateError: null});
                this.openModal();
            } else {
                let message = 'Unexpected Error Occurred !';
                this.addToNotifications(message, 'error');
            }
            setTimeout(() => {
                this.setState({getQuoteButtonLoading: false})
            }, 100);
        }).catch(err => {
            let message = 'Error Occurred: ';
            if (err.response && err.response.data && !err.response.data.error) {
                message += err.response.data;
            }
            if (err.response && err.response.data && err.response.data.error) {
                message += err.response.data.error;
            }
            this.setState({exchangeRateError: message})
            this.openModal();

            setTimeout(() => {
                this.setState({getQuoteButtonLoading: false})
            }, 100)
            throw err;
        })
    }

    conversionConfirmClicked = (e) => {
        e.preventDefault();
        this.setState({popUpConfirmButtonLoading: true})

        var data = {
            sourceAmount: this.state.conversionData.sellAmount,
            sourceCurreny: this.state.conversionData.sourceCurrency,
            targetCurreny: this.state.conversionData.targetCurrency
        };
        return axios.post(conversionSUri, data).then(response => {
            if (response.data.amount && parseFloat(response.data.amount) > 0) {
                this.addToNotifications('Exchange process completed successfully', 'success');
                this.closeModal();
                this.updateConversionHistory();
            } else {
                let message = 'Unexpected Error Occurred !';
                this.addToNotifications(message, 'error');
            }
            setTimeout(() => {
                this.setState({popUpConfirmButtonLoading: false})
            }, 100);
        }).catch(err => {
            let message = 'Error Occurred: ';
            if (err.response && err.response.data && err.response.data.error) {
                message += err.response.data.error;
            }
            this.addToNotifications(message, 'error');
            setTimeout(() => {
                this.setState({popUpConfirmButtonLoading: false})
            }, 100)
            throw err;
        });
    }

    conversionCancelClicked = (e) => {
        this.closeModal();
    }

    updateConversionHistory() {
        var data = {
            transactionStartDate: new Date().getTime() - 86400000
        };
        return axios.post(conversionHistorySUri, data).then(response => {
            if (response.data.conversions) {
                for (var i = 0; i < response.data.conversions.length; i++) {
                    response.data.conversions[i].transactionDate = moment(response.data.conversions[i].transactionDate).fromNow();
                    response.data.conversions[i].targetCurrency = this.formatPrice(response.data.conversions[i].targetCurrency);
                }
                this.setState({conversionHistory: response.data.conversions})
            } else {
                let message = 'Unexpected Error Occurred !';
                this.addToNotifications(message, 'error');
            }
        }).catch(err => {
            let message = 'Error Occurred: ';
            if (err.response && err.response.data && err.response.data.error) {
                message += err.response.data.error;
            }
            this.addToNotifications(message, 'error');
            throw err;
        })
    }

    render() {
        return (
            <div className="home-main-container">
                <NotificationSystem ref="notificationSystem" style={notiStyle} allowHTML={true}/>


                <div className="card mb-3 conversionPanel" id="conversionPanelId">
                    <div className="card-header list-group-item-success tabHeader">
                        Currency Exchange
                    </div>
                    <div className="card-body">
                        <form>
                            <div className="md-form form-sm">
                                <input type="text" className="form-control" id="sourceCurrency"
                                       value={this.state.conversionData.sourceCurrency}
                                       onChange={this.sourceCurrencyChange}/>
                                <label htmlFor="colPriceS">Source Currency</label>
                            </div>
                            <div className="md-form form-sm">
                                <input type="number" className="form-control" id="sellAmount"
                                       value={this.state.conversionData.sellAmount}
                                       onChange={this.sellAmountChange}/>
                                <label htmlFor="colAmountS">Sell Amount</label>
                            </div>
                            <div className="md-form form-sm">
                                <input type="text" className="form-control" id="targetCurreny"
                                       value={this.state.conversionData.targetCurrency}
                                       onChange={this.targetCurrenyChange}/>
                                <label htmlFor="colTotalS">Target Currency</label>
                            </div>
                            <LaddaButton id="getQuotebutton" loading={this.state.getQuoteButtonLoading} onClick={(e) => {
                                this.getQuoteClicked(e)
                            }} className="btn btn-warning btn-rounded btn-block" data-style='zoom-out'>
                                Get Quote
                            </LaddaButton>
                        </form>
                    </div>
                </div>


                <div>
                    <div className="conversionHistory">
                        <div className="ly_conversionHistory">

                            <div className="card" style={{width: '100%'}}>
                                <div className="card-header list-group-item-success tabHeader">Conversion History</div>
                                <div className="card-body cardBody">
                                    <div className="table table-fit">

                                        <BootstrapTable
                                            data={this.state.conversionHistory} striped={true}
                                            pagination>
                                            <TableHeaderColumn width={'30%'} dataField='transactionId' isKey>Transaction Id</TableHeaderColumn>
                                            <TableHeaderColumn width={'14%'} dataField='sellAmount'>Source Amount</TableHeaderColumn>
                                            <TableHeaderColumn width={'14%'} dataField='buyAmount'>Target Amount</TableHeaderColumn>
                                            <TableHeaderColumn width={'14%'} dataField='sellCurrency'>Source Currency</TableHeaderColumn>
                                            <TableHeaderColumn width={'14%'} dataField='buyCurrency'>Target Currency</TableHeaderColumn>
                                            <TableHeaderColumn width={'14%'} dataField='transactionDate'>Transaction Date</TableHeaderColumn>
                                        </BootstrapTable>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <Popup
                    open={this.state.popUpOpen}
                    closeOnDocumentClick
                    onClose={this.closeModal}>

                    <div className="modalFreeFX conversionDetails">
                        <a className="close" onClick={this.closeModal}>
                            &times;
                        </a>
                        <br/>
                        <br/>
                        {
                            this.state.exchangeRateError &&
                            <div className="form-error-container">
                                <label className="form-error-label">{this.state.exchangeRateError}</label>
                            </div>
                        }

                        {
                            !this.state.exchangeRateError &&
                            <div className="card">
                                <div className="card-header list-group-item-success tabHeader">Conversion Details</div>
                                <div className="card-body cardBody">
                                    <div>Your rate is {this.state.exchangeRate}</div>
                                    <div>Would you like to create a transaction?</div>
                                </div>
                                <br/>
                                <br/>

                                <div className="actions">
                                    <LaddaButton id="conversionConfirmBtn" style={{fontSize: 'small'}} loading={this.state.popUpConfirmButtonLoading} onClick={(e) => {
                                        this.conversionConfirmClicked(e)
                                    }} className="btn btn-default waves-effect transaction-tl-button" data-style='zoom-out'>
                                        Yes</LaddaButton>
                                    <LaddaButton style={{fontSize: 'small'}} onClick={(e) => {
                                        this.conversionCancelClicked(e)
                                    }} className="btn btn-default waves-effect transaction-tl-button" data-style='zoom-out'>
                                        No</LaddaButton>
                                </div>
                            </div>
                        }
                        <br/>
                        <br/>
                    </div>
                </Popup>
            </div>
        );
    }
}
