import java.util.ArrayList;
import java.util.List;

public class BattleStrategy {
    List<Platoon> playerPlatoons;
    List<Platoon> enemyPlatoons;

    BattleStrategy(List<Platoon> player, List<Platoon> enemy){
        this.playerPlatoons=player;
        this.enemyPlatoons=enemy;
    }
    // Try all permutations to find a winning setup
    public List<String> findWinningArrangement() {
        List<List<Platoon>> permutations = generatePermutations(playerPlatoons);
        for (List<Platoon> arrangement : permutations) {
            int wins = 0;
            for (int i = 0; i < arrangement.size(); i++) {
                if (arrangement.get(i).beats(enemyPlatoons.get(i))) {
                    wins++;
                }
            }
            if (wins >= 3) {
                List<String> result = new ArrayList<>();
                for (int i = 0; i < arrangement.size(); i++) {
                    result.add(arrangement.get(i) + " vs " + enemyPlatoons.get(i));
                }
                return result;
            }
        }
        return null;
    }

    // Generate all permutations of platoons
    private List<List<Platoon>> generatePermutations(List<Platoon> original) {
        if (original.isEmpty()) {
            List<List<Platoon>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        Platoon firstElement = original.removeFirst();
        List<List<Platoon>> returnValue = new ArrayList<>();
        List<List<Platoon>> permutations = generatePermutations(original);
        for (List<Platoon> smaller : permutations) {
            for (int index = 0; index <= smaller.size(); index++) {
                List<Platoon> temp = new ArrayList<>(smaller);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        original.addFirst(firstElement);
        return returnValue;
    }

}
