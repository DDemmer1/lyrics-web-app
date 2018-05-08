package de.demmer.dennis.lyricswebapp;

import de.demmer.dennis.lyricswebapp.util.lucene.LuceneUtil;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//    public static void main(String[] args) throws  IOException, ParseException {
//        LuceneUtil.buildTrainIndex();
//    }

}


