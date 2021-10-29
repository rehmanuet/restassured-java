# RestAssured | TestNG | CircleCI | ExtentReport
<sup>Live Build Status:</sup><br />[![CircleCI](https://circleci.com/gh/rehmanuet/restassured-java/tree/main.svg?style=shield)](https://circleci.com/gh/rehmanuet/restassured-java/tree/main)

Introduction:
---------------
This Test Automation Framework is created using Java + RestAssured Library + TestNG which can be used to test APIs. Covered all the mandatory & additional scenarios. I tried to make it S.O.L.I.D compliant and used OOP (Inheritance).

Stack
---------------
<img src="https://brandslogos.com/wp-content/uploads/images/large/java-logo-1.png?raw=true?raw=true" width="120" height="90" /><img src="https://i2.wp.com/www.entrofi.net/wp-content/uploads/2020/01/rest-assured-logo.png?fit=400%2C400&ssl=1?raw=true" width="100" height="100"/><img src="https://images.ctfassets.net/k62me4xboi1l/55FkKC6k4E6I80qOOu2A0M/4b03468aed1c04a639acfa2c513cbcae/angular-sdk-03.svg" width="110" height="100"/><img src="https://www.extentreports.com/wp-content/uploads/2018/09/Extent_logomark_transparentbg.png?raw=true" width="80" height="80" />


Prerequisites:
---------------
*	Java jdk-1.8 or higher
*	Apache Maven 3 or higher
*	Please refer for any help in Maven.
* 	http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
* 	http://www.tutorialspoint.com/maven/maven_environment_setup.htm

Execution
---------------
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

```
git clone git@github.com:rehmanuet/restassured-java.git
mvn clean install
mvn clean test -Dsurefire.suiteXmlFiles=TestNG.xml
```

Coverage
---------------
This framework covers the both positive and negative testcases.

#### Test Cases Breakdown
|    <sub>Endpoint</sub>  |    <sub>Test cases</sub> |
| :-:  | :-: |
|    <b> <sub>/users</sub> </b>   | <sub>5</sub>  |
|    <b> <sub>/posts</sub> </b>   | <sub>4</sub>  |
|    <b> <sub>/comments</sub> </b>   | <sub>5</sub>  
<b><sub>Sub Total Test cases</sub></b>|    <b><sub>14</b></sub> |

Sample Testcase
---------------
Validate if the emails in the comment section for specific user is valid

```
  @Test
    public void tc012_validateEmailForEachComment() {
        ExtentTestManager.startTest(Constants.COMMENTS_ENDPOINT+" EmailForEachComment","Validate the email for each comment");
        ArrayList<String> emails = getEmail(Constants.VALID_USERNAME);
        Pattern ptr = Pattern.compile(Constants.EMAIL_REGEX);
        if (emails.size() == 0) Assert.fail("No Email found");
        for (String email : emails) {
            Assert.assertTrue(ptr.matcher(email).matches());
        }
    }
```

Result
---------------
<img src="https://github.com/rehmanuet/DataEssential/blob/master/junk/Screenshot%202021-10-29%20at%2012.53.06%20PM.png?raw=true" width="600" height="400" />

_contact:`rehmanuet[at]yahoo[dot]com`_ *or* _[LinkedIn](https://www.linkedin.com/in/rehmanuet/)_
