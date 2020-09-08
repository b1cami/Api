package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Domain.PostOutput;

public class PostResponse extends Response {

    PostOutput post;

    public PostResponse(int status, String message) {
        super(status, message);
    }

    public PostResponse(int status, String message, Post post) {
        super(status, message);
        this.post = new PostOutput(post);
    }

}
