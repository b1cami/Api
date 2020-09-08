package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByTokenOwnerId(Integer tokenOwnerId);

    Optional<Token> findByTokenKey(String tokenKey);

    @Transactional
    @Modifying
    @Query(value = "UPDATE token SET token_key = :key where token_owner_id = :ownerId", nativeQuery = true)
    void changeToken(@Param("ownerId") Integer ownerId, @Param("key") String key);

}
