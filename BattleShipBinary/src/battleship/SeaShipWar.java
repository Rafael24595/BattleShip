
package battleship;

public class SeaShipWar extends SeaShip {
    
    int maxHealth=350;
    int speedP=60;

    int attackUpgrade[] = new int[]{ 70, 80, 90, 100, -1, 300, 500, 1000};
    int defenseUpgrade[] = new int[]{ 70, 80, 90, 100, -1, 250, 500, 700};
    int bedsUpgrade[] = new int[]{15, 25, 35, 40, -1, 300, 500, 1000};

    public SeaShipWar() {
    }
    
    public SeaShipWar(String name, String subname, int country, int saveSlot, int newGame) {
        super(name, subname, country, saveSlot, newGame);
        
        super.setHealth(350);
        
        super.setAttackP(attackUpgrade[0]);
        super.setDefenseP(defenseUpgrade[0]);
        
        super.setBed(0, bedsUpgrade[0]);
        super.setBed(1, bedsUpgrade[3]);
        super.setBed(2, bedsUpgrade[0]);
        super.setBed(3, 0);

    }
    
    public SeaShipWar(String name, String subname, int country, int health, int armor, int cannon_ammo, int musket_ammo, int gold, double attackP, double defenseP, int[] beds, int saveSlot, int newGame) {
        super(name, subname, country, health, armor, cannon_ammo, musket_ammo, gold, attackP, defenseP, beds, saveSlot, newGame);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getSpeedP() {
        return speedP;
    }
    
    public int[] getAttackUpgrade() {
        return attackUpgrade;
    }

    public int[] getDefenseUpgrade() {
        return defenseUpgrade;
    }

    public int[] getBedsUpgrade() {
        return bedsUpgrade;
    }
    
    public void fixBoat(int i) {

        setHealth(getHealth() + i);
        
        setGold(getGold() - i * getPrices()[3]);
        
    }
    
    public void upgradeAttack(int i) {

        setAttackP(getAttackUpgrade()[i]);
                            
        setGold(getGold() - getAttackUpgrade()[i+4]);
        
    }

    public void upgradeBeds(int i) {

        setBed(0,getBedsUpgrade()[i]);
                            
        setGold(getGold() - getBedsUpgrade()[i+4] );
        
    }

    public void upgradeDefense(int i) {

        setDefenseP(getDefenseUpgrade()[i]);
                            
        setGold(getGold() - getDefenseUpgrade()[i+4]);
        
    }
    
}
