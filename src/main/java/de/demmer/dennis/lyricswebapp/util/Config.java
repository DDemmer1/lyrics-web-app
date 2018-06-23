package de.demmer.dennis.lyricswebapp.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("config")
public class Config {


    public final boolean INIT_CORPUS_INDEX= false;


    public final boolean INIT_TRAINING_INDEX = false;

    public final boolean ENABLE_WILDCARD_SEARCH = false;

    public String test = "test";


}
