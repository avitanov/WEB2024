package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
public class Song {
    private long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;
    private Album album;
    public Song(String trackId, String title, String genre, int releaseYear) {
        this.id=(long) (Math.random() * 1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        album=null;
    }
    public void AddArtist(Artist a){
        performers.add(a);
    }
    public void AddAlbum(Album a){
        this.album=a;
    }
}
