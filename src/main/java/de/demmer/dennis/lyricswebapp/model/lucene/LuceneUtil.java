package de.demmer.dennis.lyricswebapp.model.lucene;

import de.demmer.dennis.lyricswebapp.model.Song;
import de.demmer.dennis.lyricswebapp.util.Util;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;


import java.util.*;

public class LuceneUtil {


    public static void buildIndex() throws IOException, ParseException {
        List<Song> songs = Util.deserializeSongs();

        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new NIOFSDirectory(new File("index").toPath());

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter w = new IndexWriter(index, config);

        for (Song song : songs) {
            addSong(w, song);
        }

        w.close();

    }

    public static void buildTrainIndex() throws IOException, ParseException {
        List<Song> songs = Util.deserializeSongs();

        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new NIOFSDirectory(new File("training-index").toPath());

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter w = new IndexWriter(index, config);

        for (Song song : songs) {
            if(!song.getGenre().equals("Non-Music")){
            addSong(w, song);
            } else {
                System.out.println("song is 'Non-Music' genre");
            }
        }

        w.close();

    }

    private static void addSong(IndexWriter w, Song song) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("artist", song.getArtist(), Field.Store.YES));
        doc.add(new TextField("songName", song.getSongName(), Field.Store.YES));
        doc.add(new TextField("lyrics", song.getLyrics(), Field.Store.YES));
        doc.add(new StringField("genre", song.getGenre(), Field.Store.YES));
        doc.add(new StringField("year", song.getYear(), Field.Store.YES));
        doc.add(new TextField("albumName", song.getAlbumName(), Field.Store.YES));
        for (String style : song.getStyles()) {
            doc.add(new TextField("styles", style, Field.Store.YES));
        }
//      TrackID als StringField damit sie nicht tokanisiert wird
        doc.add(new StringField("trackID", song.getTrackID(), Field.Store.YES));
        w.addDocument(doc);

    }

    //TODO autowire index
    public static Map<Song, List<String>> search(String querystr, String field) throws IOException, ParseException {
        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new NIOFSDirectory(new File("index").toPath());

//        Query q = new QueryParser(field, analyzer).parse(querystr+"~1");
        Term term = new Term(field, querystr);
        Query q = new WildcardQuery(term);
        // Suche
        int hitsPerPage = 100;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        // Ergenisse einer Liste hinzufuegen
        List<Song> songs = new ArrayList<>();

        Map<Song, List<String>> searchResult = new HashMap<>();

        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            List<String> styles = new ArrayList<>();

            List<String> classes = GenreClassifierTraining.classifieGenre(d);

            if (d.get("styles") != null) {
                StringTokenizer st = new StringTokenizer(d.get("styles"));
                while (st.hasMoreTokens()) {
                    styles.add(st.nextToken());
                }
            }

            Song song = new Song(d.get("songName"), d.get("albumName"), d.get("artist"), d.get("lyrics"), d.get("genre"), styles, d.get("year"), d.get("trackID"));
            searchResult.put(song, classes);
        }


        reader.close();

        return searchResult;

    }
}