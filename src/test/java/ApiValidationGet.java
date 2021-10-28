import Constants.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.regex.Pattern;

public class ApiValidationGet extends ApiValidationBaseClass {


    @Test
    public void IsStatusCodeForUsersIsOk() {
        int response_code = getStatusCode(Constants.USERS_ENDPOINT);
        Assert.assertEquals(response_code, 200);

    }

    @Test
    public void checkStatusCodeForPosts() {
        int response_code = getStatusCode(Constants.POSTS_ENDPOINT);
        Assert.assertEquals(response_code, 200);

    }

    @Test
    public void checkStatusCodeForComments() {
        int response_code = getStatusCode(Constants.COMMENTS_ENDPOINT);
        Assert.assertEquals(response_code, 200);

    }

    @Test
    public void searchValidUser() {

        boolean user = getUsername(Constants.VALID_USERNAME);
        Assert.assertTrue(user);
    }

    @Test
    public void searchInvalidUser() {
        boolean user = getUsername(Constants.INVALID_USERNAME);
        Assert.assertFalse(user);
    }

    @Test
    public void checkIfUserIdExist() {
        int userId = getUser(Constants.VALID_USERNAME);
        Assert.assertNotNull(userId);
    }

    @Test
    public void checkIfPostsExistForValidUser() {
        ArrayList<Integer> posts = getPosts(getUser(Constants.VALID_USERNAME));
        System.out.println(posts);
        Assert.assertNotNull(posts);
    }

    @Test
    public void checkIfPostsExistForInvalidUser() {
        ArrayList<Integer> post = getPosts(getUser(Constants.INVALID_USERNAME));
        Assert.assertEquals(post.size(), 0);
    }

    @Test
    public void checkCommentsForEachPost() {

        ArrayList<Integer> posts = getPosts(getUser(Constants.VALID_USERNAME));
        for (int post : posts) {
            List<HashMap<String, Object>> comments = getComments(post);
            Assert.assertNotNull(comments);
        }
    }

    @Test
    public void validateEmailForEachComment() {

        Pattern ptr = Pattern.compile(Constants.EMAIL_REGEX);
        ArrayList<String> emails = getEmail(Constants.VALID_USERNAME);
        Assert.assertNotEquals(emails.size(), 0);
        for (String email : emails) {
            Assert.assertTrue(ptr.matcher(email).matches());
        }
    }
}

