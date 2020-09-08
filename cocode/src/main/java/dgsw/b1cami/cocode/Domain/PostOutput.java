package dgsw.b1cami.cocode.Domain;

import java.time.LocalDateTime;

public class PostOutput {

    Long id;

    String uploader;

    String title;

    String content;

    LocalDateTime upload;

    LocalDateTime update;

    public PostOutput(Post post) {
        this.setId(post.getId());
        this.setUploader(post.getUploader());
        this.setTitle(post.getTitle());
        this.setContent(post.getContent());
        this.setUpload(post.getPostUploaded());
        this.setUpdate(post.getPostUpdated());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUpload() {
        return upload;
    }

    public void setUpload(LocalDateTime upload) {
        this.upload = upload;
    }

    public LocalDateTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalDateTime update) {
        this.update = update;
    }

}