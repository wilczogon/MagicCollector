<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core" %>  
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Details</title>
</head>
<body>

<f:view>
<h:form>
	<h:outputText value="#{detailsBean.cardName}"></h:outputText><br/>
	<br/>
	<h:graphicImage value="http://api.mtgdb.info/content/card_images/#{detailsBean.cardId}.jpeg"></h:graphicImage><br/>
	<br/>
	<h:commandButton value="Return" action="MyCards"></h:commandButton>
</h:form>
</f:view>

</body>
</html>