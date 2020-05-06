
package battleship;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class SeaShip {
    
    ArrayList<Worker> worker_list;
    private String name;
    private String subname;
    private int country;
    private final int attack = 100;
    private final int defense = 75;
    private final int speed = 10;
    private int defend [] = new int [2];                    /*DefensePlusPercentage - LoopTime*/
    private int health;
    private int armor;
    private int cannon_ammo;
    private int musket_ammo;
    private int gold;
    private int turn;
    private int saveSlot;
    private int newGame; /*Useless*/
    
    private int maxHealth;
    private final int maxArmor = 100;
    private final int maxAmmo [] = {20, 20};                /*CannonMaxAmmo - MusketMaxAmmo*/
    private final int defensePlus [] = {15, 2};             /*DefensePlusPercentage - LoopTime*/
    private final int lot[] = {10, 10, 25};                 /*CannonAmmoPack - MusketAmmoPack - ArmorPiecePack*/
    private final int prices [] = {100, 50, 25, 2, 100};    /*CannonAmmoPrice - MusketAmmoPrice - WorkerUnitPrice - FixUnitPrice - ArmorPrice*/
    
    private double attackP;
    private double defenseP;
    private int speedP;
    private int beds [] = new int [4];                                    /*AvailableBeds - MaxBeds(*) - OcupiedBeds*/
    
    private int attackUpgrade[];
    private int defenseUpgrade[];
    private int bedsUpgrade[];

    /*Empty Constructor*/
    
    public SeaShip() {
    }
    
    /*New Game Constructor*/
    
    public SeaShip(String name, String subname, int country, int saveSlot, int newGame) {
       
        worker_list = new ArrayList<>();
        
        this.name = name;
        this.subname = subname;
        this.country = country;
        
        armor = 0;
        cannon_ammo = 10;
        musket_ammo = 10;
        gold = 100;
        
        this.saveSlot = saveSlot;
        this.newGame = newGame;
    }

    /*Load Constructor*/
    
    public SeaShip(String name, String subname, int country, int health, int armor, int cannon_ammo, int musket_ammo, int gold, double attackP, double defenseP, int[] beds, int saveSlot, int newGame) {
       
        this.worker_list = new ArrayList<>();
        
        this.name = name;
        this.subname = subname;
        this.country = country;
        
        this.health = health;
        this.armor = armor;
        this.cannon_ammo = cannon_ammo;
        this.musket_ammo = musket_ammo;
        this.gold = gold;
        
        this.attackP = attackP;
        this.defenseP = defenseP;
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
    
    public SeaShip createDummyShip(int random) throws IOException {
        
        Scanner keyboard = new Scanner(System.in);
        
        ArrayList<SeaShip> CPU_Boat = new ArrayList<SeaShip>();
        
        ArrayList data = new ArrayList();

        int country = getCountry();
        
        int level = -1;
        
        int option = -1;
        
        while (option < 0 || option > 3) {

            if (random != 1) {

                try {

                    System.out.println("\n¿Contra qué clase de navío quieres luchar? \n1.-Goleta \n2.-Fragata \n3.-Buque \n0.-Atrás \n");

                    option = keyboard.nextInt();

                } catch (InputMismatchException | NumberFormatException e) {keyboard.next();}

            } else {

                option = (int) (Math.random() * 3) + 1;

            }

            while (country == getCountry()) {

                country = (int) (Math.random() * 3);

            }

            if(option == 0){
                
                option = 1;
                
                country = -1;
                
                random = 1;
                
            }
            
            data.add(0, option - 1);
            
            data.add(1, worker_list.get(0).getNameS()[(int) (Math.random() * 20)]);

            data.add(2, worker_list.get(0).getSubnameS()[(int) (Math.random() * 20)]);

            data.add(3, country);

            data.add(4, -1);

            data.add(5, 0);

            while (level < 0 || level > 3) {

                if (random != 1) {

                    try {

                        System.out.println("\n¿A qué nivel quieres que se encuentre? \n1.-Mínimo \n2.-Medio \n3.-Alto \n0.-Atrás\n");

                        level = keyboard.nextInt();

                    } catch (InputMismatchException | NumberFormatException e) {keyboard.next();}

                    if (level == 0) {

                        data.set(3, -1);

                    }

                }
                else {

                    level = (int) (Math.random() * 2) + 1;

                }

                SeaShip CPU = DataManager.generateGameData(data, 1);

                CPU_Boat.add(0, CPU);

                int gold = (int) (Math.random() * 101) + 50;

                int upgrade[] = new int[3];

                switch (level) {

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

                CPU_Boat.get(0).createTripulation(CPU_Boat.get(0).getBeds()[2], 0);

            }

        }

        return CPU_Boat.get(0);
    }
    
    public void createTripulation(int newBeds, int type) {
        
        Worker worker;
        
        int range;
        int name;
        int subname;

        if(type==0){
            
            worker = new Worker(20, 20, 0);
        
            worker.createCaptain(this.name, this.subname);
        
            worker_list.add(worker);
            
        }
        
        if(type==1){
            
            gold = gold - newBeds * getPrices()[2];
        
            beds[2] = beds[2] + newBeds;
            
        }

        for (int i = 1; i < newBeds; i++) {
            range = (int) (Math.random() * 2) + 1;
            name = (int) (Math.random() * 20);
            subname = (int) (Math.random() * 20);
            worker = new Worker(name, subname, range);
            worker_list.add(worker);
        }
        
    }
    
    public void loadTripulation() throws FileNotFoundException, IOException {
        
        File file = new File(String.format("File%d.bin", getSaveSlot()));

        int count = 0;
        
        Worker worker;
        
        String lines;

        try{
            
            FileInputStream fis = new FileInputStream(file);
            
            DataInputStream dis = new DataInputStream(fis);
            
            while (true) {

                String s_data;
                
                String sa_data [];
                
                int ia_data [] = new int [3];
                
                lines = dis.readUTF();
                
                if(lines.charAt(0) == 119){
                    
                    s_data = lines.split(":")[1];
                    
                    s_data = s_data.replace("\n", "");
                    
                    sa_data = s_data.split(" ");
                
                    for (int i = 0; i < sa_data.length; i++) {
                        
                        ia_data[i] =Integer.parseInt(sa_data[i]);                       
                        
                    }
                    
                    worker = new Worker(ia_data[0], ia_data[1], ia_data[2]);

                    worker_list.add(worker);
                    
                    count++;
                    
                }

            }
            
        }catch(IOException e){}

        if (count < getBeds()[2]) {

            setBed(2, count);

        }

    }
    
    public void deleteTripulation(int deads) {
        
        if (deads >= worker_list.size() -1){
            
            deads = worker_list.size() -1;
            
        }
        
        int size = deads - 1;
        
        for (int i = 0; i < deads; i++) {
            
            int random = (int)Math.random()*size + 1;
            
            worker_list.remove(random);
            
            size--;
            
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

        double formule1 = ((getAttack() * ((getAttackP()/100) * hit[0])) * (1 - ((double)enemy.getDefense()/100) * (double)enemy.getDefenseP()/100 * (1.0 + (double)enemy.getDefend()[0]/100))); /*EnemyCanonDamage*/
               
        double formule2 = ( hit[1] * ((hit[2]/2 * (getAttackP()/100))) * (1 - (((double)getDefense()/100) * (double)(getDefenseP()/100)))); /*PlayerAccidentDamage*/
        
        double formule3 = ((double)getBeds()[2] * ((hit[4]/100) * hit[1] * (getDefense()/100))); /*PlayerWorkersDamage*/
        
        double formule4 = ((double)enemy.getBeds()[0] * ((hit[5]/100) * hit[0])) * (((double)enemy.getDefense()/100) * ((double)enemy.getDefenseP()/100)); /*EnemyWorkersDamage*/
        
        System.out.println(formule4);
        
        if (formule3 > getBeds()[2]){
            
            formule3 = getBeds()[2];
            
        }
        
        if (formule4 > enemy.getBeds()[2]){
            
            formule4 = enemy.getBeds()[2];
            
        }
        
        System.out.print(String.format("%s ha recibido un ataque", enemy.worker_list.get(0).getNameS()[enemy.worker_list.get(0).getName()]));
        
        if(hit [0] == 1.25){
            System.out.print(" critico");
        }
        
        if(hit [0] == 0.85){
            System.out.print(" costoso");
        }

        if(enemy.getDefend()[1] >0){
            System.out.print(" estando protegido");
        }
        
        System.out.print(String.format(" de %d puntos de daño , que ha causado %d bajas \n", (int) formule1, (int)formule4)); 
        
        enemy.setArmor((int)(enemy.getArmor() - (int)formule1));
        
        if (enemy.getArmor() < 0){
            
            if((-enemy.getArmor()) != (int)formule1 && enemy.getSaveSlot() != -1){
                
                
                System.out.println(String.format("La armadura ha absobrido %d puntos de daño", (int)(formule1 + (enemy.getArmor()))));
                
            }
            
            enemy.setHealth(enemy.getHealth() + enemy.getArmor());
            
            enemy.setArmor(0);
            
        }
        else{
            
            if(enemy.getSaveSlot() != -1){
                
                System.out.println(String.format("La armadura ha aguantado el golpe quedando intactos %d puntos de armadura", enemy.getArmor()));
                
            }
 
        }

        setHealth(getHealth() - (int)formule2);
        
        deads = (int) formule3;

        setBed(2, getBeds()[2] - deads);

        
        if(hit[1]==1){
            
            System.out.println(String.format("La polvora se ha incendiado durante el ataque provocando un accidente que ha causado %d puntos de daño y %d bajas.", (int)formule2, deads));
            
        }
        
        deleteTripulation(deads);

        deads = (int) formule4;

        enemy.setBed(2, enemy.getBeds()[2] - deads);

        enemy.deleteTripulation(deads);
        
        setCannon_ammo(getCannon_ammo()-1);
        
        setTurn();

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
        
        double formule1 = ( hit[1]/100 * ((getAttack() * ((getAttackP()/100) * hit[0])) * (1 - ((double)enemy.getDefense()/100) * (double)enemy.getDefenseP()/100 * (1.0 + (double)enemy.getDefend()[0]/100)))); /*EnemyMusketDamage*/
               
        double formule2 = ((double)enemy.getBeds()[0] * ((((double)hit[2]/100) * hit[0]) * ((double)getBeds()[0]/(double)enemy.getBeds()[0])) * (((double)enemy.getDefense()/100) * ((double)enemy.getDefenseP()/100))); /*EnemyWorkersDamage*/
        
        if (formule2 > enemy.getBeds()[2]){
            
            formule2 = enemy.getBeds()[2];
            
        }
        
        System.out.print(String.format("%s ha recibido un ataque", enemy.worker_list.get(0).getNameS()[enemy.worker_list.get(0).getName()]));
        
        if(hit [0] == 1.25){
            System.out.print(" critico");
        }
        
        if(hit [0] == 0.85){
            System.out.print(" costoso");
        }

        if(enemy.getDefend()[1] >0){
            System.out.print(" estando protegido");
        }
        
        System.out.print(String.format(" de %d puntos de daño, que ha causado %d bajas \n", (int) formule1, (int)formule2));
        
        enemy.setArmor((int)(enemy.getArmor() - (int)formule1));
        
        if (enemy.getArmor() < 0){
            
            if((-enemy.getArmor()) != (int)formule1 && enemy.getSaveSlot() != -1){
                
                System.out.print(String.format("La armadura ha absobrido %d puntos de daño", (int)(formule1 + (enemy.getArmor()))));
                
            }
            
            enemy.setHealth(enemy.getHealth() + enemy.getArmor());
            
            enemy.setArmor(0);
            
        }
        else{
            
            if(enemy.getSaveSlot() != -1){
                
                System.out.print(String.format("La armadura ha aguantado el golpe quedando intactos %d puntos de armadura", enemy.getArmor()));
                
            }
 
        }
        
        deads = (int) formule2;

        enemy.setBed(2, enemy.getBeds()[2] - deads);

        enemy.deleteTripulation(deads);
        
        setMusket_ammo(getMusket_ammo()-1);
        
        setTurn();
        
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
        
        if (getCannon_ammo() > getMaxAmmo()[0]){
            
            setCannon_ammo(getMaxAmmo()[0]);
            
        }
                
        setGold(getGold() - getPrices()[0]);
        
    }
    public void buyMusketAmmo() {
        
        setMusket_ammo(getMusket_ammo() + getLot()[1]);    
        
        if (getMusket_ammo() > getMaxAmmo()[1]){
            
            setMusket_ammo(getMaxAmmo()[1]);
            
        }
                
        setGold(getGold() - getPrices()[1]);
                    
    }
    
    public String toStringWorker_list(int developer){
        
        String worker_list = "";

        String worker [] = new String[3];
        
        for (int i = 0; i < this.worker_list.size(); i++) {
            
            worker [0]  = this.worker_list.get(i).getNameS()[this.worker_list.get(i).getName()];
            
            worker [1]  = this.worker_list.get(i).getSubnameS()[this.worker_list.get(i).getSubname()];
            
            worker [2]  = this.worker_list.get(i).getRangeS()[this.worker_list.get(i).getRange()];
            
            worker_list = String.format("\nNombre: %s - Apellidos: %s - Rango: %s", worker [0], worker [1], worker [2]);
            
            if(developer == 1){
                
                worker_list = String.format("%s - Position: %d",worker_list , i );
                 
            }
        }
        
        return worker_list;
    }
    
    public String toString(){
        
        String countryS [] = {"España", "Francia", "Gran Bretaña"};
        
        String boat = getClass().getSimpleName();
        
        setTurn();
        
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
        
        StringBuilder data = new StringBuilder("\n====================================================");
        data.append("\n| Capitan: ").append(this.worker_list.get(0).getNameS()[this.worker_list.get(0).getName()]).append(" ").append(this.worker_list.get(0).getSubnameS()[this.worker_list.get(0).getSubname()]).append(" | Nacionalidad: " + countryS[country]);
        data.append("\n----------------------------------------------------");
        data.append("\n| Tipo de navío: ").append(boat).append(" | Salud: ").append(getHealth()).append(" | Blindaje: ").append(getArmor()).append(" ");
        data.append("\n----------------------------------------------------");
        data.append("\n| Oro: ").append(getGold()).append(" | Munición: ").append(getCannon_ammo()).append(" - Cañón | ").append(getMusket_ammo()).append(" - Mosquete");
        data.append("\n|                     ------------------------------");
        data.append("\n| Tripulación: ").append(getBeds()[2]).append("/").append(getBeds()[0]).append(" | Atk: ").append((int)getAttackP()).append(" | Def: ").append((int)getDefenseP()).append(" | Spd: ").append((int)getTurn());
        data.append("\n====================================================");
        
        return data.toString();
    }

    public abstract void fixBoat(int i);
    
    public abstract void upgradeAttack(int i);

    public abstract void upgradeBeds(int i);
    
    public abstract void upgradeDefense(int i);
    
}
