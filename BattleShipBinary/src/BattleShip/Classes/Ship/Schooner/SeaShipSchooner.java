
package BattleShip.Classes.Ship.Schooner;

import BattleShip.Classes.Ship.SeaShip;

public class SeaShipSchooner extends SeaShip{
    
    int maxHealth=350;
    int speedP=90;
    
    int attackUpgrade[] = new int[]{25, 30, 40, 50, -1, 50, 100, 250};
    int defenseUpgrade[] = new int[]{25, 30, 40, 50, -1, 50, 150, 300};
    int bedsUpgrade[] = new int[]{5, 10, 15, 20, -1, 100, 200, 500};

    public SeaShipSchooner() {
    }
    
    public SeaShipSchooner(String name, String subname, int country, int saveSlot, int newGame) {
        super(name, subname, country, saveSlot, newGame);
        
        super.setHealth(100);
        
        super.setAttackP(attackUpgrade[0]);
        super.setDefenseP(defenseUpgrade[0]);
        
        super.setBed(0, bedsUpgrade[0]);
        super.setBed(1, bedsUpgrade[3]);
        super.setBed(2, bedsUpgrade[0]);
        
    }
    
    public SeaShipSchooner(String name, String subname, int country, int health, int armor, int cannon_ammo, int musket_ammo, int gold, double attackP, double defenseP, int[] beds, int saveSlot, int newGame) {
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
