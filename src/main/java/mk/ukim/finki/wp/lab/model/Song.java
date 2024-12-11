package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String trackId;

    private String title;

    private String genre;

    private int releaseYear;

    @OneToMany
    private List<Artist> performers;

    @ManyToOne
    private Album album;


    private int visits;

    @ElementCollection
    @CollectionTable(name = "song_reviews", joinColumns = @JoinColumn(name = "song_id"))
    @Column(name = "review",length = 1000)
    private List<String> reviews;

    public Song(String trackId, String title, String genre, int releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        album=null;
        visits=0;
        reviews=new ArrayList<>();
    }
    public void AddArtist(Artist a){
        performers.add(a);
    }
    public void AddAlbum(Album a){
        this.album=a;
    }
    public void AddReview(String text){
        this.reviews.add(text);
    }
}
