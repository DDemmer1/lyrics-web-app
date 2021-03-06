package de.demmer.dennis.lyricswebapp;

import de.demmer.dennis.lyricswebapp.model.Song;
import de.demmer.dennis.lyricswebapp.model.lucene.LuceneUtil;
import de.demmer.dennis.lyricswebapp.util.Config;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class MainController {

    @Autowired
    LuceneUtil lucene;


    @GetMapping(value = "/", produces = "text/html")
    public String index(Model model) {
        model.addAttribute("checkartist",false);
        model.addAttribute("checklyrics",true);
        model.addAttribute("checksongname",false);

        return "index";
    }

    @GetMapping(value = "search")
    public String home(HttpServletRequest request, Model model) {

        Map<Song, List<String>> songs = null;

        if (request.getParameter("query") == "") {
            return "index";
        }

        String option = request.getParameter("option");

        try {
            System.out.println("check" + option);
            songs = lucene.search(request.getParameter("query"), option, 20);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("check"+option, true);
        model.addAttribute("results", songs);
        model.addAttribute("inputval",request.getParameter("query"));


        System.out.println(songs.size() + " Matches found!");
        return "index";
    }


    @GetMapping(value = "trackID/{trackID}")
    public String detail(@PathVariable("trackID") String trackID, Model model) {

        System.out.println("TrackID: " + trackID);
        Map<Song, List<String>> songs = null;

        try {
            songs = lucene.search(trackID, "trackID", 20);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

            Song song = (Song) songs.keySet().toArray()[0];
            System.out.println(songs.size() + " Match found!");
            System.out.println(song.getSongName());


            model.addAttribute("songname", song.getSongName());
            model.addAttribute("lyrics", song.getLyrics());
            model.addAttribute("artist", song.getArtist());
            model.addAttribute("album", song.getAlbumName());
            model.addAttribute("year", song.getYear());
            model.addAttribute("genre", song.getGenre());
            model.addAttribute("predgenre", songs.get(song).get(0));
            model.addAttribute("checkartist", false);
            model.addAttribute("checklyrics", true);
            model.addAttribute("checksongName", false);

//            System.out.println(lucene.getSimilar(song).size());
            model.addAttribute("results",lucene.getSimilar(song));


            return "songdetail";



    }


}
