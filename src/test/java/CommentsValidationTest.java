import Constants.Constants;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.regex.Pattern;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CommentsValidationTest extends ValidationBaseClass {

    @Test
    public void tc010_checkStatusCodeForUsers() {
        int response_code = getStatusCode(Constants.USERS_ENDPOINT);
        System.out.printf("Endpoint: %s, Status Code: %s\n", Constants.USERS_ENDPOINT, response_code);
        Assert.assertEquals(response_code, 200);
    }

    @Test
    public void tc011_checkCommentsForEachPost() {
        ArrayList<Integer> posts = getPosts(getUser(Constants.VALID_USERNAME));
        for (int post : posts) {
            List<HashMap<String, Object>> comments = getComments(post);
            Assert.assertNotNull(comments);
        }
    }

    @Test
    public void tc012_validateEmailForEachComment() {
        ArrayList<String> emails = getEmail(Constants.VALID_USERNAME);
        Pattern ptr = Pattern.compile(Constants.EMAIL_REGEX);
        if (emails.size() == 0) Assert.fail("No Email found");
        for (String email : emails) {
            Assert.assertTrue(ptr.matcher(email).matches());
        }
    }

    @Test
    public void tc013_validateBodyIsPresentForEachComment() {
        ArrayList<Integer> posts = getPosts(getUser(Constants.VALID_USERNAME));
        for (int post : posts) {
            List<HashMap<String, Object>> comments = getComments(post);
            for (HashMap<String, Object> comment : comments) {
                Assert.assertNotNull(comment.get("body"));
            }
        }
    }

    @Test
    public void tc014_schemaValidationComments() {
        RestAssured.get(Constants.BASE_URL + Constants.COMMENTS_ENDPOINT).then().assertThat()
                .body(matchesJsonSchemaInClasspath(Constants.COMMENTS_ENDPOINT.replace("/", "") + "_schema.json"));
    }
}

