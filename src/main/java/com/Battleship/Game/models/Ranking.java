package com.Battleship.Game.models;

import jakarta.persistence.*;

@Entity
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private long score;

    private long position;

    @ManyToOne
    @JoinColumn(name = "User_Id")
    private User user;

    public Ranking (){}

    public Ranking (long score, long position){
        this.position = position;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public long getPosition() {
        return position;
    }

    public long getScore() {
        return score;
    }

    public User getUser() {
        return user;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
