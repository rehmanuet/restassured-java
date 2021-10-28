import Constants.Constants;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiValidationBaseClass {

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

    int getStatusCode(String path) {
        return RestAssured.get(Constants.BASE_URL + path).getStatusCode();
    }

    Boolean getUsername(String name) {
        boolean isPresent = false;
        List<HashMap<String, Object>> users = getResponse(Constants.USERS_ENDPOINT);
        for (HashMap<String, Object> user : users) {
            if (user.get("username").equals(name)) {
                isPresent = true;
            }
        }
        return isPresent;
    }

    List<HashMap<String, Object>> getResponse(String path) {
        return RestAssured.get(Constants.BASE_URL + path).jsonPath().getList(".");
    }

    List<HashMap<String, Object>> getComments(int postId) {
        return getResponse(Constants.POSTS_ENDPOINT + "/" + postId + Constants.COMMENTS_ENDPOINT);
    }

    ArrayList<String> getEmail(String userName) {
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<Integer> posts = getPosts(getUser(userName));
        for (int post : posts) {
            List<HashMap<String, Object>> comments = getComments(post);
            for (HashMap<String, Object> comment : comments) {
                emails.add((String) comment.get("email"));
            }
        }
        return emails;
    }
}
