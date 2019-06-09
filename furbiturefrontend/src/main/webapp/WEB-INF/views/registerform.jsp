 <%@ include file = "nav.jsp" %>
<body style="counter-reset:section;text-align:center;background:#E7DFDE;">
<div class="contain">
  <h1 style="color:black;">Register Form</h1>
 <c:url var="add" value="/addUsers"/>
  <form:form action="${add}" method="post" id="join-us" style="margin-top:25px; display:inline-block;" modelAttribute="users" >
   <br />
    <div class="fields">
     <span>
       <form:input class="inputfields" path="UserId" placeholder="UserId" type="text" />
    </span>
    <br />
    <span>
       <form:input class="inputfields" path="UserName" placeholder="UserName" type="text" />
    </span>
    <br />
     <span>
       <form:input class="inputfields" path="UserAddress" placeholder="UserAddress" type="text" />
    </span>
    <br />
	<span>
       <form:input class="inputfields" path="password" placeholder="password" type="password" />
    </span>
	<br />
	<span>
       <form:input class="inputfields" path="emailid" placeholder="emailid" type="text" />
    </span>
    <br />
    <span>
       <form:input class="inputfields" path="phoneno" placeholder="phoneno" type="text" />
    </span>
    <br />
   <span>
         <form:input class="inputfields"  path="billing.billCity" placeholder="Enter Your City" type="text" />
         </span>
         <br />
      <span>
         <form:input class="inputfields" path="billing.billState" placeholder="Enter Your State" type="text" />
         </span>
         <br />
         <span>
         <form:input class="inputfields" path="billing.billAddress" placeholder="Enter your Address" type="text" />
         </span>
         <br />
            
            
             
        
	</div>
	
    <br />
    <div class="submit">
	<input style="background:rgb(0,0,0);
  color:#fff;
  position:relative;
  left:9px;
  top:25px; 
  width:100px;
  cursor:pointer;" class="inoutfields" value="Submit" type="submit" >
    </div>
  </form:form>
  <h1>${mess}</h1>
</div>

</body>
<%@ include file="footer.jsp" %>