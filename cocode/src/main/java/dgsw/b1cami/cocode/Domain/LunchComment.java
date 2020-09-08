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
public class LunchComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long lcId;

    @Column(nullable = false)
    Long lunchId;

    @Column(nullable = false, length = 50)
    Integer userId;

    @Column(nullable = true, length = 100)
    String lcComment;

    @Column(nullable = false)
    @CreationTimestamp
    LocalDateTime commentUploaded;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime commentUpdated;

    public Long getId() {
        return lcId;
    }

    public void setId(Long id) {
        this.lcId = id;
    }

    public Long getLunchId() {
        return lunchId;
    }

    public void setLunchId(Long lunchId) {
        this.lunchId = lunchId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return lcComment;
    }

    public void setComment(String comment) {
        this.lcComment = comment;
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
