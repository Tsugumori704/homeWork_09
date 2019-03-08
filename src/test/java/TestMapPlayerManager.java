import Task01.MapPlayerManager;
import Task01.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class TestMapPlayerManager {

    @Test
    public void testAddPlayer() throws Exception {
        //Given
        Player player1 = new Player("Tsugumori704");
        Player player2 = new Player("Sigurd");
        Player player3 = new Player("Gizmo");
        Player player4 = new Player("Gizmo");
        Player player5 = new Player("Overlord");

        MapPlayerManager underTest = new MapPlayerManager();
        //When

        Assert.assertTrue(underTest.addPlayer(player1));
        Assert.assertTrue(underTest.addPlayer(player2));
        Assert.assertTrue(underTest.addPlayer(player3));
        Assert.assertFalse(underTest.addPlayer(player4));
        Assert.assertTrue(underTest.addPlayer(player5));

        Player playerMyTest = underTest.getPlayers().get(player1.getNickName());

        Assert.assertEquals(4, underTest.getPlayers().size());

        //Then
        //no exceptions thrown
    }

    @Test
    public void testAddPlayerGames() throws Exception {
        //Given

        Player player1 = new Player("Sigurd");

        //When

        player1.addGame("StarCraft");
        player1.addGame("Subnautica");
        player1.addGame("FrostPunk");

        Set<String> gamesList = player1.getGamesList();
        Assert.assertEquals(3, gamesList.size());

        //Then
        //no exceptions thrown
    }

    @Test
    public void testAddPlayerRatingGamesOnPlayer() throws Exception {
        //Given

        Player player1 = new Player("Sigurd");

        //When

        String starCraft = "StarCraft";
        player1.addGame(starCraft);
        String subnautica = "Subnautica";
        player1.addGame(subnautica);


        player1.addRatingPoint(starCraft, 10);
        player1.addRatingPoint(subnautica, 50);


        Assert.assertEquals(10, (int) player1.getRatingGame(starCraft));
        Assert.assertEquals(50, (int) player1.getRatingGame(subnautica));

        //Then
        //no exceptions thrown
    }


    @Test
    public void testPrintAllGamesOnSite() throws Exception {
        //Given

        Player player1 = new Player("Tsugumori704");
        Player player2 = new Player("Sigurd");
        Player player3 = new Player("Gizmo");

        MapPlayerManager underTest = new MapPlayerManager();

        underTest.addPlayer(player1);
        underTest.addPlayer(player2);
        underTest.addPlayer(player3);

        player1.addGame("StarCraft");
        player1.addGame("Subnautica");
        player1.addGame("FrostPunk");

        player2.addGame("StarCraft");
        player2.addGame("Warhammer 40k");
        player2.addGame("ForHonor");

        player3.addGame("ForHonor");
        player3.addGame("XCOM2");
        player3.addGame("BATTLETECH");


        //When

        underTest.printAllPlayerGamesOnSite();

        //Then
        //no exceptions thrown
    }


    @Test
    public void testPrintRatingPlayerOnGames() throws Exception {
        //Given

        Player player1 = new Player("Tsugumori704");

        MapPlayerManager underTest = new MapPlayerManager();

        underTest.addPlayer(player1);

        player1.addGame("StarCraft");
        player1.addGame("Subnautica");
        player1.addGame("FrostPunk");

        underTest.addRatingPointPlayerOnGame(player1.getNickName(), "Starcraft", 50);
        underTest.addRatingPointPlayerOnGame(player1.getNickName(), "Starcraft", 250);
        underTest.addRatingPointPlayerOnGame(player1.getNickName(), "Starcraft", 200);

        //When

        underTest.printRatingPlayerOnGame(player1.getNickName(), "starcraft");

        Assert.assertEquals(500, (int) player1.getRatingGame("Starcraft"));

        //Then
        //no exceptions thrown
    }

    @Test
    public void testPrintTop10PlayerOnGames() throws Exception {
        //Given

        Player player1 = new Player("Tsugumori704");
        Player player2 = new Player("Sigurd");
        Player player3 = new Player("Gizmo");

        MapPlayerManager underTest = new MapPlayerManager();

        underTest.addPlayer(player1);
        underTest.addPlayer(player2);
        underTest.addPlayer(player3);

        player1.addGame("StarCraft");
        player2.addGame("StarCraft");
        player3.addGame("StarCraft");

        underTest.addRatingPointPlayerOnGame(player1.getNickName(), "Starcraft", 50);
        underTest.addRatingPointPlayerOnGame(player2.getNickName(), "Starcraft", 250);
        underTest.addRatingPointPlayerOnGame(player3.getNickName(), "Starcraft", 200);

        //When

        underTest.printTop10PlayerOnGame("Starcraft");

        //Then
        //no exceptions thrown
    }

    @Test
    public void testPrintTop10PlayerOnSite() throws Exception {
        //Given

        Player player1 = new Player("Tsugumori704");
        Player player2 = new Player("Sigurd");
        Player player3 = new Player("Gizmo");
        Player player4 = new Player("Gizmo1");
        Player player5 = new Player("Gizmo2");
        Player player6 = new Player("Gizmo3");
        Player player7 = new Player("Gizmo4");
        Player player8 = new Player("Gizmo5");
        Player player9 = new Player("Gizmo6");
        Player player10 = new Player("Gizmo7");
        Player player11 = new Player("Gizmo8");
        Player player12 = new Player("Gizmo9");
        Player player13 = new Player("Gizmo10");


        MapPlayerManager underTest = new MapPlayerManager();

        underTest.addPlayer(player1);
        underTest.addPlayer(player2);
        underTest.addPlayer(player3);
        underTest.addPlayer(player4);
        underTest.addPlayer(player5);
        underTest.addPlayer(player6);
        underTest.addPlayer(player7);
        underTest.addPlayer(player8);
        underTest.addPlayer(player9);
        underTest.addPlayer(player10);
        underTest.addPlayer(player11);
        underTest.addPlayer(player12);
        underTest.addPlayer(player13);

        player1.addGame("StarCraft");
        player1.addGame("FrostPunk");

        player2.addGame("StarCraft");
        player2.addGame("Warhammer 40k");
        player2.addGame("ForHonor");

        player3.addGame("ForHonor");
        player3.addGame("Starcraft");

        player4.addGame("Starcraft");
        player5.addGame("Starcraft");
        player6.addGame("Starcraft");
        player7.addGame("Starcraft");
        player8.addGame("Starcraft");
        player9.addGame("Starcraft");
        player10.addGame("Starcraft");
        player11.addGame("Starcraft");
        player12.addGame("Starcraft");
        player13.addGame("Starcraft");


        underTest.addRatingPointPlayerOnGame(player1.getNickName(), "Starcraft", 50);
        underTest.addRatingPointPlayerOnGame(player2.getNickName(), "Starcraft", 250);
        underTest.addRatingPointPlayerOnGame(player2.getNickName(), "Warhammer 40k", 250);
        underTest.addRatingPointPlayerOnGame(player3.getNickName(), "Starcraft", 200);
        underTest.addRatingPointPlayerOnGame(player3.getNickName(), "ForHonor", 500);
        underTest.addRatingPointPlayerOnGame(player4.getNickName(), "Starcraft", 123);
        underTest.addRatingPointPlayerOnGame(player5.getNickName(), "Starcraft", 23);
        underTest.addRatingPointPlayerOnGame(player6.getNickName(), "Starcraft", 54);
        underTest.addRatingPointPlayerOnGame(player7.getNickName(), "Starcraft", 25);
        underTest.addRatingPointPlayerOnGame(player8.getNickName(), "Starcraft", 14);
        underTest.addRatingPointPlayerOnGame(player9.getNickName(), "Starcraft", 789);
        underTest.addRatingPointPlayerOnGame(player10.getNickName(), "Starcraft", 5);
        underTest.addRatingPointPlayerOnGame(player11.getNickName(), "Starcraft", 1);
        underTest.addRatingPointPlayerOnGame(player12.getNickName(), "Starcraft", 234);
        underTest.addRatingPointPlayerOnGame(player13.getNickName(), "Starcraft", 166);

        //When

        underTest.printTop10PlayerOnSite();

        //Then
        //no exceptions thrown
    }


}
