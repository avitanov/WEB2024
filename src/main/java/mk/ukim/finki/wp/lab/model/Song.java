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
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;

    public Song(String trackId, String title, String genre, int releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
    }
    public void AddArtist(Artist a){
        performers.add(a);
    }
}
