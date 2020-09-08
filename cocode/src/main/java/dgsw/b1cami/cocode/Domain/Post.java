package dgsw.b1cami.cocode.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}