import java.util.*;

public class BattleStrategy {
    private final List<Platoon> playerPlatoons;
    private final List<Platoon> enemyPlatoons;

    public BattleStrategy(List<Platoon> player, List<Platoon> enemy) {
        this.playerPlatoons = player;
        this.enemyPlatoons = enemy;
    }

    public ArrangementResult findBestArrangementWithOutcome() {
        List<List<Platoon>> permutations = getPermutations(playerPlatoons);
        ArrangementResult bestResult = null;

        for (List<Platoon> perm : permutations) {
            int wins = 0, draws = 0;
            List<String> outcomes = new ArrayList<>();

            for (int i = 0; i < perm.size(); i++) {
                Platoon my = perm.get(i);
                Platoon enemy = enemyPlatoons.get(i);
                int myPower = my.effectivePowerAgainst(enemy);
                int enemyPower = enemy.getSoldiers();

                if (myPower > enemyPower) {
                    outcomes.add("Win");
                    wins++;
                } else if (myPower == enemyPower) {
                    outcomes.add("Draw");
                    draws++;
                } else {
                    outcomes.add("Loss");
                }
            }

            if (bestResult == null || wins > bestResult.wins ||
                    (wins == bestResult.wins && draws > bestResult.draws)) {
                bestResult = new ArrangementResult(perm, outcomes, wins, draws);
            }

            if (wins == 5) break; // optimal exit
        }

        return bestResult;
    }

    private List<List<Platoon>> getPermutations(List<Platoon> original) {
        List<List<Platoon>> result = new ArrayList<>();
        permuteHelper(original, 0, result);
        return result;
    }

    private void permuteHelper(List<Platoon> list, int index, List<List<Platoon>> result) {
        if (index == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < list.size(); i++) {
            Collections.swap(list, i, index);
            permuteHelper(list, index + 1, result);
            Collections.swap(list, i, index);
        }
    }

    public static class ArrangementResult {
        public final List<Platoon> arrangement;
        public final List<String> battleOutcomes;
        public final int wins;
        public final int draws;

        public ArrangementResult(List<Platoon> arrangement, List<String> battleOutcomes, int wins, int draws) {
            this.arrangement = arrangement;
            this.battleOutcomes = battleOutcomes;
            this.wins = wins;
            this.draws = draws;
        }
    }
}
