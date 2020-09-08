package dgsw.b1cami.cocode.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pcId;

    @Column(nullable = false)
    Long postId;

    @Column(nullable = false, length = 50)
    Integer userId;

    @Column(nullable = true, length = 100)
    String pcComment;

    @Column(nullable = false)
    @CreationTimestamp
    LocalDateTime commentUploaded;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime commentUpdated;

    public Long getId() {
        return pcId;
    }

    public void setId(Long id) {
        this.pcId = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return pcComment;
    }

    public void setComment(String comment) {
        this.pcComment = comment;
    }

    public LocalDateTime getCommentUploaded() {
        return commentUploaded;
    }

    public void setCommentUploaded(LocalDateTime commentUploaded) {
        this.commentUploaded = commentUploaded;
    }

    public LocalDateTime getCommentUpdated() {
        return commentUpdated;
    }

    public void setCommentUpdated(LocalDateTime commentUpdated) {
        this.commentUpdated = commentUpdated;
    }

}
