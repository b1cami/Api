package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.LunchComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface LunchCommentRepository extends JpaRepository<LunchComment, Long> {

    ArrayList<LunchComment> findByLunchId(Long lunchId);

}
