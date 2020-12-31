<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
  <head>
    <title>Spittle</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <!-- l oggetto spittle viene ritornato dal controller SpittleController (metodo spittle) -->
    <div class="spittleView">
        <div class="spittleMessage"><c:out value="${spittle.message}" /></div>
        <div>
            <span class="spittleTime"><c:out value="${spittle.time}" /></span>
        </div>
    </div>
   </body>
</html>