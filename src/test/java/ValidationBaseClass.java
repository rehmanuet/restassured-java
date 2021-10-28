import Constants.Constants;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Implementation of the Base Class for the generic methods used for test cases
 *
 * @author Abdur.Rehman
 */

public class ValidationBaseClass {


    /**
     * To retrieve user's id from the Response
     *
     * @param name, Name of user
     */
    Integer getUser(String name) {
        Integer userId = null;
        List<HashMap<String, Object>> users = getResponse(Constants.USERS_ENDPOINT);

        for (HashMap<String, Object> user : users) {
            if (user.get("username").equals(name)) {
                userId = (Integer) user.get("id");
            }
        }
        return userId;
    }

    /**
     * To retrieve all the posts made by specific user
     *
     * @param userId, I'd of user
     */
    ArrayList<Integer> getPosts(Integer userId) {
        List<HashMap<String, Object>> userPosts = getResponse(Constants.POSTS_ENDPOINT);
        ArrayList<Integer> allPost = new ArrayList<>();
        for (HashMap<String, Object> post : userPosts) {
            if (post.get("userId").equals(userId)) {
                allPost.add((Integer) post.get("id"));
            }
        }
        return allPost;
    }

    /**
     * To retrieve status code of endpoint
     *
     * @param path, path of endpoint
     */
    int getStatusCode(String path) {
        return RestAssured.get(Constants.BASE_URL + path).getStatusCode();
    }


    /**
     * To retrieve the username of user
     *
     * @param name, name of user
     */
    boolean getUsername(String name) {

        boolean isPresent = false;
        List<HashMap<String, Object>> response = getResponse(Constants.USERS_ENDPOINT + "?username=" + name);
        if (response.size() != 0 && (response.get(0).get("username").equals(name))) {
            isPresent = true;
        }
        return isPresent;
    }

    /**
     * To retrieve the response body of given path
     *
     * @param path, path of endpoint
     */
    List<HashMap<String, Object>> getResponse(String path) {
        return RestAssured.get(Constants.BASE_URL + path).jsonPath().getList(".");
    }

    /**
     * To retrieve all the comments of specific post
     *
     * @param postId, path of endpoint
     */
    List<HashMap<String, Object>> getComments(int postId) {
        return getResponse(Constants.POSTS_ENDPOINT + "/" + postId + Constants.COMMENTS_ENDPOINT);
    }

    /**
     * To retrieve all emails against the post made by specific users
     *
     * @param username, username of the user
     */
    ArrayList<String> getEmail(String username) {
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<Integer> posts = getPosts(getUser(username));
        for (int post : posts) {
            List<HashMap<String, Object>> comments = getComments(post);
            for (HashMap<String, Object> comment : comments) {
                emails.add((String) comment.get("email"));
            }
        }
        return emails;
    }



}
