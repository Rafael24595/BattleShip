
package battleship;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class SeaShip {
    
    ArrayList<Worker> worker_list;
    private String name;
    private String subname;
    private int country;
    private final int attack = 100;
    private final int defense = 75;
    private final int speed = 10;
    private final int defensePlus [] = {15, 2};
    private int health;
    private int armor;
    private int cannon_ammo;
    private int musket_ammo;
    private int gold;
    
    private int turn;
    private int maxHealth;
    private int maxArmor = 100;
    private int maxAmmo [] = {20, 20};
    private int defend [] = new int [2];
    
    private double attackP;
    private double defenseP;
    private int speedP;
    private int beds []; /*First for all beds - Second for ocupied beds*/
    
    private String countryS [] = {"España", "Francia", "Gran Bretaña"};
    
    private int saveSlot;
    
    private int newGame;
    
    private int attackUpgrade[];
    private int defenseUpgrade[];
    private int bedsUpgrade[];
    
    private int lot[] = {10, 10, 25};
    private int prices [] = {100, 50, 25, 2, 100};

    public SeaShip() {
    }
    
    /*New Game Constructor*/
    
    public SeaShip(String name, String subname, int country, int saveSlot, int newGame) {
       
        worker_list = new ArrayList<Worker>();
        
        this.name = name;
        this.subname = subname;
        this.country = country;
        
        health = 100;
        armor = 0;
        cannon_ammo = 10;
        musket_ammo = 10;
        gold = 100;
        
        turn = 0;
        
        attackP = 0;
        defenseP = 0;
        speedP = 0;
        beds = new int [4];
        
        this.saveSlot = saveSlot;
        this.newGame = newGame;
    }

    /*Load Constructor*/
    
    public SeaShip(String name, String subname, int country, int health, int armor, int cannon_ammo, int musket_ammo, int gold, double attackP, double defenseP, int[] beds, int saveSlot, int newGame) {
       
        this.worker_list = new ArrayList<Worker>();
        
        this.name = name;
        this.subname = subname;
        this.country = country;
        
        this.health = health;
        this.armor = armor;
        this.cannon_ammo = cannon_ammo;
        this.musket_ammo = musket_ammo;
        this.gold = gold;
        
        turn = 0;
        
        this.attackP = attackP;
        this.defenseP = defenseP;
        this.speedP = speedP;
        this.beds = beds;
        
        this.saveSlot = saveSlot;
        this.newGame = newGame;
    }

    public ArrayList<Worker> getWorker_list() {
        return worker_list;
    }

    public String getName() {
        return name;
    }

    public String getSubname() {
        return subname;
    }

    public int getCountry() {
        return country;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public int getCannon_ammo() {
        return cannon_ammo;
    }

    public int getMusket_ammo() {
        return musket_ammo;
    }

    public int getGold() {
        return gold;
    }

    public int getTurn() {
        return turn;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxArmor() {
        return maxArmor;
    }

    public int[] getMaxAmmo() {
        return maxAmmo;
    }

    public int[] getDefend() {
        return defend;
    }
    
    public double getAttackP() {
        return attackP;
    }

    public double getDefenseP() {
        return defenseP;
    }

    public int getSpeedP() {
        return speedP;
    }

    public int [] getDefensePlus() {
        return defensePlus;
    }

    public int[] getBeds() {
        return beds;
    }

    public String[] getCountryS() {
        return countryS;
    }

    public int getSaveSlot() {
        return saveSlot;
    }

    public int getNewGame() {
        return newGame;
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

    public int[] getLot() {
        return lot;
    } 
    
    public int[] getPrices() {
        return prices;
    }
    
    public void setWorker_list(ArrayList<Worker> worker_list) {
        this.worker_list = worker_list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }
    
    public void setCountry(int country) {
        this.country = country;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setCannon_ammo(int cannon_ammo) {
        this.cannon_ammo = cannon_ammo;
    }

    public void setMusket_ammo(int musket_ammo) {
        this.musket_ammo = musket_ammo;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    public void setTurn() {
        turn=(int)((double)getSpeed() * (1 - ((double)getSpeedP()/100) * ((double)getBeds()[2]/(double)getBeds()[1])));        
    }

    public void setDefend(int i) {
        
        defend [1] = i;
        
        if(i > 0){
            defend [0] = defensePlus[0];
        }
        else{
            defend [0] = 0;
        }

    }

    public void setAttackP(double attackP) {
        this.attackP = attackP;
    }

    public void setDefenseP(double defenseP) {
        this.defenseP = defenseP;
    }
    
    public void setBed(int position, int value) {
        beds[position] = value;
    }
    
    public void setBeds(int[] beds) {
        for (int i = 0; i < this.beds.length; i++) {
            this.beds[i] = beds[i];
        }
    }

    public void setCountryS(String[] countryS) {
        this.countryS = countryS;
    }

    public abstract void fixBoat(int i);
    
    public abstract void upgradeAttack(int i);

    public abstract void upgradeBeds(int i);
    
    public abstract void upgradeDefense(int i);
    
    public SeaShip createDummyShip(int random) {
        
        Scanner keyboard = new Scanner(System.in);
        
        SeaShipSchooner CPU = new SeaShipSchooner("Null", "Null", -1, -1, -1);
        
        ArrayList data = new ArrayList();
        
        ArrayList<SeaShip> CPU_Boat = new ArrayList<SeaShip>();
        
        CPU_Boat.add(0, CPU);
        
        int level = 0;
        
        int option = 0;
        
        while (option < 1 || option > 4){
            
            if(random != 1){
                
                System.out.println("\n¿Contra qué clase de navío quieres luchar? \n1.-Goleta \n2.-Fragata \n3.-Buque \n4.-Atrás \n");
            
                option = keyboard.nextInt();
                
            }
            else{
                
                option = (int)(Math.random() * 3) +1;
                
            }
            
            
            
            int country = getCountry();
            
            while(country == getCountry()){
                
                country = (int)(Math.random() * 3);
                
            }
            
            data.add(0 , worker_list.get(0).getNameS()[(int)(Math.random()*20)]);
            
            data.add(1 , worker_list.get(0).getSubnameS()[(int)(Math.random()*20)]);
            
            data.add(2, country);
            
            data.add(3, -1);
            
            data.add(4, 0);
            
            while((level < 1 || level > 4) && option != 4){
                
                if (random != 1){
                    
                    System.out.println("\n¿A qué nivel quieres que se encuentre? \n1.-Mínimo \n2.-Medio \n3.-Alto \n4.-Atrás\n");
                
                    level = keyboard.nextInt();
                    
                    if (level == 4){
                    
                    option = 0;
                    
                    }
                    
                }
                else{
                    
                    level = (int)(Math.random() * 2) +1;
                    
                }

            switch(option){
                
                case 1:
                   
                    SeaShipSchooner CPU_Schooner = new SeaShipSchooner((String)data.get(0), (String)data.get(1), (int)data.get(2), (int)data.get(3), (int)data.get(4));
                    
                    CPU_Boat.add(0, CPU_Schooner);
                    
                break;
                
                case 2:
                   
                    SeaShipFrigate CPU_Frigate= new SeaShipFrigate((String)data.get(0), (String)data.get(1), (int)data.get(2), (int)data.get(3), (int)data.get(4));
                    
                    CPU_Boat.add(0, CPU_Frigate);
                    
                break;
                
                case 3:
                   
                    SeaShipWar CPU_War= new SeaShipWar((String)data.get(0), (String)data.get(1), (int)data.get(2), (int)data.get(3), (int)data.get(4));
                    
                    CPU_Boat.add(0, CPU_War);
                    
                break;
                
            }
            
            int gold = (int)(Math.random() * 101) + 50;
            
            int upgrade [] = new int [3];
            
            switch(level){
            
            case 1:
                
                upgrade[0] = 0;
                
                upgrade[1] = 0;
                
                upgrade[2] = gold;
                
            break;
            
            case 2:
                
                upgrade[0] = 1;
                
                upgrade[1] = 50;
                
                upgrade[2] = gold * 2;
                
            break;
            
            case 3:
                
                upgrade[0] = 3;
                
                upgrade[1] = 100;
                
                upgrade[2] = gold * 3;
                
            break;
            
        }
            
                CPU_Boat.get(0).setAttackP(CPU_Boat.get(0).getAttackUpgrade()[upgrade[0]]);
                
                CPU_Boat.get(0).setDefenseP(CPU_Boat.get(0).getDefenseUpgrade()[upgrade[0]]);
                
                CPU_Boat.get(0).setBed(0, CPU_Boat.get(0).getBedsUpgrade()[upgrade[0]]);
                
                CPU_Boat.get(0).setBed(2, CPU_Boat.get(0).getBedsUpgrade()[upgrade[0]]);
                
                CPU_Boat.get(0).setArmor(upgrade[1]);
                
                CPU_Boat.get(0).setGold(upgrade[2]);
                
                CPU_Boat.get(0).setCannon_ammo(30);
                
                 CPU_Boat.get(0).setMusket_ammo(30);
        
                CPU_Boat.get(0).createTripulation();
        
                System.out.println(CPU_Boat.get(0).toString());
        
           } 
        } 
        
        return CPU_Boat.get(0);
    }
    
    public void createTripulation() {
        
        int range;
        int name;
        int subname;

        Worker worker = new Worker(20, 20, 0);
        
        worker.createCaptain(this.name, this.subname);
        
        worker_list.add(worker);

        for (int i = 1; i < beds[2]; i++) {
            range = (int) (Math.random() * 2) + 1;
            name = (int) (Math.random() * 20);
            subname = (int) (Math.random() * 20);
            worker = new Worker(name, subname, range);
            worker_list.add(worker);
        }
    }
    
    public void loadTripulation() throws FileNotFoundException, IOException {
        
        int saveSlot = getSaveSlot();
        
        File file = new File("File" + saveSlot + ".bin");

        String lines;
        
        int count = 0;
        
        Worker worker = new Worker();
        
        try{
            
            FileInputStream fis = new FileInputStream(file);
            
            DataInputStream dis = new DataInputStream(fis);
            
            while (true) {

                lines = dis.readUTF();
                
                ArrayList w_data = new ArrayList();

                int information = 0;

                String s_data = "";

                for (int i = 0; i < lines.length(); i++) {

                    if (lines.charAt(i) == 58 && lines.charAt(0) == 119) {
                        information = 1;
                    }

                    if (information == 1 && lines.charAt(i) != 32 && lines.charAt(i) != 58 && lines.charAt(i) != 10) {

                        s_data = s_data + lines.charAt(i);

                    }

                    if (lines.charAt(i) == 32 || i == (lines.length() - 1)) {

                        if (s_data != "") {

                            int i_data = Integer.parseInt(s_data);

                            s_data = "";

                            w_data.add(i_data);

                        }

                    }

                }

                if (lines.charAt(0) == 119) {

                    worker = new Worker((int) w_data.get(0), (int) w_data.get(1), (int) w_data.get(2));

                    worker_list.add(worker);
                    
                    count++;

                }

            }
            
        }catch(IOException e){}
        
        if (count < getBeds()[2]){
                
            setBed(2, count);
            
            }
        
    }
    
    public void increaseTripulation(int workerNum){
        
        int range;
        int name;
        int subname;

        Worker worker;
        
        for (int i = 1; i < workerNum; i++) {
            range = (int) (Math.random() * 2) + 1;
            name = (int) (Math.random() * 20);
            subname = (int) (Math.random() * 20);
            worker = new Worker(name, subname, range);
            worker_list.add(worker);
        }
        
        gold = gold - workerNum * getPrices()[2];
        
        beds[2] = beds[2] + workerNum;
        
    }
    
    public void deleteTripulation(int deads) {
        
        if (deads >= worker_list.size() -1){
            deads = worker_list.size() -1;
        }
        
        for (int i = 0; i < deads; i++) {
            
            worker_list.remove(1);
            
        }
        
    }
    
    public void cannonAttack(SeaShip enemy) {

        double hit[] = new double [] {0, 0, 0, 0, 0, 0}; // {Critic, CannonExplode, CannonExplodeShipDamage, CannonExplodeWorkerDamage, CannonWorkerDamage, CanonEnemyWorkerDamage} 
        
        int deads;

        for (int i = 0; i < hit.length; i++) {
            
            if(i < hit.length - 2){
                
                hit[i] = (int) (Math.random() * 100) + 1;
                
            }
            else{
                
              hit[i] = (int) (Math.random() * 125) + 1;  
                
            }
 
        }

        //Critical hit //Soft hit
        
        if (hit[0] >= 0 && hit[0] <= 12 || hit[0] >= 88 && hit[0] <= 100) {
            hit[0]=1.25;
        }
        else{
            if(hit[0] >= 12 && hit[0] <= 24 || hit[0] >= 76 && hit[0] <= 88){
                hit[0]=0.85;
            }
            else{
                hit[0]=1;
            }
            
        }
        
        //Cannon Explode
        
        if(hit[1] >= 0 && hit[1] <= 15 || hit[1] >= 85 && hit[1] <= 100){
            hit[1]=1;
        }
        else{
            hit[1]=0;
        }
        
        //
        
        /*double formule = getAttack() * (((getAttackP()/100) * hit[0]) - (int)((double)(enemy.getDefense())/100) * (double)enemy.getDefenseP()/100 * (1 + (double)enemy.getDefend()[0]/100));*/
        
        double formule1 = ((getAttack() * ((getAttackP()/100) * hit[0])) * (1 - ((double)enemy.getDefense()/100) * (double)enemy.getDefenseP()/100 * (1.0 + (double)enemy.getDefend()[0]/100)));
               
        double formule2 = ( hit[1] * ((hit[2]/2 * (getAttackP()/100))) * (1 - (((double)getDefense()/100) * (double)(getDefenseP()/100))));
        
        double formule3 = ((double)getBeds()[2] * ((hit[4]/100) * hit[1] * (getDefense()/100)));
        
        double formule4 = ((double)enemy.getBeds()[0] * ((hit[5]/100) * hit[0])) * (((double)enemy.getDefense()/100) * ((double)enemy.getDefenseP()/100));
        
        System.out.println(formule4);
        
        if (formule3 > getBeds()[2]){
            
            formule3 = getBeds()[2];
            
        }
        
        if (formule4 > enemy.getBeds()[2]){
            
            formule4 = enemy.getBeds()[2];
            
        }
        
        System.out.print(enemy.worker_list.get(0).getNameS()[enemy.worker_list.get(0).getName()] + " ha recibido un ataque");
        
        if(hit [0] == 1.25){
            System.out.print(" critico");
        }
        
        if(hit [0] == 0.85){
            System.out.print(" costoso");
        }

        if(enemy.getDefend()[1] >0){
            System.out.print(" estando protegido");
        }
        
        System.out.print(" de " + (int) formule1 + " puntos de daño , que ha causado " + (int)formule4 + " bajas \n"); 
        
        enemy.setArmor((int)(enemy.getArmor() - (int)formule1));
        
        if (enemy.getArmor() < 0){
            
            if((-enemy.getArmor()) != (int)formule1 && enemy.getSaveSlot() != -1){
                
                System.out.println("La armadura ha absobrido " + (int)(formule1 + (enemy.getArmor())) + " puntos de daño");
                
            }
            
            enemy.setHealth(enemy.getHealth() + enemy.getArmor());
            
            enemy.setArmor(0);
            
        }
        else{
            
            if(enemy.getSaveSlot() != -1){
                
                System.out.println("La armadura ha aguantado el golpe quedando intactos " + enemy.getArmor() + " puntos de armadura");
                
            }
 
        }

        setHealth(getHealth() - (int)formule2);
        
        deads = (int) formule3;

        setBed(2, getBeds()[2] - deads);

        
        if(hit[1]==1){
            System.out.println("La polvora se ha incendiado durante el ataque provocando un accidente que ha causado " + (int)formule2 + " puntos de daño y " + deads + " bajas." );
        }
        
        deleteTripulation(deads);

        deads = (int) formule4;

        enemy.setBed(2, enemy.getBeds()[2] - deads);

        setTurn();

        enemy.deleteTripulation(deads);
        
        setCannon_ammo(getCannon_ammo()-1);

    }
    
    public void musketAttack(SeaShip enemy){
        
        double hit[] = new double [] {0, 0, 0}; // {Critic, MusketShipDamage, MusketWorkerDamage} 
        
        int deads;

        for (int i = 0; i < hit.length; i++) {
            
            if (i > 0){
                
                if(i == hit.length-1){

                    hit[i] = (int) (Math.random() * 100) + 1;
                    
                }
                else{
                    
                    hit[i] = (int) (Math.random() * 50) + 1;
                    
                }

            }
            else{
                
                hit[i] = (int) (Math.random() * 100) + 1; 
                
            }
            
        }
        
        //Critical hit //Soft hit
        
        if (hit[0] >= 0 && hit[0] <= 12 || hit[0] >= 88 && hit[0] <= 100) {
            
            hit[0]=1.25;
            
        }
        else{
            
            if(hit[0] >= 12 && hit[0] <= 24 || hit[0] >= 76 && hit[0] <= 88){
                
                hit[0]=0.85;
                
            }
            else{
                
                hit[0]=1;
                
            }
            
        }
        
        //
        
        double formule1 = ( hit[1]/100 * ((getAttack() * ((getAttackP()/100) * hit[0])) * (1 - ((double)enemy.getDefense()/100) * (double)enemy.getDefenseP()/100 * (1.0 + (double)enemy.getDefend()[0]/100))));
               
        double formule2 = ((double)enemy.getBeds()[0] * ((((double)hit[2]/100) * hit[0]) * ((double)getBeds()[0]/(double)enemy.getBeds()[0])) * (((double)enemy.getDefense()/100) * ((double)enemy.getDefenseP()/100)));
        
        if (formule2 > enemy.getBeds()[2]){
            
            formule2 = enemy.getBeds()[2];
            
        }
        
        System.out.print(enemy.worker_list.get(0).getNameS()[enemy.worker_list.get(0).getName()] + " ha recibido un ataque");
        
        if(hit [0] == 1.25){
            System.out.print(" critico");
        }
        
        if(hit [0] == 0.85){
            System.out.print(" costoso");
        }

        if(enemy.getDefend()[1] >0){
            System.out.print(" estando protegido");
        }
        
        System.out.print(" de " + (int) formule1 + " puntos de daño, que ha causado " + (int)formule2 + " bajas \n"); 
        
        enemy.setArmor((int)(enemy.getArmor() - (int)formule1));
        
        if (enemy.getArmor() < 0){
            
            if((-enemy.getArmor()) != (int)formule1 && enemy.getSaveSlot() != -1){
                
                System.out.println("La armadura ha absobrido " + (int)(formule1 + (enemy.getArmor())) + " puntos de daño");
                
            }
            
            enemy.setHealth(enemy.getHealth() + enemy.getArmor());
            
            enemy.setArmor(0);
            
        }
        else{
            
            if(enemy.getSaveSlot() != -1){
                
                System.out.println("La armadura ha aguantado el golpe quedando intactos " + enemy.getArmor() + " puntos de armadura");
                
            }
 
        }
        
        deads = (int) formule2;

        enemy.setBed(2, enemy.getBeds()[2] - deads);

        enemy.deleteTripulation(deads);
        
        setTurn();
        
        setMusket_ammo(getMusket_ammo()-1);
        
    }
    
    public void runAway(SeaShip enemy) {

        int run[] = new int[2];

        run[0] = (int) (Math.random() * 101);

        run[1] = (int) (Math.random() * 251);

        if (run[0] > 0 && run[0] < 12 || run[0] > 88 && run[0] < 100) {

            System.out.print("\nConsigues huir con éxito, pero pierdes ");

            if (getGold() >= run[1]) {

                System.out.print(run[1]);

                setGold(getGold() - run[1]);

            } else {

                System.out.print(getGold());

                setGold(0);

            }

            System.out.print(" doblones de oro \n");

            enemy.setBed(2, -999);

        } else {

            System.out.println("\nIntento de huida fallido");

        }

    }
        
    public void buyArmor(){
        
        setArmor(getArmor() + getLot()[2]);
        
        if (getArmor() > getMaxArmor()){
            
            setArmor(getMaxArmor());
            
        }
        
        setGold(getGold() - getPrices()[4]);
        
    }
    
    public void buyCannonAmmo() {
        
        setCannon_ammo(getCannon_ammo() + getLot()[0]);
                
        setGold(getGold() - getPrices()[0]);
        
    }
    public void buyMusketAmmo() {
        
        setMusket_ammo(getMusket_ammo() + getLot()[1]);    
                
        setGold(getGold() - getPrices()[1]);
                    
    }
    
    public String toStringWorker_list(int developer){
        
        String worker_list = "";

        String worker [] = new String[3];
        
        for (int i = 0; i < this.worker_list.size(); i++) {
            
            worker [0]  = this.worker_list.get(i).getNameS()[this.worker_list.get(i).getName()];
            
            worker [1]  = this.worker_list.get(i).getSubnameS()[this.worker_list.get(i).getSubname()];
            
            worker [2]  = this.worker_list.get(i).getRangeS()[this.worker_list.get(i).getRange()];
            
            worker_list = worker_list + "\nNombre: " + worker [0] + " - Apellidos: " + worker[1] + " - Rango: " + worker[2];
            
            if(developer == 1){
                
                 worker_list = worker_list + " - Position: " + i;
                 
            }
        }
        
        return worker_list;
    }
    
    public String toString(){
        
        String boat = getClass().getSimpleName();
        
        if(boat.charAt(7) == 83){
            
            boat = "Goleta";
            
        }
        else{
            
            if(boat.charAt(7) == 70){
            
            boat = "Fragata";
            
            }
            else{
                
                if(boat.charAt(7) == 87){
            
                    boat = "Buque";
            
                }
                
            }
            
        }
        
        
        
        String data = "\n===================================================="
                    + "\n| Capitan: " +  this.worker_list.get(0).getNameS()[this.worker_list.get(0).getName()] + " " + this.worker_list.get(0).getSubnameS()[this.worker_list.get(0).getSubname()] + " | Nacionalidad: " + countryS[country] 
                    + "\n----------------------------------------------------"
                    + "\n| Tipo de navío: " + boat + " | Salud: " + getHealth() + " | Blindaje: " + getArmor() + " "
                    + "\n----------------------------------------------------"
                    + "\n| Oro: " + getGold() + " | Munición: " + getCannon_ammo() + " - Cañón | " + getMusket_ammo() + " - Mosquete"
                    + "\n|                     ------------------------------"
                    + "\n| Tripulación: " + getBeds()[2] + "/" + getBeds()[0] + " | Atk: " + (int)getAttackP() + " | Def: " + (int)getDefenseP() + " | Spd: " + (int)((double)getSpeed() * (1 - ((double)getSpeedP()/100) * ((double)getBeds()[2]/(double)getBeds()[1])))
                    + "\n====================================================";
        
        return data;
    }
    
    public String toStringDeveloper(){
        
        return  "Save slot: "+ "File"+ this.saveSlot + ".txt\n" + "Captain: " + this.worker_list.get(0).getNameS()[this.worker_list.get(0).getName()] + " - Type of ship: " + getClass().getSimpleName() + " - Seaship name: " + name + " - Country:" + countryS[country] + " - Health:" + health + " - Armor:" + armor + " - Cannon ammo:" + cannon_ammo + " - Musket ammo:" + musket_ammo + " - Gold:" + gold + " - Attack percentage:" + attackP + " - Defense percentage:" + defenseP + " - Speed percentage:" + speedP + " - Available beds:" + beds[0] + " - Ocuppied beds:" + beds[2] + " - Max beds:" + beds[1] + "Turn: " + (getSpeed() * (1 - (double)getSpeedP()/100 * getBeds()[2]/getBeds()[1])) + '}';
    }

}
