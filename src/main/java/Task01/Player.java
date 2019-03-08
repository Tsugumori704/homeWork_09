package Task01;

import java.util.*;

public class Player {

    private String nickName;
    private Set<String> gamesList = new HashSet<String>();
    private Map<String, Integer> playerGamesRating = new HashMap<String, Integer>();


    public Player(String nickName) {
        this.nickName = nickName;

    }

    public String getNickName() {
        return nickName;
    }

    public Set<String> getGamesList() {
        return gamesList;
    }

    public Integer getRatingGame(String nameGame) {
        return playerGamesRating.get(nameGame.toUpperCase());
    }

    public Map<String, Integer> getPlayerGamesRating() {
        return playerGamesRating;
    }

    public void addGame(String nameGame) {
        gamesList.add(nameGame.toUpperCase());
        playerGamesRating.put(nameGame.toUpperCase(), 0);
    }

    public void addRatingPoint(String nameGame, int valueRating) {
        if (playerGamesRating.get(nameGame.toUpperCase()) == null) {
            playerGamesRating.put(nameGame.toUpperCase(), valueRating);
        } else {
            Integer ratingPlayer = playerGamesRating.get(nameGame.toUpperCase());
            ratingPlayer += valueRating;
            playerGamesRating.put(nameGame.toUpperCase(), ratingPlayer);

        }
    }

}
