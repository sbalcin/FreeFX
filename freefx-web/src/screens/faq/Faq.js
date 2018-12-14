import React from 'react';


import './Faq.css';

export default class AboutUs extends React.Component {


    render() {
        return (
            <div className="faq-main-container">


                <div class="container">
                    <br/>
                    <br/>
                    <br/>

                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.
                    </div>

                    <br/>

                    <div class="accordion" id="accordion">
                        <div class="faqHeader">Mauris nec massa ipsum</div>
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-header">
                                    <a class="accordion-toggle" aria-controls="001" data-toggle="collapse" data-parent="#accordion" href="#001">
                                        Duis vestibulum cursus velit eget faucibus?
                                    </a>
                                </h4>
                            </div>
                            <div id="001" class="panel-collapse collapse in">
                                <div class="faqContent">
                                    Cras ornare ante in erat malesuada sagittis. Integer vitae lacus ex. Pellentesque id risus a justo facilisis ultrices.
                                    <br/>
                                    Curabitur quis sapien pulvinar, volutpat leo et, elementum purus. Mauris nec turpis a mi tempus rutrum.
                                    <br/>
                                    Pellentesque scelerisque lobortis condimentum.
                                    <br/>
                                    Curabitur quis sapien pulvinar, volutpat leo et, elementum purus. Mauris nec turpis a mi tempus rutrum.
                                    <br/>
                                    Cras ornare ante in erat malesuada sagittis. Integer vitae lacus ex. Pellentesque id risus a justo facilisis ultrices.
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-header">
                                    <a class="accordion-toggle" aria-controls="001" data-toggle="collapse" data-parent="#accordion" href="#0011">
                                        Etiam a urna tempus, placerat libero sit amet, dignissim risus?
                                    </a>
                                </h4>
                            </div>
                            <div id="0011" class="panel-collapse collapse in">
                                <div class="faqContent">
                                    Curabitur quis sapien pulvinar, volutpat leo et, elementum purus. Mauris nec turpis a mi tempus rutrum.
                                    <br/>
                                    Pellentesque scelerisque lobortis condimentum.
                                    <br/>
                                    Integer in ornare ante. Aenean sit amet augue bibendum, venenatis nunc quis, porttitor risus. Proin est urna, interdum ut condimentum nec, laoreet at justo. Vivamus at venenatis magna..
                                    <br/>
                                    Cras ornare ante in erat malesuada sagittis. Integer vitae lacus ex. Pellentesque id risus a justo facilisis ultrices.
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-header">
                                    <a class="accordion-toggle" aria-controls="002" data-toggle="collapse" data-parent="#accordion" href="#002">
                                        Pellentesque scelerisque lobortis condimentum?
                                    </a>
                                </h4>
                            </div>
                            <div id="002" class="panel-collapse collapse in">
                                <div class="faqContent">
                                    Integer in ornare ante. Aenean sit amet augue bibendum, venenatis nunc quis, porttitor risus. Proin est urna, interdum ut condimentum nec,
                                    laoreet at justo. Vivamus at venenatis magna.
                                    <br/>
                                    Curabitur quis sapien pulvinar, volutpat leo et, elementum purus. Mauris nec turpis a mi tempus rutrum.
                                    <br/>
                                    Cras ornare ante in erat malesuada sagittis. Integer vitae lacus ex. Pellentesque id risus a justo facilisis ultrices.
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        );
    }
}
