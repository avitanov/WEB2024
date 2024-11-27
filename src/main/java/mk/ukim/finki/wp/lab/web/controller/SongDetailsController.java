package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

@Controller
@RequestMapping("/songsDetails")
public class SongDetailsController {
    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping("/{trackId}")
    public String getDetails(@PathVariable String trackId, Model model){
        Song song = songService.findByTrackId(trackId);

        model.addAttribute("song", song);
        model.addAttribute("performers", song.getPerformers());

        return "songDetails";
    }

    @PostMapping("/{trackId}")
    public String getDetails(@PathVariable String trackId,
                             @RequestParam String id, Model model){
        Song song = songService.findByTrackId(trackId);
        Artist selectedArtist = artistService.ArtistfindById(Long.parseLong(id));
        if(!song.getPerformers().contains(selectedArtist)) {
            songService.addArtistToSong(selectedArtist, song);
        }

        model.addAttribute("song", song);
        model.addAttribute("performers", song.getPerformers());

        return "songDetails";
    }
}
