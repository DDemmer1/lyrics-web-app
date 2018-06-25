package de.demmer.dennis.lyricswebapp;

import de.demmer.dennis.lyricswebapp.model.lucene.LuceneUtil;
import de.demmer.dennis.lyricswebapp.util.Config;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@EnableAutoConfiguration
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
//        new LuceneUtil().init();
//		try {
//			new LuceneUtil().buildTrainIndex();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		SpringApplication.run(Application.class, args);

	}
}


