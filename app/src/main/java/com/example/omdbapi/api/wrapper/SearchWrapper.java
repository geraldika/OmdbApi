package com.example.omdbapi.api.wrapper;

import com.example.omdbapi.main.model.Film;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchWrapper {

    @SerializedName("Search")
    @Expose
    private List<Film> filmList;

    public List<Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }
}
