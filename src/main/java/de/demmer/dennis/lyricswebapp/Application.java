package de.demmer.dennis.lyricswebapp;

import de.demmer.dennis.lyricswebapp.model.lucene.LuceneUtil;
import de.demmer.dennis.lyricswebapp.util.Config;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class Application {



	public static void main(String[] args) {

        if(Config.INIT_CORPUS_INDEX){
            //buildCorpus
        }

        if(Config.INIT_TRAINING_INDEX){
            //buildTrainingIndex
        }

		SpringApplication.run(Application.class, args);
	}

//    public static void main(String[] args) throws IOException, ParseException {
//		LuceneUtil.buildIndex();
//	}

}


