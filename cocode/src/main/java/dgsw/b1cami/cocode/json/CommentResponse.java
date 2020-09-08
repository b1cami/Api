package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.CommentOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommentResponse extends Response {

    ArrayList<CommentOutput> comments;

    public CommentResponse(int status, String message) {
        super(status, message);
    }

    public CommentResponse(int status, String message, HashMap<String, ArrayList<String>> comments) {
        super(status, message);

        this.comments = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> entry : comments.entrySet()) {
            for(String comment : entry.getValue())
                this.comments.add(new CommentOutput(entry.getKey(), comment));
        }
    }

    public ArrayList<CommentOutput> getComments() {
        return comments;
    }
}
