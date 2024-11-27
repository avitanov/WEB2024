package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;


    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @PostMapping()
    public String getArtists(@RequestParam String trackId, Model model){
        List<Artist> artistList=this.artistService.listArtists();
        model.addAttribute("artists",artistList);
        model.addAttribute("trackId",trackId);

        return "artist";
    }
//    @PostMapping()
//    public String addArist(@RequestParam String trackId,Model model){
//        Artist selectedArtist = artistService.ArtistfindById(Long.valueOf(trackId));
//        model.addAttribute("selectedArtist",selectedArtist);
//       return "songDetails";
//    }
}
