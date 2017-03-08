package com.telegram_bot.entity;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Greg on 10/9/16.
 */
@Entity
@Table(name = "Sessions", schema = "telegram")
public class Sessions {
    private int idSession;
    private int price;
    private Time time;
    private int idCinema;
    private int idMovie;
    private Integer idFormat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sessions sessions = (Sessions) o;

        if (idSession != sessions.idSession) return false;
        if (price != sessions.price) return false;
        if (idCinema != sessions.idCinema) return false;
        if (idMovie != sessions.idMovie) return false;
        if (time != null ? !time.equals(sessions.time) : sessions.time != null) return false;
        return idFormat != null ? idFormat.equals(sessions.idFormat) : sessions.idFormat == null;

    }

    @Override
    public int hashCode() {
        int result = idSession;
        result = 31 * result + price;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + idCinema;
        result = 31 * result + idMovie;
        result = 31 * result + (idFormat != null ? idFormat.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "idSession", nullable = false)
    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "idCinema", nullable = false)
    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    @Basic
    @Column(name = "idMovie", nullable = false)
    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    @Basic
    @Column(name = "idFormat", nullable = true)
    public Integer getIdFormat() {
        return idFormat;
    }

    public void setIdFormat(Integer idFormat) {
        this.idFormat = idFormat;
    }
}
