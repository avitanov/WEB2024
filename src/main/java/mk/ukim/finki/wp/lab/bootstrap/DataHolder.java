package mk.ukim.finki.wp.lab.bootstrap;
import java.util.Random;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.Jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.Jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artistList=null;
    public static List<Album> albumList=null;
    public static List<Song> songList=null;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public DataHolder(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @PostConstruct
    public void init(){
        artistList=new ArrayList<>();
        artistList.add(new Artist(1001L, "John", "Doe", "Rock"));
        artistList.add(new Artist(1002L, "Jane", "Smith", "Jazz"));
        artistList.add(new Artist(1003L, "Michael", "Johnson", "Classical"));
        artistList.add(new Artist(1004L, "Emily", "Davis", "Pop"));
        artistList.add(new Artist(1005L, "Robert", "Brown", "Hip-Hop"));



        List<Album> albumList=new ArrayList<>();
        if(this.albumRepository.count()==0){
            albumList.add(new Album("Abbey Road", "Rock", "1969"));
            albumList.add(new Album("Born to Run", "Rock", "1975"));
            albumList.add(new Album("Nevermind", "Grunge", "1991"));
            albumList.add(new Album("The Wall", "Progressive Rock", "1979"));
            albumList.add(new Album("A Night at the Opera", "Rock", "1975"));

            this.albumRepository.saveAll(albumList);
        }
        songList=new ArrayList<>();
        if(this.songRepository.count() == 0){
            songList.add(new Song("T001", "Bohemian Rhapsody", "Rock", 1975));
            songList.add(new Song("T002", "Imagine", "Pop", 1971));
            songList.add(new Song("T003", "Hotel California", "Rock", 1977));
            songList.add(new Song("T004", "Billie Jean", "Pop", 1982));
            songList.add(new Song("T005", "Smells Like Teen Spirit", "Grunge", 1991));
            Random random = new Random();
            songList.forEach(song -> {
                int randomIndex = random.nextInt(albumList.size());
                song.AddAlbum(albumList.get(randomIndex));
            });

            this.songRepository.saveAll(songList);
        }

    }
}
