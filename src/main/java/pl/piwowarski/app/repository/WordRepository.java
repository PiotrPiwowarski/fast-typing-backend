package pl.piwowarski.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piwowarski.app.entity.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
}
