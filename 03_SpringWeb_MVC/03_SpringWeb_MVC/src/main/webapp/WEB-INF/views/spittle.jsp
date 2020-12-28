<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <%@ page isELIgnored="false" %>
    <title>Spitter</title>

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