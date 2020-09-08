package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    ArrayList<PostComment> findByPostId(Long postId);

}
