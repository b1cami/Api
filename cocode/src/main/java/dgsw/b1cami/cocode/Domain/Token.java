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
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tokenId;

    @Column(nullable = false, unique = true)
    Integer tokenOwnerId;

    @Column(nullable = false)
    String tokenKey;

    @Column(nullable = false)
    String tokenIp;

    @Column(nullable = false)
    @CreationTimestamp
    LocalDateTime tokenCreated;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime tokenUpdated;

    public Token(Integer ownerId, String key, String ip) {
        this.setOwnerId(ownerId);
        this.setKey(key);
        this.setIp(ip);
    }

    public Integer getId() {
        return tokenId;
    }

    public void setId(Integer id) {
        this.tokenId = id;
    }

    public Integer getOwnerId() {
        return tokenOwnerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.tokenOwnerId = ownerId;
    }

    public String getKey() {
        return tokenKey;
    }

    public void setKey(String key) {
        this.tokenKey = key;
    }

    public String getIp() {
        return tokenIp;
    }

    public void setIp(String Ip) {
        this.tokenIp = Ip;
    }

    public LocalDateTime getCreated() {
        return tokenCreated;
    }

    public void setCreated(LocalDateTime created) {
        this.tokenCreated = created;
    }

    public LocalDateTime getUpdated() {
        return tokenUpdated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.tokenUpdated = updated;
    }
}
