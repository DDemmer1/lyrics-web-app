package de.demmer.dennis.lyricswebapp.util;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.classification.ClassificationResult;
import org.apache.lucene.classification.SimpleNaiveBayesClassifier;
import org.apache.lucene.classification.document.SimpleNaiveBayesDocumentClassifier;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreClassifierTraining {


    public static void classifieGenre(Document doc) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new NIOFSDirectory(new File("training-index").toPath());

        IndexReader reader = DirectoryReader.open(index);

        Map<String, Analyzer> field2analyser = new HashMap<>();
        field2analyser.put("lyrics",analyzer);

//        SimpleNaiveBayesDocumentClassifier classifier = new SimpleNaiveBayesDocumentClassifier(reader,null,"genre",field2analyser);
        SimpleNaiveBayesClassifier classifier1 = new SimpleNaiveBayesClassifier(reader,analyzer,null,"genre","lyrics");
        List<ClassificationResult<BytesRef>> classes = classifier1.getClasses(doc.get("lyrics"));

        System.out.println("Classes found:" + classes.size());
        for (ClassificationResult<BytesRef> resultList : classes) {
            System.out.println(resultList.getAssignedClass().toString());
        }

    }



}
