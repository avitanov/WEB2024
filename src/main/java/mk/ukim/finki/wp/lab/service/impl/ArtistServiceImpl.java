package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Service;
import mk.ukim.finki.wp.lab.repository.OldRepo.ArtistRepositoryOld;
import mk.ukim.finki.wp.lab.service.ArtistService;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepositoryOld artistRepositoryOld;

    public ArtistServiceImpl(ArtistRepositoryOld artistRepositoryOld) {
        this.artistRepositoryOld = artistRepositoryOld;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepositoryOld.findAll();
    }

    @Override
    public Artist ArtistfindById(Long id) {
        return artistRepositoryOld.findById(id).orElse(null);
    }
}
