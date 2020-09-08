package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface LunchRepository extends JpaRepository<Lunch, Integer> {

    Optional<Lunch> findByLunchId(Integer lunchId);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM lunch WHERE lunch_id >= (:lunchCount - 20) AND lunch_id < :lunchCount ORDER BY lunch_id DESC", nativeQuery = true)
    ArrayList<Lunch> findLunches(@Param("lunchCount") Integer lunchCount);

}
