package com.auguryrock.luv4s.domain;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class WvWMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    protected Colour colour;
    @ManyToOne
    @JoinColumn(name ="match_id")
    protected Matchup matchup;
    @OneToMany(cascade = CascadeType.ALL)
    protected Set<Objective> objectives = new TreeSet<>();

    public Set<Objective> getObjectives() {
        return objectives;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Integer getPk() {
        return pk;
    }

    public WvWMap() {
    }

    public WvWMap(Colour colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WvWMap wvWMap = (WvWMap) o;

        if (pk != null ? !pk.equals(wvWMap.pk) : wvWMap.pk != null) return false;
        if (colour != wvWMap.colour) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        return result;
    }

    public void setMatchup(Matchup matchup) {
        this.matchup = matchup;
    }

    public Matchup getMatchup() {
        return matchup;
    }

    public void addOjective(Objective objective) {
        objectives.add(objective);
        objective.setMap(this);
    }
}
