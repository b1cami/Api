package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostId(Long postId);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM post WHERE post_id >= (:postCount - 20) AND post_id < :postCount ORDER BY post_id DESC", nativeQuery = true)
    ArrayList<Post> findPosts(@Param("postCount") Long postCount);

}
