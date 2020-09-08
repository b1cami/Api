package dgsw.b1cami.cocode.Domain;

public class PostOutput {

    Long id;

    String uploader;

    String title;

    String content;

    public PostOutput(Post post) {
        this.setId(post.getId());
        this.setUploader(post.getUploader());
        this.setTitle(post.getTitle());
        this.setContent(post.getContent());
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
}