package dgsw.b1cami.cocode.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Lunch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer lunchId;

    @Column(nullable = false)
    String lunchUploader;

    @Column(nullable = false)
    String lunchFoodName;

    @Column(nullable = true)
    String lunchDescription;

    public Integer getId() {
        return lunchId;
    }

    public void setId(Integer id) {
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
}
