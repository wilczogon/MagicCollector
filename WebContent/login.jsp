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
<script src="https://apis.google.com/js/client:platform.js" async defer></script>
</head>
<body>
<script>
	function signinCallback(authResult) {
	  if (authResult['status']['signed_in']) {
	    // Update the app to reflect a signed in user
	    // Hide the sign-in button now that the user is authorized, for example:
	    document.getElementById('signinButton').setAttribute('style', 'display: none');
	  } else {
	    // Update the app to reflect a signed out user
	    // Possible error values:
	    //   "user_signed_out" - User is signed-out
	    //   "access_denied" - User denied access to your app
	    //   "immediate_failed" - Could not automatically log in the user
	    console.log('Sign-in state: ' + authResult['error']);
	  }
	}

</script>
<f:view>
	<h:form>
			<h:outputText id="nicknameLabel" value="Login"></h:outputText>
			<h:inputText id="nicknameInput" value="#{loginBean.nickname}" label="nicknameLabel"></h:inputText>
			<h:commandButton id="loginButton" action="#{loginBean.login}" value="Login"></h:commandButton>
			<span id="signinButton">
  				<span
    				class="g-signin"
    				data-callback="signinCallback"
    				data-clientid="771245024748-8fmjif6q7f8fkcftnnqbuq428389pkl0.apps.googleusercontent.com"
    				data-cookiepolicy="single_host_origin"
    				data-scope="profile">
  				</span>
			</span>
			
	</h:form>
</f:view>
</body>
</html>