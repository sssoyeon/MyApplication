package com.example.soyeon.final_report_02_20150991;

import java.io.Serializable;

/**
 * Created by soyeon on 2017. 6. 23..
 */

public class MovieData implements Serializable {
    //제목,감독,주연,개봉일 평점,내
    //감독
    //주연
    //개봉일
    //평점, 내여ㅛㅇ
    int _id;
    String title;
    String director;
    float rating;
    String releaseDay;
    String genre;
    String actor;
    String story;

    public MovieData(int _id, String title, String director, float rating, String releaseDay, String genre, String actor, String story) {
        this._id = _id;
        this.title = title;
        this.director = director;
        this.rating = rating;
        this.releaseDay = releaseDay;
        this.genre = genre;
        this.actor = actor;
        this.story = story;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float raiting) {
        this.rating = rating;
    }

    public String getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(String releaseDay) {
        this.releaseDay = releaseDay;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String toString() {
        return "MovieData{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", rating='" + rating + '\'' +
                ", genre'" + genre + '\'' +
                ", actor='" + actor + '\'' +
                ", story='" + story + '\'' +
                '}';
    }
}
