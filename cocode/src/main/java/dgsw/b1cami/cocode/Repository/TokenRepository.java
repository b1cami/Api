package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByTokenOwnerId(String tokenOwnerId);

    Optional<Token> findByTokenKey(String tokenKey);

}
