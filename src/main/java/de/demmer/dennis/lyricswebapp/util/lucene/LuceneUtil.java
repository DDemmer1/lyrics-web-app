package de.demmer.dennis.lyricswebapp.util.lucene;

import de.demmer.dennis.lyricswebapp.Song;
import de.demmer.dennis.lyricswebapp.util.Util;

import java.io.File;
import java.io.IOException;

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
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;


import java.nio.file.Path;
import java.util.List;

public class LuceneUtil {


    public static void buildIndex() throws IOException, ParseException {
        List<Song> songs = Util.deserializeSongs();

        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new NIOFSDirectory(new File("index").toPath());

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter w = new IndexWriter(index, config);

        for (Song song: songs){
            addSong(w, song);
        }

        w.close();

    }

    private static void addSong(IndexWriter w, Song song) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("artist", song.getArtist(), Field.Store.YES));
        doc.add(new TextField("songName", song.getSongName(), Field.Store.YES));
        doc.add(new TextField("lyrics", song.getLyrics(), Field.Store.YES));
        doc.add(new TextField("genre;", song.getGenre(), Field.Store.YES));
        doc.add(new TextField("year", song.getYear(), Field.Store.YES));
        doc.add(new TextField("albumName", song.getAlbumName(), Field.Store.YES));
        for (String style: song.getStyles()) {
            doc.add(new TextField("styles", style, Field.Store.YES));

        }
//      TrackID als StringField damit sie nicht tokanisiert wird
        doc.add(new StringField("trackID", song.getTrackID(), Field.Store.YES));
        w.addDocument(doc);

    }


    public static void search(String querystr) throws IOException, ParseException {
        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new NIOFSDirectory(new File("index").toPath());


        // the "title" arg specifies the default field to use
        // when no field is explicitly specified in the query.
        Query q = new QueryParser("artist", analyzer).parse(querystr);

        // 3. search
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        // 4. display results
        System.out.println("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("artist") + "\t" + d.get("songName"));
        }

        reader.close();

    }
}
