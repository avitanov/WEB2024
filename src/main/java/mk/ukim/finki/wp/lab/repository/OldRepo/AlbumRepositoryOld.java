package mk.ukim.finki.wp.lab.repository.OldRepo;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepositoryOld {
    public List<Album> findAll(){
        return DataHolder.albumList.stream().toList();
    }
    public Optional<Album> findById(Long id){
        return DataHolder.albumList.stream().filter(album -> album.getId().equals(id)).findFirst();
    }
}
