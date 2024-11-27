package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist,song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public List<Song> findByTitle(String text) {
        return songRepository.findSongsbyTitle(text);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteSong(id);
    }

    @Override
    public Optional<Song> findbyId(Long id) {
        return this.songRepository.findbyId(id);
    }

    @Override
    public void saveSong(String trackId,String title, String genre, int releaseYear, Long albumId){
        Album al=this.albumRepository.findById(albumId).orElse(null);
        this.songRepository.saveSong(trackId,title,genre,releaseYear,al);
    }

    @Override
    public void editSong(Long id,String trackId, String title, String genre, int releaseYear, Long albumId) {
        Album al=this.albumRepository.findById(albumId).orElse(null);
        this.songRepository.editSong(id,trackId,title,genre,releaseYear,al);
    }
    @Override
    public void visitSong(String trackId){
        this.songRepository.visitedSong(trackId);
    }

}
