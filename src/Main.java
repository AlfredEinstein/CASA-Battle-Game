import java.util.*;

public class Main {
    public static void main(String[] args) {

/*      TestCase 1:
        player = Militia#50;Spearman#60;FootArcher#90;LightCavalry#70;HeavyCavalry#80
        Enemy = Spearman#30;FootArcher#100;Militia#40;CavalryArcher#40;LightCavalry#30
        ExpectedOutput = Militia#50;Spearman#60;FootArcher#90;LightCavalry#70;HeavyCavalry#80

        TestCase 2:
        Player = Militia#20;Spearman#20;FootArcher#20;LightCavalry#20;HeavyCavalry#20
        Enemy = Militia#100;Spearman#100;FootArcher#100;LightCavalry#100;CavalryArcher#100
        ExpectedOutput = There is no chance of winning

        TestCase 3:
        Player = FootArcher#100;HeavyCavalry#60;Militia#100;Spearman#60;CavalryArcher#50
        Enemy = CavalryArcher#80;Militia#30;LightCavalry#30;HeavyCavalry#30;Spearman#30
        ExpectedOutput = FootArcher#100;Militia#100;Spearman#60;CavalryArcher#50;HeavyCavalry#60

        TestCase 4:
        Player = Militia#10;Spearman#20;LightCavalry#30;HeavyCavalry#40;FootArcher#50
        Enemy = Militia#10;Spearman#20;LightCavalry#30;HeavyCavalry#40;FootArcher#50
        ExpectedOutput = Militia#10;LightCavalry#30;Spearman#20;FootArcher#50;HeavyCavalry#40
*/

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Platoons in below format \nSpearman#10;Militia#30;platoonClass#NoOfSoldiers");

        System.out.println("Enter your platoons:");
        String playerInput = scanner.nextLine().trim();

        System.out.println("Enter enemy platoons:");
        String enemyInput = scanner.nextLine().trim();

        List<Platoon> playerPlatoons = parsePlatoons(playerInput);
        List<Platoon> enemyPlatoons = parsePlatoons(enemyInput);

        if (playerPlatoons.size() != 5 || enemyPlatoons.size() != 5) {
            System.out.println("Each side must have exactly 5 platoons.");
            return;
        }

        BattleStrategy strategy = new BattleStrategy(playerPlatoons, enemyPlatoons);
        BattleStrategy.ArrangementResult result = strategy.findBestArrangementWithOutcome();

        if (result.wins >= 3) {
            System.out.println("Battle Outcomes:");
            for (int i = 0; i < 5; i++) {
                System.out.printf("Battle %d: %s vs %s → %s%n",
                        i + 1,
                        result.arrangement.get(i).toString(),
                        enemyPlatoons.get(i).toString(),
                        result.battleOutcomes.get(i));
            }
            System.out.println("\nBest winning arrangement:");
            System.out.println(joinPlatoons(result.arrangement));
        }
        else {
            System.out.println("There is no chance of winning");
        }
    }

    static List<Platoon> parsePlatoons(String input) {
        List<Platoon> platoons = new ArrayList<>();
        try {
            for (String s : input.split(";")) {
                String[] parts = s.split("#");
                if (parts.length != 2) throw new IllegalArgumentException("Invalid format");
                platoons.add(new Platoon(parts[0], Integer.parseInt(parts[1])));
            }
        } catch (Exception e) {
            System.out.println("Error parsing platoons. Format should be ClassName#Count;ClassName#Count;...");
            System.exit(1);
        }
        return platoons;
    }

    static String joinPlatoons(List<Platoon> platoons) {
        List<String> parts = new ArrayList<>();
        for (Platoon p : platoons) {
            parts.add(p.toString());
        }
        return String.join(";", parts);
    }
}
