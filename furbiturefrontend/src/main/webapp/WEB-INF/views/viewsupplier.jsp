<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
   <c:url var="add" value="/admin/supplier"/>
<h1>Supplier List</h1> <br/>
  <form:form method = "GET" action = "${add}">
<table border="2" width="70%" cellpadding="2" modelAttribute="suppliers" >  
<tr>
<th>Id</th>
<th>Name</th>
<th>EmailId</th>
<th>Address</th>
<th>Edit</th>
<th>Delete</th>
</tr>  
   <c:forEach var="sup" items="${suppliers}">   
   <tr>  
   <td>${sup.supplierId}</td>  
   <td>${sup.supplierName}</td>  
   <td>${sup.emailid}</td>
   <td>${sup.address}</td>
   <td><a href="<c:url value="/admin/editsupplier/${sup.getSupplierId()}"/>">Edit</a></td>  
   <td><a href="<c:url value="/admin/deletesupplier/${sup.getSupplierId()}"/>">Delete</a></td>  
   </tr>  
   </c:forEach> 
   <br/>
   <br/>
   </table> 
      <input type = "submit" value = "Add Suppliers"/> 
   </form:form>  
   <br/>  
