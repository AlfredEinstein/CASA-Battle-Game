import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String playerInput = "Spearman#10;Militia#30;FootArcher#20;LightCavalry#1000;HeavyCavalry#120";
        String enemyInput = "Militia#10;Spearman#10;FootArcher#1000;LightCavalry#120;CavalryArcher#100";

        List<Platoon> playerPlatoons = parseInput(playerInput);
        List<Platoon> enemyPlatoons = parseInput(enemyInput);

        BattleStrategy strategy = new BattleStrategy(playerPlatoons, enemyPlatoons);
        List<String> arrangement = strategy.findWinningArrangement();

        if (arrangement != null) {
            System.out.println("Winning Arrangement:");
            arrangement.forEach(System.out::println);
        } else {
            System.out.println("There is no chance of winning.");
        }
    }

    private static List<Platoon> parseInput(String input) {
        List<Platoon> platoons = new ArrayList<>();
        String[] parts = input.split(";");
        for (String part : parts) {
            String[] info = part.split("#");
            platoons.add(new Platoon(info[0], Integer.parseInt(info[1])));
        }
        return platoons;
    }
}