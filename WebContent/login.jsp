<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core" %>  
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html
    xmlns:p="http://primefaces.org/ui" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Log in!</title>
</head>
<body>
<f:view>
	<h:form>
		<h:panelGrid columns="2">
			<h:outputText id="nicknameLabel" value="Login"></h:outputText>
			<h:inputText id="nicknameInput" value="#{loginBean.nickname}" label="nicknameLabel"></h:inputText>
			<h:outputText id="passwordLabel" value="Password"></h:outputText>
			<h:inputSecret id="passwordInput" value="#{loginBean.password}" label="passwordLabel"></h:inputSecret>
		</h:panelGrid>
		<h:commandButton id="loginButton" action="#{loginBean.login}" value="Login"></h:commandButton>
			
	</h:form>
</f:view>
</body>
</html>