package com.Battleship.Game.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Ranking implements Comparable<Ranking> {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int score;

    private long position;

    @OneToMany(mappedBy = "ranking")
    private List<Account> accounts;

    //Constructors
    public Ranking() {
    }

    public Ranking(int score, long position) {
        this.position = position;
        this.score = score;
    }

    //Getters and setters
    public long getId() {
        return id;
    }

    public long getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }

    public List<Account> getUsers() {
        return accounts;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUsers(List<Account> accounts) {
        this.accounts = accounts;
    }

    //Methods
    public void addUser(Account account) {
        account.setRanking(this);
        accounts.add(account);
    }

    public void incrementScore(int points) {
        this.score += points;
    }

    public void decrementScore(int points) {
        this.score -= points;
    }

    @Override
    public int compareTo(Ranking other) {
        // Comparar por puntaje descendente
        return Integer.compare(other.score, this.score);
    }
}
