package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.CommentOutput;
import dgsw.b1cami.cocode.Domain.LunchComment;
import dgsw.b1cami.cocode.Domain.PostComment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommentResponse extends Response {

    ArrayList<Object> comments;

    public CommentResponse(int status, String message) {
        super(status, message);
    }

    public CommentResponse(int status, String message, HashMap<String, ArrayList<Object>> comments) {
        super(status, message);

        this.comments = new ArrayList<>();
        for(Map.Entry<String, ArrayList<Object>> entry : comments.entrySet()) {
            for (Object obj : entry.getValue()) {
                if(obj instanceof LunchComment)
                    this.comments.add(new CommentOutput(entry.getKey(), (LunchComment) obj));
                else
                    this.comments.add(new CommentOutput(entry.getKey(), (PostComment) obj));
            }
        }
    }

    public ArrayList<Object> getComments() {
        return comments;
    }
}
