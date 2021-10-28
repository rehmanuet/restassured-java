import Constants.Constants;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Implementation of the Base Class for the generic methods used for test cases
 *
 * @author Abdur.Rehman
 */

public class UsersValidationTest extends ValidationBaseClass {


    @Test
    public void tc001_checkStatusCodeForUsers() {
        int response_code = getStatusCode(Constants.USERS_ENDPOINT);
        Assert.assertEquals(response_code, 200);
    }

    @Test
    public void tc002_checkIfUserIdExist() {
        int userId = getUser(Constants.VALID_USERNAME);
        Assert.assertNotNull(userId);
    }

    @Test
    public void tc003_searchValidUser() {
        boolean user = getUsername(Constants.VALID_USERNAME);
        Assert.assertTrue(user);
    }

    @Test
    public void tc004_searchInvalidUser() {
        boolean user = getUsername(Constants.INVALID_USERNAME);
        Assert.assertFalse(user);
    }

    @Test
    public void tc005_schemaValidationUsers(){
        RestAssured.get(Constants.BASE_URL+Constants.USERS_ENDPOINT).then().assertThat()
         .body(matchesJsonSchemaInClasspath(Constants.USERS_ENDPOINT.replace("/","")+"_schema.json"));
    }
}

