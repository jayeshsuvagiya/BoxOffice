package com.adamant.boxoffice.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jayesh on 20-03-2016.
 */
public class Movie implements Parcelable {

    /*{
        "poster_path": "/6bCplVkhowCjTHXWv49UjRPn0eK.jpg",
            "adult": false,
            "overview": "Fearing the actions of a god-like Super Hero left unchecked, Gotham City’s own formidable, forceful vigilante takes on Metropolis’s most revered, modern-day savior, while the world wrestles with what sort of hero it really needs. And with Batman and Superman at war with one another, a new threat quickly arises, putting mankind in greater danger than it’s ever known before.",
            "release_date": "2016-03-23",
            "genre_ids": [
        28,
                12,
                14,
                878
        ],
        "id": 209112,
            "original_title": "Batman v Superman: Dawn of Justice",
            "original_language": "en",
            "title": "Batman v Superman: Dawn of Justice",
            "backdrop_path": "/15PbZtjRJ4zgQA8XS0otL70piQi.jpg",
            "popularity": 23.432449,
            "vote_count": 139,
            "video": false,
            "vote_average": 5.08
    }*/


        @SerializedName("poster_path")
        @Expose
        public String posterPath;
        @SerializedName("adult")
        @Expose
        public boolean adult;
        @SerializedName("overview")
        @Expose
        public String overview;
        @SerializedName("release_date")
        @Expose
        public String releaseDate;
        @SerializedName("genre_ids")
        @Expose
        public List<Long> genreIds = new ArrayList<Long>();
        @SerializedName("id")
        @Expose
        public long id;
        @SerializedName("original_title")
        @Expose
        public String originalTitle;
        @SerializedName("original_language")
        @Expose
        public String originalLanguage;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("backdrop_path")
        @Expose
        public String backdropPath;
        @SerializedName("popularity")
        @Expose
        public double popularity;
        @SerializedName("vote_count")
        @Expose
        public long voteCount;
        @SerializedName("video")
        @Expose
        public boolean video;
        @SerializedName("vote_average")
        @Expose
        public double voteAverage;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeByte(adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeList(this.genreIds);
        dest.writeLong(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeDouble(this.popularity);
        dest.writeLong(this.voteCount);
        dest.writeByte(video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.voteAverage);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.posterPath = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.genreIds = new ArrayList<Long>();
        in.readList(this.genreIds, Long.class.getClassLoader());
        this.id = in.readLong();
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.popularity = in.readDouble();
        this.voteCount = in.readLong();
        this.video = in.readByte() != 0;
        this.voteAverage = in.readDouble();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
