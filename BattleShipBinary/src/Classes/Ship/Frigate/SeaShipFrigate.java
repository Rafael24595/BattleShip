
package Classes.Ship.Frigate;

import Classes.Ship.SeaShip;

public class SeaShipFrigate extends SeaShip{
    
    int maxHealth=350;
    int speedP=80;
    
    int attackUpgrade[] = new int[]{45, 55, 65, 75, -1, 200, 400, 800};
    int defenseUpgrade[] = new int[]{45, 55, 65, 75, -1, 200, 400, 600};
    int bedsUpgrade[] = new int[]{10, 15, 20, 25, -1, 100, 300, 700};

    public SeaShipFrigate() {
    }
    
    public SeaShipFrigate(String name, String subname, int country, int saveSlot, int newGame) {
        super(name, subname, country, saveSlot, newGame);
        
        super.setHealth(200);
        
        super.setAttackP(attackUpgrade[0]);
        super.setDefenseP(defenseUpgrade[0]);
        
        super.setBed(0, bedsUpgrade[0]);
        super.setBed(1, bedsUpgrade[3]);
        super.setBed(2, bedsUpgrade[0]);
        
    }
    
    public SeaShipFrigate(String name, String subname, int country, int health, int armor, int cannon_ammo, int musket_ammo, int gold, double attackP, double defenseP, int[] beds, int saveSlot, int newGame) {
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
