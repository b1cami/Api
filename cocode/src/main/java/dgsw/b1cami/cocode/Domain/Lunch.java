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
public class Lunch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long lunchId;

    @Column(nullable = false)
    String lunchUploader;

    @Column(nullable = false, length = 50)
    String lunchFoodName;

    @Column(nullable = true, length = 100)
    String lunchDescription;

    @Column(nullable = false)
    @CreationTimestamp
    LocalDateTime lunchUploaded;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime lunchUpdated;

    public Long getId() {
        return lunchId;
    }

    public void setId(Long id) {
        this.lunchId = id;
    }

    public String getUploader() {
        return lunchUploader;
    }

    public void setUploader(String uploader) {
        this.lunchUploader = uploader;
    }

    public String getFoodName() {
        return lunchFoodName;
    }

    public void setFoodName(String foodName) {
        this.lunchFoodName = foodName;
    }

    public String getDescription() {
        return lunchDescription;
    }

    public void setDescription(String description) {
        this.lunchDescription = description;
    }

    public LocalDateTime getLunchUploaded() {
        return lunchUploaded;
    }

    public void setLunchUploaded(LocalDateTime lunchUploaded) {
        this.lunchUploaded = lunchUploaded;
    }

    public LocalDateTime getLunchUpdated() {
        return lunchUpdated;
    }

    public void setLunchUpdated(LocalDateTime lunchUpdated) {
        this.lunchUpdated = lunchUpdated;
    }

}
