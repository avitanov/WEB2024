package mk.ukim.finki.wp.lab.repository.Jpa;

import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
Song findByTrackId(String trackId);
List<Song> findAllByTitle(String title);
void deleteById(Long Id);
Optional<Song> findById(Long Id);
}
