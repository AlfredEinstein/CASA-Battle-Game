import java.util.*;

public class ClassTypeAdvantage {
    private static final Map<String, List<String>> advantageMap = new HashMap<>();
    static {
        advantageMap.put("Militia", Arrays.asList("Spearman", "LightCavalry"));
        advantageMap.put("Spearman", Arrays.asList("LightCavalry", "HeavyCavalry"));
        advantageMap.put("LightCavalry", Arrays.asList("FootArcher", "CavalryArcher"));
        advantageMap.put("HeavyCavalry", Arrays.asList("Militia", "FootArcher", "LightCavalry"));
        advantageMap.put("CavalryArcher", Arrays.asList("Spearman", "HeavyCavalry"));
        advantageMap.put("FootArcher", Arrays.asList("Militia", "CavalryArcher"));
    }
    public static boolean hasAdvantage(String attacker, String defender) {
        return advantageMap.getOrDefault(attacker, new ArrayList<>()).contains(defender);
    }

}
