package ovh.zain.thetennistracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ovh.zain.thetennistracker.entity.TennisSet;

@Repository
public interface TennisSetRepository extends JpaRepository<TennisSet, Long> {
}