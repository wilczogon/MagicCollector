<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core" %>  
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Search</title>
</head>
<body>
	<f:view>
		<h:form>
			<h:outputText value="Search by name:"></h:outputText><h:inputText value="#{searchBean.pattern}"></h:inputText><br/>
			<h:commandButton value="Search" action="#{searchBean.search}"></h:commandButton><br/>
			<br/>
			<h:dataTable rendered="#{searchBean.resultAvailable && searchBean.patternAdded}" var="singleCardData" value="#{searchBean.cardData}" border="1">
				<f:facet name="header"><h:outputText value="Search results" /></f:facet>
    			<h:column>
    				<f:facet name="header"><h:outputText value="Card Name" /></f:facet>
        			<h:outputText value="#{singleCardData.card.name}"></h:outputText>
    			</h:column>
 
    			<h:column>
    				<f:facet name="header"><h:outputText value="Amount" /></f:facet>
        			<h:outputText value="#{singleCardData.amount}"/>
    			</h:column>
    			
    			<h:column>
    				<f:facet name="header"><h:outputText value="User nickname" /></f:facet>
        			<h:outputText value="#{singleCardData.user.nickname}"/>
    			</h:column>

			</h:dataTable>
			
			<h:outputText rendered="#{!searchBean.resultAvailable && searchBean.patternAdded}" value="No matching cards found."></h:outputText>
			<br/>
			<br/>
			<h:commandButton value="Return" action="MyCards"></h:commandButton>
		</h:form>
	</f:view>
</body>
</html>