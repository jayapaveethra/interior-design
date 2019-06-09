<%@ include file = "nav.jsp" %>
        <!-- Header Area End -->

        
        <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-title">
                                
                              <li><a href="<c:url value="/checkout"/>">Checkout</a></li>
                              
                            </div>
                            <c:url var="add" value="/placeorder" />
                            <form:form method="post" action="${add}"  class="checkout" modelAttribute="shipping" >
                                <div class="row">
                                   
                                
                                    <div class="col-md-6 mb-3">
                                        <form:input type="hidden" path="ShipId" class="form-control" id="ShipId"  />
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <form:input type="text" path="ShipName" class="form-control" id="ShipName" placeholder="ShipName" />
                                    </div>
                                    <div class="col-12 mb-3">
                                        <form:input type="text" class="form-control" path="ShipState" id="ShipState" placeholder="ShipState"  />
                                    </div>
                                    <div class="col-12 mb-3">
                                        <form:input type="email" class="form-control" path="email" id="email" placeholder="email" />
                                    </div>
                                    <div class="col-12 mb-3">
                                        <select class="w-100" id="ShipCountry" path="ShipCountry">
                                        <option value="usa">United States</option>
                                        <option value="uk">United Kingdom</option>
                                        <option value="ger">Germany</option>
                                        <option value="fra">France</option>
                                        <option value="ind">India</option>
                                        <option value="aus">Australia</option>
                                        <option value="bra">Brazil</option>
                                        <option value="cana">Canada</option>
                                    </select>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <form:input type="text" class="form-control mb-3" path="ShipAddress" id="ShipAddress" placeholder="ShipAddress" />
                                    </div>
                                    <div class="col-12 mb-3">
                                        <form:input type="text" class="form-control" path="ShipCity" id="ShipCity" placeholder="ShipCity"  />
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <form:input type="text" class="form-control"  path="ShipZipCode" id=" ShipZipCode" placeholder=" ShipZipCode" />
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <form:input type="number" class="form-control" path="PhoneNo" id="PhoneNo" min="0" placeholder="PhoneNo" />
                                    </div>
                
                                </div>
                                 <div class="col-12 col-lg-4">
                        <div class="cart-summary">
                            <h5>Cart Total</h5>
                            <ul class="summary-table">         
                                <li><span>Grand total:</span> <span>${orders.getGrandTotal()}</span></li>
                            </ul>

                            <div class="payment-method">
                                <!-- Cash on delivery -->
                                <div class="custom-control custom-checkbox mr-sm-2">
                                    <input type="radio" id="payment-method" name="payment-method" value="cash">
                                    <label class="custom-control-label" for="cod">Cash on Delivery</label>
                                </div>
                                <!-- Paypal -->
                                <div class="custom-control custom-checkbox mr-sm-2">
                                    <input type="radio" id="payment-method" name="payment-method" value="card">
                                    <label class="custom-control-label" for="paypal">Card <img class="ml-15" src="img/core-img/paypal.png" alt=""></label>
                                </div>
                            </div>

                            <div class="cart-btn mt-100">
                                <button class="btn amado-btn w-100">Checkout</button>
                            </div>
                        </div>
                    </div>
                                </form:form>
                            
                        </div>
                    </div>
                                    
                                    
                                </div>
                        </div>
                    </div>
                   
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Main Content Wrapper End ##### -->
    <%@ include file = "footer.jsp" %>

    