package de.demmer.dennis.lyricswebapp.util;


import de.demmer.dennis.lyricswebapp.Song;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class Util {

    public static List<Song> deserializeSongs(){


        try {
            FileInputStream streamIn = new FileInputStream("songs.ser");
            ModifiedObjectInputStream objectinputstream = new ModifiedObjectInputStream(streamIn);
            List<Song> songs = (List<Song>) objectinputstream.readObject();
            objectinputstream .close();
            return songs;

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Not deserialized");
        return null;
    }

}
