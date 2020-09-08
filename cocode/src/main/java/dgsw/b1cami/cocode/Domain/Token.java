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

}
