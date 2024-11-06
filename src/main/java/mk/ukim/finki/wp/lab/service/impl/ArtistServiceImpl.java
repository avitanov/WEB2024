package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Service;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist ArtistfindById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }
}
