Feature: Login Action

Scenario: User Authentication
	Given User is on LoginPage
	When User enters UserName and wrong Password
	Then Message should display Authentication failed
	When User enters UserName and correct Password
	Then User should be redirected to the landing page