package de.demmer.dennis.lyricswebapp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class MainController {


    @GetMapping(value = "lyrics/{id}")
    @ResponseBody
    String home(@PathVariable("id") String id) {
        System.out.println(id);
        return id;
    }

}
