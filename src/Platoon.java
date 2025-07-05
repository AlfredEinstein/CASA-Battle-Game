public class Platoon {
    private final String unitType;
    private int soldiers;

    Platoon(String unitType, int soldiers) {
        this.unitType = unitType;
        this.soldiers = soldiers;
    }
    int effectivePowerAgainst(Platoon opponent) {
        if (ClassTypeAdvantage.hasAdvantage(this.unitType, opponent.unitType)) {
            return this.soldiers * 2;
        }
        return this.soldiers;
    }

    public void setSoldiers(int soldiers){
        this.soldiers=soldiers;
    }

    public int getSoldiers() {
        return this.soldiers;
    }

    boolean beats(Platoon opponent) {
        return effectivePowerAgainst(opponent) > opponent.soldiers;
    }
    @Override
    public String toString() {
        return unitType + "#" + soldiers;
    }
}
