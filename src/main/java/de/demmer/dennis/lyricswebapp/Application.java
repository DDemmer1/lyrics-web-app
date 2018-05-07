package de.demmer.dennis.lyricswebapp;

import de.demmer.dennis.lyricswebapp.util.Util;
import de.demmer.dennis.lyricswebapp.util.lucene.LuceneUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

@SpringBootApplication
public class Application {

//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}

    public static void main(String[] args) throws IOException, ParseException {

//        LuceneUtil.buildIndex();
        LuceneUtil.search("Coldplay");

    }

}


