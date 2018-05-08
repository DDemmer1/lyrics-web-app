package de.demmer.dennis.lyricswebapp;

import de.demmer.dennis.lyricswebapp.util.lucene.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class MainController {


    @GetMapping(value = "search/{query}")
    @ResponseBody
    String home(@PathVariable("query") String query) {
        List<Song> songs = null;
        try {
            songs = LuceneUtil.search(query, "artist");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (songs != null) {
            StringBuffer buffer = new StringBuffer();
            for (Song song : songs) {
                buffer.append(song.getArtist() + "\n | " + song.getSongName() + "\n | " + song.getGenre() + "\n" + "<br>");
            }
            System.out.println(songs.size()+ " Matches found!");
            return buffer.toString();
        } else{
            return "No matches found!";
        }


    }

}
