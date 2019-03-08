package Task01;

import Task01.Player;

public interface PlayerManager {

    public boolean addPlayer(Player player);

    public void addRatingPointPlayerOnGame(String nickName, String nameGame, Integer valueRating);

    public void printAllPlayerGamesOnSite();

    public void printRatingPlayerOnGame(String nickName, String nameGame);

    public void printTop10PlayerOnGame(String nameGame);

    public void printTop10PlayerOnSite();


}
