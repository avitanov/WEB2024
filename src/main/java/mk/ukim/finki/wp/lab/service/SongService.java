package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    public Song findByTrackId(String trackId);
    public List<Song> findByTitle(String text);
    public void deleteSong(Long id);
    public Optional<Song> findbyId(Long id);
    public void saveSong(String trackId,String title, String genre, int releaseYear, Long albumId);
    public void editSong(Long id,String trackId,String title, String genre, int releaseYear, Long albumId);
}
