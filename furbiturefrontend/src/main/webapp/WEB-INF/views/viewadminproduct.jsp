<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
  
<h1>Products List</h1> <br/>
 <c:url var="add" value="/admin/product"/>
  <form:form method = "GET" action = "${add}">
<table border="2" width="70%" cellpadding="2" modelAttribute="productss" >  
<tr>
<th>Id</th>
<th>Name</th>
<th>Price</th>
<th>Quantity</th>
<th>Description</th>
<th>Edit</th>
<th>Delete</th>
</tr>  
   <c:forEach var="prod" items="${products}">   
   <tr>  
   <td>${prod.productId}</td>  
   <td>${prod.productName}</td>  
   <td>${prod.price}</td>
   <td>${prod.quantity}</td>
   <td>${prod.description}</td>
   <td><a href="<c:url value="/admin/editproducts/${prod.getProductId()}"/>">Edit</a></td>  
   <td><a href="<c:url value="/admin/deleteproducts/${prod.getProductId()}"/>">Delete</a></td>  
   </tr>  
   </c:forEach> 
   <br/>
   <br/>
   </table> 
      <input type = "submit" value = "Add Products"/> 
   </form:form>  
   <br/>