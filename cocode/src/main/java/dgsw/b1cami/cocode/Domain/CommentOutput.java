package dgsw.b1cami.cocode.Domain;

public class CommentOutput {

    String userName;

    String comment;

    public CommentOutput(String userName, String comment) {
        this.userName = userName;
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }
}
