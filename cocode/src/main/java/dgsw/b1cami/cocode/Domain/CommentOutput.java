package dgsw.b1cami.cocode.Domain;

import java.time.LocalDateTime;

public class CommentOutput {

    Long id;

    String userName;

    String comment;

    LocalDateTime upload;

    LocalDateTime update;

    public CommentOutput(String userName, LunchComment comment) {
        this.id = comment.getId();
        this.userName = userName;
        this.comment = comment.getComment();
        this.upload = comment.getCommentUploaded();
        this.update = comment.getCommentUpdated();
    }

    public CommentOutput(String userName, PostComment comment) {
        this.id = comment.getId();
        this.userName = userName;
        this.comment = comment.getComment();
        this.upload = comment.getCommentUploaded();
        this.update = comment.getCommentUpdated();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getUpload() {
        return upload;
    }

    public LocalDateTime getUpdate() {
        return update;
    }

}
