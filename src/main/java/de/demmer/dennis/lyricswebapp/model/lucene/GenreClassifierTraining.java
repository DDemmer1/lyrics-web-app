package de.demmer.dennis.lyricswebapp.model.lucene;


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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreClassifierTraining {


    public static List<String> classifieGenre(Document doc) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new NIOFSDirectory(new File("training-index").toPath());

        IndexReader reader = DirectoryReader.open(index);


        SimpleNaiveBayesClassifier classifier = new SimpleNaiveBayesClassifier(reader,analyzer,null,"genre","lyrics");
        List<ClassificationResult<BytesRef>> classes = classifier.getClasses(doc.get("lyrics")).subList(0,1);


        List<String> classList = new ArrayList<>();
        for (ClassificationResult<BytesRef> resultList : classes) {
//            System.out.println(resultList.getAssignedClass().utf8ToString());
            classList.add(resultList.getAssignedClass().utf8ToString());
        }

        return classList;
    }



}
