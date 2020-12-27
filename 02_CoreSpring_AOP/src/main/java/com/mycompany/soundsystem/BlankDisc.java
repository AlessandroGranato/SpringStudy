package com.mycompany.soundsystem;

import org.springframework.stereotype.Component;

import java.util.List;

//No component annotation. There's no autoscanning. Beans are defined into TrackCounterConfig class
//@Component
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    public BlankDisc() { }

    public BlankDisc(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
        for(int i = 0; i < tracks.size(); i++) {
            playTrack(i);
        }
    }

    public void playTrack(int trackNumber) {
        System.out.println("-Track: " + tracks.get(trackNumber));
    }
}