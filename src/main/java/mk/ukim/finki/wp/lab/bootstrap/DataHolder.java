package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artistList=null;
    public static List<Song> songList=null;
    @PostConstruct
    public void init(){
        artistList=new ArrayList<>();
        artistList.add(new Artist(1001L, "John", "Doe", "Rock"));
        artistList.add(new Artist(1002L, "Jane", "Smith", "Jazz"));
        artistList.add(new Artist(1003L, "Michael", "Johnson", "Classical"));
        artistList.add(new Artist(1004L, "Emily", "Davis", "Pop"));
        artistList.add(new Artist(1005L, "Robert", "Brown", "Hip-Hop"));

        songList=new ArrayList<>();
        songList.add(new Song("T001", "Bohemian Rhapsody", "Rock", 1975));
        songList.add(new Song("T002", "Imagine", "Pop", 1971));
        songList.add(new Song("T003", "Hotel California", "Rock", 1977));
        songList.add(new Song("T004", "Billie Jean", "Pop", 1982));
        songList.add(new Song("T005", "Smells Like Teen Spirit", "Grunge", 1991));
    }
}
