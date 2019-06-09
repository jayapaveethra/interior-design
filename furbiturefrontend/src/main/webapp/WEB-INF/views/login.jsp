 <%@ include file = "nav.jsp" %>
 <body style="counter-reset:section;text-align:center;background:#E7DFDE;">
<div class="contain">
  <h1 style="color:black;">login Form</h1>
  ${mlog }
 <c:url var="login" value="/j_spring_security_check"/>
 
 
  <form:form action="${login}" method="post" id="join-us" style="margin-top:25px; display:inline-block;"  >
   <br />
    <div class="fields">
     <span>
       <input class="inputfields" name="j_username" placeholder="emailId" type="text"  />
    </span>
    <br />
    <span>
       <input class="inputfields" name="j_password" placeholder="password" type="password"  />
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
  cursor:pointer;" class="submit" value="login" type="submit" >
    </div>
  </form:form>
  
</div>

</body>
<%@ include file="footer.jsp" %>