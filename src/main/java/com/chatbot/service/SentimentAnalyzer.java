package com.chatbot.service;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class SentimentAnalyzer {

    private final StanfordCoreNLP pipeline;

    public SentimentAnalyzer() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String getSentiment(String text) {
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree sentimentTree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentimentScore = RNNCoreAnnotations.getPredictedClass(sentimentTree);

            // Convert numerical sentiment scores to readable text
            if (sentimentScore == 0 || sentimentScore == 1) {
                return "negative";
            } else if (sentimentScore == 2) {
                return "neutral";
            } else {
                return "positive";
            }
        }
        return "neutral"; // Default if no sentiment is detected
    }
}
