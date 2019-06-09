<%@ include file="nav.jsp" %>

<body style="counter-reset:section;text-align:center;background:#E7DFDE;">
<div class="contain" style="height:800px;">

 <c:url var="add" value="/addCard"/>
<form:form action="${add}" method="post" modelAttribute="card">

<form:input type="hidden" path="cardId" />

<label>NAME ON CARD</label>
<form:input type="text" placeholder="NAME ON CARD" path="nameonCard"/>
</br>

<label>CARD NUMBER</label>
<form:input type="text" placeholder="CARD NUMBER" path="cardNumber"/>
</br>

<label>EXPIRY DATE</label>
<form:input type="text" placeholder="MM/YY" path="expiryDate"/>

</br>

<label>CVV</label>
<form:input type="text" placeholder="CVV" path="cvv"/>
</br>

<input type="submit" value="Submit"/>

</form:form>

</div>
</body>
<%@ include file="footer.jsp" %>