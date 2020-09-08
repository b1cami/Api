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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long postId;

    @Column(nullable = false)
    String postUploader;

    @Column(nullable = false, length = 100)
    String postTitle;

    @Column(nullable = false, length = 10000)
    String postContent;

    @Column(nullable = false)
    @CreationTimestamp
    LocalDateTime postUploaded;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime postUpdated;

    public Long getId() {
        return postId;
    }

    public void setId(Long id) {
        this.postId = id;
    }

    public String getUploader() {
        return postUploader;
    }

    public void setUploader(String uploader) {
        this.postUploader = uploader;
    }

    public String getTitle() {
        return postTitle;
    }

    public void setTitle(String title) {
        this.postTitle = title;
    }

    public String getContent() {
        return postContent;
    }

    public void setContent(String content) {
        this.postContent = content;
    }

    public LocalDateTime getPostUploaded() {
        return postUploaded;
    }

    public void setPostUploaded(LocalDateTime postUploaded) {
        this.postUploaded = postUploaded;
    }

    public LocalDateTime getPostUpdated() {
        return postUpdated;
    }

    public void setPostUpdated(LocalDateTime postUpdated) {
        this.postUpdated = postUpdated;
    }
    
}