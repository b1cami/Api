package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findByPostId(Integer postId);

}
