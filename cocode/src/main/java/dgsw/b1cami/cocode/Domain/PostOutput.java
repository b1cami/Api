package dgsw.b1cami.cocode.Domain;

public class PostOutput {

    Integer id;

    Long uploader;

    String title;

    String content;

    public PostOutput(Post post) {
        this.setId(post.getId());
        this.setUploader(post.getUploader());
        this.setTitle(post.getTitle());
        this.setContent(post.getContent());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUploader() {
        return uploader;
    }

    public void setUploader(Long uploader) {
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