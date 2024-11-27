package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SongRepository {
    public List<Song> findAll(){
        return DataHolder.songList;
    }
    public Song findByTrackId(String trackId){
        return DataHolder.songList.stream().filter(song->song.getTrackId().equals(trackId)).findFirst().orElse(null);
    }
    public Artist addArtistToSong(Artist artist, Song song){
        Song tmp=this.findByTrackId(song.getTrackId());
        if(tmp!=null){
            tmp.AddArtist(artist);
        }
        return artist;
    }
    public List<Song> findSongsbyTitle(String text){
        return DataHolder.songList.stream().filter(song -> song.getTitle().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());

    }
    public void deleteSong(Long id){
        DataHolder.songList.removeIf(song -> song.getId()==id);
    }
    public Optional<Song> findbyId(Long id){
        return DataHolder.songList.stream().filter(song-> song.getId()==id).findFirst();
    }
    public void saveSong(String trackId,String title, String genre, int releaseYear, Album album){
        Song tmp=new Song(trackId,title,genre,releaseYear);
        if(album!=null){
            tmp.AddAlbum(album);
        }
        DataHolder.songList.add(tmp);
    }
    public void editSong(Long id,String trackId,String title,String genre,int releaseYear,Album album){
        Song tmp=DataHolder.songList.stream().filter(song -> song.getId()==id).findFirst().get();
        tmp.setTrackId(trackId);
        tmp.setTitle(title);
        tmp.setGenre(genre);
        tmp.setReleaseYear(releaseYear);
        tmp.setAlbum(album);

    }
}
