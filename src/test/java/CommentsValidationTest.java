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
    public void tc010_checkCommentsForEachPost() {
        ArrayList<Integer> posts = getPosts(getUser(Constants.VALID_USERNAME));
        for (int post : posts) {
            List<HashMap<String, Object>> comments = getComments(post);
            Assert.assertNotNull(comments);
        }
    }

    @Test
    public void tc011_validateEmailForEachComment() {
        Pattern ptr = Pattern.compile(Constants.EMAIL_REGEX);
        ArrayList<String> emails = getEmail(Constants.VALID_USERNAME);
        if (emails.size() == 0) Assert.fail("No Email found");
        for (String email : emails) {
            Assert.assertTrue(ptr.matcher(email).matches());
        }
    }

    @Test
    public void tc012_validateBodyIsPresentForEachComment() {
        ArrayList<Integer> posts = getPosts(getUser(Constants.VALID_USERNAME));
        for (int post : posts) {
            List<HashMap<String, Object>> comments = getComments(post);
            for (HashMap<String, Object> comment : comments) {
                Assert.assertNotNull(comment.get("body"));
            }
        }
    }
    @Test
    public void tc013_schemaValidationComments(){
        RestAssured.get(Constants.BASE_URL+Constants.COMMENTS_ENDPOINT).then().assertThat()
                .body(matchesJsonSchemaInClasspath(Constants.COMMENTS_ENDPOINT.replace("/","")+"_schema.json"));
    }
}

