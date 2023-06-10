package com.example.mymovie;

public class Tv {
    String title;
    String poster;
    String overview;
    String release;

    public Tv(String title , String poster , String overview , String release){
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.release = release;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease() {
        return release;
    }

}

