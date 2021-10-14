# AmdocsPortal
Yoy have to create testng.xml file like this one below:

EXAMPLE:

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="My test suite">
     <parameter name = "userName" value="YOUR_USER"/>
     <parameter name = "userP" value="PASSWORD"/>
    
	<test name="testing">
		<classes>
		   
		   <class name="tests.AmdocsPortalTest">
				<methods>
				
				<include name="searchAllField"/>
				
														
				</methods>
			</class>
	
		</classes>
	</test>
</suite>
