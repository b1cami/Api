package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserId(Integer userId);

    Optional<User> findByUserEmail(String userEmail);

    Optional<User> findByUserCertifyCode(String userCertifyCode);

    Optional<User> findByUserName(String userName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET user_certify_code = null where user_id = :userId", nativeQuery = true)
    void certifyUser(@Param("userId") Integer userId);

}