package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;

import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.impl.AlbumServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService=albumService;
    }

    @GetMapping()
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Song> songList = this.songService.listSongs();
        model.addAttribute("songs", songList);
        return "listSongs";

    }
    @GetMapping("/delete/{id}")
    public String deleteSongs(@PathVariable Long id){
        this.songService.deleteSong(id);
        return "redirect:/songs";
    }

    @GetMapping("edit-song/{id}")
    public String editSong(@PathVariable Long id, Model model) {
        if (this.songService.findbyId(id).isPresent()) {
            Song song = this.songService.findbyId(id).get();
            List<Album> albumList=this.albumService.findAll();
            model.addAttribute("song", song);
            model.addAttribute("albums",albumList);
            return "add-song";
        }
        return "redirect:/songs?error=SongNotFound";
    }

    @GetMapping("add-song")
    public String addSong(Model model){
        List<Album> albumList=this.albumService.findAll();
        model.addAttribute("albums",albumList);
        return "add-song";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam String trackId,
                           @RequestParam String name,
                           @RequestParam String genre,
                           @RequestParam Integer releaseYear,
                           @RequestParam Long album){
        this.songService.saveSong(trackId,name,genre,releaseYear,album);

        return "redirect:/songs";
    }

    @PostMapping("/edit-song/{id}")
    public String getEditSongForm(@PathVariable Long id,@RequestParam String trackId,
                                  @RequestParam String name,
                                  @RequestParam String genre,
                                  @RequestParam Integer releaseYear,
                                  @RequestParam Long album){
        this.songService.editSong(id,trackId,name,genre,releaseYear,album);
        return "redirect:/songs";
    }

}
