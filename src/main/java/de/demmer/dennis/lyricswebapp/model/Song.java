package de.demmer.dennis.lyricswebapp.model;

import java.io.Serializable;
import java.util.List;

public class Song implements Serializable{
    private String songName;
    private String artist;
    private String lyrics;
    private String genre;
    private List<String> styles;
    private String year;
    private String albumName;
    private String trackID;




    public Song(String songName, String albumName, String artist, String lyrics, String genre, List<String> styles, String year, String trackID) {
        this.songName = songName;
        this.albumName = albumName;
        this.artist = artist;
        this.lyrics = lyrics;
        this.genre = genre;
        this.styles = styles;
        this.year = year;
        this.trackID = trackID;
    }


    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }


    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getStyles() {
        return styles;
    }

    public void setStyles(List<String> styles) {
        this.styles = styles;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Song: "+songName+", Album: "+albumName+", Artist: " + artist + ", Lyrics.len: " + lyrics.length() + ", Genre: " + genre + ", Styles: " + styles.toString()+ ", Track ID: "+ trackID;
    }
}
