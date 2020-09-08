package dgsw.b1cami.cocode.Domain;

public class LunchOutput {

    Long id;

    String uploader;

    String foodName;

    String description;

    public LunchOutput(Lunch lunch) {
        this.setId(lunch.getId());
        this.setUploader(lunch.getUploader());
        this.setFoodName(lunch.getFoodName());
        this.setDescription(lunch.getDescription());
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
}
