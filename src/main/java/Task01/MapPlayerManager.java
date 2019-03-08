package Task01;

import java.util.*;

public class MapPlayerManager implements PlayerManager {

    private Map<String, Player> players = new HashMap<>();
    private Set<String> PlayerGamesOnManager = new HashSet<>();
    private Map<String, Map<String, Integer>> playerRatingInGames = new HashMap<>();
    private Map<String, Integer> playerRatingInSite = new HashMap<>();


    public boolean addPlayer(Player player) {
        String nickName = player.getNickName();

        if (players.get(nickName) == null) {
            players.put(nickName, player);
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    private void addOnPlayerGamesOnManager() {
        players.values().stream().forEach(x -> PlayerGamesOnManager.addAll(x.getGamesList()));
    }

    public Set<String> getPlayerGamesOnManager() {
        addOnPlayerGamesOnManager();
        return PlayerGamesOnManager;
    }

    public void printAllPlayerGamesOnSite() {
        addOnPlayerGamesOnManager();
        System.out.println("Список игр всех игроков на сайте :");
        PlayerGamesOnManager.stream().forEach(out -> System.out.println(out));
    }

    public void printRatingPlayerOnGame(String nickName, String nameGame) {
        Integer ratingGame = players.get(nickName).getRatingGame(nameGame.toUpperCase());
        if (ratingGame == null) {
            System.out.printf("%s не играет в %s", nickName, nameGame.toUpperCase());
        } else {
            System.out.printf("Рейтинг %s в %s: %d%n", nickName, nameGame.toUpperCase(), ratingGame);
        }
    }

    public void addRatingPointPlayerOnGame(String nickName, String nameGame, Integer valueRating) {
        players.get(nickName).addRatingPoint(nameGame, valueRating);
    }

    public void printTop10PlayerOnGame(String nameGame) {

        Map<String, Integer> playerGamesRating = new HashMap<String, Integer>();

        playerRatingInGames.put(nameGame, playerGamesRating);

        players.values().stream().forEach(x ->
                playerGamesRating
                        .put(x.getNickName(), x.getRatingGame(nameGame.toUpperCase())));

        System.out.printf("Топ 10 игроков в %s: %n",nameGame.toUpperCase());

        playerRatingInGames.get(nameGame).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10).forEach(x -> System.out.println(x.getKey() + " = " + x.getValue()));
    }

    public void printTop10PlayerOnSite() {


        players.values().stream().forEach(x ->
                playerRatingInSite
                        .put(x.getNickName(),
                                x.getPlayerGamesRating().values().stream().mapToInt(Integer::intValue).sum()));

        System.out.println("Топ 10 игроков на сайте: ");
        playerRatingInSite.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10).forEach(x ->
                System.out.println(x.getKey() + " = " + x.getValue()));
    }
}
