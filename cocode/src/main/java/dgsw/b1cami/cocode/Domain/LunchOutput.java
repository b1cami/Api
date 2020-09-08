package dgsw.b1cami.cocode.Domain;

import java.time.LocalDateTime;

public class LunchOutput {

    Long id;

    String uploader;

    String foodName;

    String description;

    LocalDateTime upload;

    LocalDateTime update;

    public LunchOutput(Lunch lunch) {
        this.setId(lunch.getId());
        this.setUploader(lunch.getUploader());
        this.setFoodName(lunch.getFoodName());
        this.setDescription(lunch.getDescription());
        this.setUpload(lunch.getLunchUploaded());
        this.setUpdate(lunch.getLunchUpdated());
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
