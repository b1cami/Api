package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LunchRepository extends JpaRepository<Lunch, Long> {

    Optional<Lunch> findByLunchId(Long lunchId);

}
