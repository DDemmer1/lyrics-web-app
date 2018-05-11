package de.demmer.dennis.lyricswebapp;

import de.demmer.dennis.lyricswebapp.model.Song;
import de.demmer.dennis.lyricswebapp.model.lucene.LuceneUtil;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class MainController {


    @GetMapping(value = "search/{query}")
    @ResponseBody
    String home(@PathVariable("query") String query) {
        Map<Song,List<String>> songs = null;
        try {
            songs = LuceneUtil.search(query, "artist");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (songs != null) {
            StringBuffer buffer = new StringBuffer();

            for(Map.Entry<Song,List<String>> entry : songs.entrySet()) {
                Song song = entry.getKey();
                List<String> classes = entry.getValue();
                buffer.append(song.getArtist() + "\n | "
                            + song.getSongName() + "\n | "
                            + song.getGenre() + "\n | "
                            + classes.toString() + "<br>");
            }
            System.out.println(songs.size()+ " Matches found!");
            return buffer.toString();

        } else{
            return "No matches found!";
        }


    }

}
