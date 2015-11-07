package org.utn.edu.ar.model.domain;

import org.junit.Assert;
import org.junit.Test;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.util.Coordinates;

import java.util.Date;

public class MatchTest {

    private Match testMatch = new Match(1, 1, 2, new Date(), null, new Coordinates(1.0D, 1.0D));

    private Player p1 = new Player(1, "Leo");
    private Player p2 = new Player(2, "Tom");
    private Player p3 = new Player(3, "Nico");

    @Test
    public void addTest() throws PlayerAlreadyConfirmedException {
        testMatch.addPlayer(p1);
        testMatch.addPlayer(p2);
        testMatch.addPlayer(p3);
        Assert.assertEquals(2, testMatch.getStarters().size());
        Assert.assertEquals(1, testMatch.getAlternates().size());
    }

    @Test
    public void failToAddTest() throws PlayerAlreadyConfirmedException {
        testMatch.addPlayer(p1);
        testMatch.addPlayer(p1);
    }
}
