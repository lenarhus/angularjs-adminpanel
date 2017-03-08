package com.telegram_bot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Greg on 10/9/16.
 */
@Entity
@Table(name = "Movies", schema = "telegram")
public class Movies implements Serializable {

    private int idMovie;
    private String title;
    private String originalTitle;
    private Serializable productionYear;
    private String tagline;
    private Date releaseDate;
    private Short runtime;
    private String description;

    @Id
    @Column(name = "idMovie", nullable = false)
    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 250)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "original_title", nullable = true, length = 250)
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @Basic
    @Column(name = "tagline", nullable = true, length = 250)
    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    @Basic
    @Column(name = "release_date", nullable = true)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "runtime", nullable = true)
    public Short getRuntime() {
        return runtime;
    }

    public void setRuntime(Short runtime) {
        this.runtime = runtime;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movies movies = (Movies) o;

        if (idMovie != movies.idMovie) return false;
        if (title != null ? !title.equals(movies.title) : movies.title != null) return false;
        if (originalTitle != null ? !originalTitle.equals(movies.originalTitle) : movies.originalTitle != null)
            return false;
        if (productionYear != null ? !productionYear.equals(movies.productionYear) : movies.productionYear != null)
            return false;
        if (tagline != null ? !tagline.equals(movies.tagline) : movies.tagline != null) return false;
        if (releaseDate != null ? !releaseDate.equals(movies.releaseDate) : movies.releaseDate != null) return false;
        if (runtime != null ? !runtime.equals(movies.runtime) : movies.runtime != null) return false;
        if (description != null ? !description.equals(movies.description) : movies.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMovie;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
        result = 31 * result + (productionYear != null ? productionYear.hashCode() : 0);
        result = 31 * result + (tagline != null ? tagline.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
