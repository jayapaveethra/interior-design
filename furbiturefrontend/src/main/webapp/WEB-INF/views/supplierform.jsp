  
  <%@ include file = "nav.jsp" %>
<body style="counter-reset:section;text-align:center;background:#E7DFDE;">
<div class="contain">
  <h1 style="color:black;">Supplier Form</h1>
  <c:url var="add" value="/admin/addSupplier"/>
  <form:form action="${add}" method="post" id="join-us" style="margin-top:25px; display:inline-block;" modelAttribute="supplier" >
  <br />
    <div class="fields">
     <span>
       <form:input class="inputfields" path="supplierId" placeholder="Supplier ID" type="text" />
    </span>
    <br />
      <span>
       <form:input class="inputfields" path="supplierName" placeholder="Suppier Name" type="text" />
    </span>
    <br />
     <span>
       <form:input class="inputfields" path="emailid" placeholder="Supplier Email Id" type="text" />
    </span>
    <br />
     <span>
       <form:input class="inputfields" path="address" placeholder="Supplier address" type="text" />
    </span>
    </div>
    <br />
    <div class="submit">
      <input style="background:rgb(0,0,0);
  color:#fff;
  position:relative;
  left:9px;
  top:25px; 
  width:100px;
  cursor:pointer;" class="submit" value="Submit" type="submit" >
    </div>
  </form:form>
  <h1>${mess}</h1>
</div>

</body>
<%@ include file="footer.jsp" %>