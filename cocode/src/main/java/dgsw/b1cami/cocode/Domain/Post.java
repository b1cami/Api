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
    Integer postId;

    @Column(nullable = false)
    String postUploader;

    @Column(nullable = false, length = 100)
    String postTitle;

    @Column(nullable = false, length = 10000)
    String postContent;

    public Integer getId() {
        return postId;
    }

    public void setId(Integer id) {
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