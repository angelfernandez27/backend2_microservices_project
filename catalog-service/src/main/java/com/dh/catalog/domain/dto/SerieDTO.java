package com.dh.catalog.domain.dto;

public class SerieDTO {
    private String id;
    private String name;
    private String genre;

    public SerieDTO(String id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    public SerieDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "SerieDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
