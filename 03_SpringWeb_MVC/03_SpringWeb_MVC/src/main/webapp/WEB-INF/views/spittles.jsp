<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<!-- l oggetto spittleList viene ritornato dal controller SpittleController (metodo spittles) -->
<div>
  <h1>Recent Spittles</h1>
  <ul>
    <c:forEach items="${spittleList}" var="spittle" >
      <li id="spittle_<c:out value="spittle.id"/>">
        <div><c:out value="${spittle.message}" /></div>
        <div>
          <span><c:out value="${spittle.time}" /></span>
          <span>(<c:out value="${spittle.latitude}" />, <c:out value="${spittle.longitude}" />)</span>
        </div>
      </li>
    </c:forEach>
  </ul>
</div>