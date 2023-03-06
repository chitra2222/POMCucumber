
Feature: login functionality for Salesforce
Background:
	Given User open the salesforce application

Scenario: login without password field

	When user in "LoginPage"
	When user enters value in username text box
	When user clicks on Login
	Then verify the error message "Please enter your password."
	
Scenario: login with the correct credentials

	When user in "LoginPage"
	When user enters value in username text box
	When user enters value in password field
	When user clicks on Login
	When user in "HomePage"
	Then verify the Home page title "Home"

Scenario: login with the remember me option

	When user in "LoginPage"
	When user enters value in username text box
	When user enters value in password field
	When user checks remember me
	When user clicks on Login
	When user in "HomePage"
	When user clicks on logout from profile menu
	Then verify "chitra@tekarch.com" is displayed in username field
	
Scenario: click Forgot password

	When user in "LoginPage"
	When user clicks the Forgot password
	Then verify the displayed forgot password message
	
Scenario: login with the wrong username and password

	When user in "LoginPage"
	When user enters wrong value in username text box
	When user enters wrong value in password field
	When user clicks on Login
	Then verify the error message for wrong credentials "Please check your username and password. If you still can't log in, contact your Salesforce administrator."
