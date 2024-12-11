package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.Jpa.AlbumRepository;
import org.springframework.stereotype.Service;
import mk.ukim.finki.wp.lab.repository.Jpa.SongRepository;
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
        Song tmp=this.songRepository.findByTrackId(song.getTrackId());
        if(tmp!=null){
            tmp.AddArtist(artist);
        }
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public List<Song> findByTitle(String text) {
        return songRepository.findAllByTitle(text);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findbyId(Long id) {
        return this.songRepository.findById(id);
    }

    @Override
    public void saveSong(String trackId,String title, String genre, int releaseYear, Long albumId){
        Album al=this.albumRepository.findById(albumId).orElse(null);
        Song tmp=new Song(trackId,title,genre,releaseYear);
        tmp.AddAlbum(al);
        this.songRepository.save(tmp);
    }

    @Override
    public void editSong(Long id,String trackId, String title, String genre, int releaseYear, Long albumId) {
        Album al=this.albumRepository.findById(albumId).orElse(null);
        this.songRepository.deleteById(id);
        Song tmp=new Song(trackId,title,genre,releaseYear);
        tmp.AddAlbum(al);
        this.songRepository.save(tmp);
    }
    @Override
    public void visitSong(String trackId){
        Song tmp = this.songRepository.findByTrackId(trackId);
        int visits=tmp.getVisits();
        tmp.setVisits(++visits);
        this.songRepository.save(tmp);
    }

}
