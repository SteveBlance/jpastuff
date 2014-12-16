package com.codaconsultancy.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {

    private long id;
    private String name;
    private List<Player> players;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "team", targetEntity = Player.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
