package com.telegram_bot.entity;

import javax.persistence.*;

@Entity
@Table(name = "Users", schema = "telegram")
public class Users {
    private int idusers;
    private int user_id;
    private int chat_id;
    private Integer state;
    private String price;
    private String dist;
    private Double lat;
    private Double lon;
    private String city;
    private String street;
    private Integer build;
    private Integer movieState;
    private Integer sessionsState;
    private String restricted;
    private String username;
    private String lastseen;

    @Id
    @Column(name = "idusers", nullable = false)
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    @Basic
    @Column(name = "chat_id", nullable = false)
    public int getchat_id() {
        return chat_id;
    }

    public void setchat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "price", nullable = true, length = 45)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Basic
    @Column(name = "dist", nullable = true, length = 45)
    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    @Basic
    @Column(name = "lat", nullable = true, precision = 0)
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lon", nullable = true, precision = 0)
    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = true, length = 45)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "build", nullable = true)
    public Integer getBuild() {
        return build;
    }

    public void setBuild(Integer build) {
        this.build = build;
    }

    @Basic
    @Column(name = "movie_state", nullable = true)
    public Integer getMovieState() {
        return movieState;
    }

    public void setMovieState(Integer movieState) {
        this.movieState = movieState;
    }

    @Basic
    @Column(name = "sessions_state", nullable = true)
    public Integer getSessionsState() {
        return sessionsState;
    }

    public void setSessionsState(Integer sessionsState) {
        this.sessionsState = sessionsState;
    }

    @Basic
    @Column(name = "restricted", nullable = true, length = 1500)
    public String getRestricted() {
        return restricted;
    }

    public void setRestricted(String restricted) {
        this.restricted = restricted;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "lastseen", nullable = true, length = 50)
    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (idusers != users.idusers) return false;
        if (user_id != users.user_id) return false;
        if (chat_id != users.chat_id) return false;
        if (state != null ? !state.equals(users.state) : users.state != null) return false;
        if (price != null ? !price.equals(users.price) : users.price != null) return false;
        if (dist != null ? !dist.equals(users.dist) : users.dist != null) return false;
        if (lat != null ? !lat.equals(users.lat) : users.lat != null) return false;
        if (lon != null ? !lon.equals(users.lon) : users.lon != null) return false;
        if (city != null ? !city.equals(users.city) : users.city != null) return false;
        if (street != null ? !street.equals(users.street) : users.street != null) return false;
        if (build != null ? !build.equals(users.build) : users.build != null) return false;
        if (movieState != null ? !movieState.equals(users.movieState) : users.movieState != null) return false;
        if (sessionsState != null ? !sessionsState.equals(users.sessionsState) : users.sessionsState != null)
            return false;
        if (restricted != null ? !restricted.equals(users.restricted) : users.restricted != null) return false;
        if (username != null ? !username.equals(users.username) : users.username != null) return false;
        if (lastseen != null ? !lastseen.equals(users.lastseen) : users.lastseen != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idusers;
        result = 31 * result + user_id;
        result = 31 * result + chat_id;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (dist != null ? dist.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lon != null ? lon.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (build != null ? build.hashCode() : 0);
        result = 31 * result + (movieState != null ? movieState.hashCode() : 0);
        result = 31 * result + (sessionsState != null ? sessionsState.hashCode() : 0);
        result = 31 * result + (restricted != null ? restricted.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (lastseen != null ? lastseen.hashCode() : 0);
        return result;
    }
}
