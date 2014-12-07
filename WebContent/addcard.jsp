<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core" %>  
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Add Card</title>
</head>
<body>
	<f:view>
		<h:form>
			<h:outputText value="Find your card:"></h:outputText><br/>
			<h:inputText value="#{addCardBean.pattern}"></h:inputText>
			<h:commandButton value="Search" action="#{addCardBean.search}"></h:commandButton><br/>
			<br/>
			<h:outputText rendered="#{addCardBean.addedPattern}" value="Searching cards for '#{addCardBean.pattern}'"></h:outputText><br/>
			
			<h:panelGroup rendered="#{addCardBean.addedPattern && addCardBean.matching}">
				<h:selectOneMenu value="#{addCardBean.chosenCardId}" required="true" >
					<f:selectItems value="#{addCardBean.matchingCards}"/>
				</h:selectOneMenu>
				
				<h:panelGrid columns="2">
					<h:outputText value="Amount (All)"></h:outputText><h:inputText value="#{addCardBean.amountAll}"></h:inputText>
					<h:outputText value="Amount (Public)"></h:outputText><h:inputText value="#{addCardBean.amountPublic}"></h:inputText>
				</h:panelGrid><br/>
				
				<h:commandButton value="Add" action="#{addCardBean.add}"></h:commandButton>
			</h:panelGroup>
			
			<h:outputText rendered="#{addCardBean.addedPattern && !addCardBean.matching}"
				value="No matching cards found."></h:outputText><br/>
			<br/>
			<h:commandButton value="Cancel" action="MyCards"></h:commandButton>
		</h:form>
	</f:view>
</body>
</html>