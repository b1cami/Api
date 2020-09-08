package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Domain.PostOutput;

import java.util.ArrayList;

public class PostsResponse extends Response {

    ArrayList<PostOutput> posts;

    public PostsResponse(int status, String message) {
        super(status, message);
    }

    public PostsResponse(int status, String message, ArrayList<PostOutput> posts) {
        super(status, message);
        this.posts = posts;
    }

    public ArrayList<PostOutput> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.add(new PostOutput(post));
    }

}
