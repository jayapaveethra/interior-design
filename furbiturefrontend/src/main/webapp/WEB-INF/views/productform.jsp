<%@ include file = "nav.jsp" %>

<body style="counter-reset:section;text-align:center;background:#E7DFDE;">
<div class="contain" style="height:800px;">
  <h1 style="color:black;">Products Form</h1>
  <c:url var="add" value="/admin2/addProducts"/>
  <form:form action="${add}?${_csrf.parameterName}=${_csrf.token}" method="post" modelAttribute="product"  enctype="multipart/form-data" style="margin-top:25px; display:inline-block;" >
  <br />
    <div class="fields">
     <span>
       <form:input class="inputfields" path="ProductId" placeholder="Product ID" type="text" />
    </span>
    <br />
    
      <span>
       <form:input class="inputfields" path="ProductName" placeholder="Product Name" type="text" />
    </span>
    <br />
    
     <span>
       <form:input class="inputfields" path="Price" placeholder="Product Price" type="text" />
    </span>
    <br />
    
     <span>
       <form:input class="inputfields" path="Quantity" placeholder="Product Quantity" type="text" />
    </span>
    <br />
    
     <span>
       <form:input class="inputfields" path="Description" placeholder="Product Description" type="text" />
    </span>
    <br />
  
  <span>
  <form:select path="category.catId" class="inputfields" >
  <option value="NONE" label="--- Select Category---"/>
  <c:forEach var="cat" items="${categories}">
  <option value="${cat.catId}">${cat.catName}</option>
  </c:forEach>
  </form:select>
  </span>
  <br />
  
  <span>
  <form:select path="supplier.supplierId" class="inputfields">
  <option value="NONE" label="--- Select Supplier ---"/>
  <c:forEach var="sup" items="${suppliers}">
  <form:option value="${sup.supplierId}">${sup.supplierName}</form:option>
  </c:forEach>
  </form:select>
  </span>
  <br />
  </div>
  <form:input type="file" path="ping" name="aa" id="aa" value="upload file"/>
    <div class="submit">
      <input style="background:rgb(0,0,0);
  color:#fff;
  position:relative;
  left:9px;
  top:25px; 
  width:100px;
  cursor:pointer;" class="submit" value="Submit" type="submit" />
    </div>
  </form:form>
</div>
</body>

<%@ include file="footer.jsp" %>