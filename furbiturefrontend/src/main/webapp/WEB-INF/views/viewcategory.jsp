   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
   <c:url var="add" value="/admin/category"/>
<h1>Category List</h1> <br/>
 <form:form method = "GET" action = "${add}">
<table border="2" width="70%" cellpadding="2" modelAttribute="categories" >  
<tr><th>Id</th><th>Name</th><th>Edit</th><th>Delete</th></tr>  
   <c:forEach var="cat" items="${categories}">   
   <tr>  
   <td>${cat.catId}</td>  
   <td>${cat.catName}</td>  
   <td><a href="<c:url value="/admin/editCategory/${cat.getCatId()}"/>">Edit</a></td>  
   <td><a href ="<c:url value="/admin/deleteCategory/${cat.getCatId()}"/>">Delete</a></td>  
   </tr>  
   </c:forEach>  
     
    <br/>
   <br/>
   </table> 
      <input type = "submit" value = "Add Categories"/> 
   </form:form>   
    
