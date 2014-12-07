<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" %>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core" %>  
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>My Cards</title>
</head>
<body>
<f:view>
	<h:form>
		<h:commandButton value="Add Card" action="AddCard"></h:commandButton>
		<h:commandButton value="Search Cards" action="Search"></h:commandButton>
		<h:commandButton value="Log out" action="Login"></h:commandButton><br/>
		<br/>
		<h:dataTable rendered="#{!myCardsBean.cardDataEmpty}" var="singleCardData" value="#{myCardsBean.cardData}" border="1">
			<f:facet name="header"><h:outputText value="Your Magic Cards" /></f:facet>
    		<h:column>
    			<f:facet name="header"><h:outputText value="Card Name" /></f:facet>
        		<h:commandLink value="#{singleCardData.card.name}" action="Details">
        			<f:setPropertyActionListener target="#{detailsBean.cardId}" value="#{singleCardData.card.id}" />
        		</h:commandLink>
    		</h:column>
 
    		<h:column>
    			<f:facet name="header"><h:outputText value="Additional Info" /></f:facet>
        		<h:outputText value="#{singleCardData.additionalInfo}"/>
    		</h:column>
 
    		<h:column>
    			<f:facet name="header"><h:outputText value="Amount (All)" /></f:facet>
        		<h:outputText value="#{singleCardData.amount}"/>
        		<h:commandButton value="+" action="#{myCardsBean.addCardAmountAll}">
        			<f:setPropertyActionListener target="#{myCardsBean.activeCardId}" value="#{singleCardData.card.id}" />
        		</h:commandButton>
        		<h:commandButton value="-" action="#{myCardsBean.subtractCardAmountAll}">
        			<f:setPropertyActionListener target="#{myCardsBean.activeCardId}" value="#{singleCardData.card.id}" />
        		</h:commandButton>
    		</h:column>
    		
    		<h:column>
    			<f:facet name="header"><h:outputText value="Amount (Public)" /></f:facet>
        		<h:outputText value="#{singleCardData.amountPublic}"/>
        		<h:commandButton value="+" action="#{myCardsBean.addCardAmountPublic}">
        			<f:setPropertyActionListener target="#{myCardsBean.activeCardId}" value="#{singleCardData.card.id}" />
        		</h:commandButton>
        		<h:commandButton value="-" action="#{myCardsBean.subtractCardAmountPublic}">
        			<f:setPropertyActionListener target="#{myCardsBean.activeCardId}" value="#{singleCardData.card.id}" />
        		</h:commandButton>
    		</h:column>
		</h:dataTable>
		
		<h:outputText rendered="#{myCardsBean.cardDataEmpty}" value="You have not added single card yet."></h:outputText>
	</h:form>
	
</f:view>
</body>
</html>