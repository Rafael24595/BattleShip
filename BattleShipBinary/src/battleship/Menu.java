
package battleship;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    
    public static SeaShip opening() throws IOException{
        
        int loadType =0;
        
        int increase = 1;
        
        File file;
        
        while(loadType !=1 && loadType != 2){
 
        System.out.println("\n¿Qué desea hacer? \n1.-Crear partida nueva \n2.-Cargar partida\n");
        
        Scanner keyboard = new Scanner(System.in);
        
        loadType = keyboard.nextInt();
        
        int option = 0;
        
        String ranure = "x";
        
        switch (loadType){
        
            case 1: 
                
                ArrayList loadInf1 = DataManager.newSaveData(loadType);
                
                file = (File)loadInf1.get(0);
                
                increase = (int)loadInf1.get(1);
                
                loadType = (int)loadInf1.get(2);
                
            break;
                
            case 2:
            
                ArrayList loadInf2 = DataManager.loadData(loadType);
                
                file = (File)loadInf2.get(0);
                
                increase = (int)loadInf2.get(1);
                
                loadType = (int)loadInf2.get(2);
              
            break;
        
    }
        }
        ArrayList<SeaShip> myBoat = new ArrayList<SeaShip>();
        
        ArrayList saveData = DataManager.readFile("File" + increase + ".bin"); 
        
        myBoat.add(0, DataManager.generateGameData(saveData, loadType));
        
        return myBoat.get(0);
    }

    public static SeaShip menu(SeaShip player) throws IOException{
        
        ArrayList<SeaShip> myBoat = new ArrayList<SeaShip>();
        
        myBoat.add(player);
        
        int option = -1;
        
        Scanner keyboard = new Scanner(System.in);
        
        while(option != 0) {
            
            System.out.println("\n¿Qué deseas hacer? \n1.-Nueva partida \n2.-Combatir con enemigo customizado \n3.-Generar enemigo aleatoriamente \n4.-Modo historia(Cooming_Soon) \n5.-Puerto \n6.-Mostrar datos \n7.-Guardar progreso \n0.-Salir\n");
        
            option = keyboard.nextInt();
        
            switch(option){
                
                case 1:
                  
                    myBoat.remove(0);
                    myBoat.add(0, Menu.opening());
                    
                break;
                
                case 2:
                     
                    myBoat.add(1, myBoat.get(0).createDummyShip(0));
                    
                    if(myBoat.get(1).getCountry() != -1){
                        
                        battleMenu(myBoat.get(0),myBoat.get(1));  
                        
                    }
                     
                break;
                
                case 3:
                     
                    myBoat.add(1, myBoat.get(0).createDummyShip(1));
                        
                    battleMenu(myBoat.get(0),myBoat.get(1));  
                     
                break;
                
                case 4:
                     
                    System.out.println("Work_In_Progress");  
                     
                break;
                
                case 5:
                    
                    shopMenu(myBoat.get(0));
                    
                break;
                
                case 6:
                    
                    System.out.println(myBoat.get(0).toString());
                    
                    System.out.println("\n| Marineros: "
                                     + "\n-----------------");
                    
                    System.out.println(myBoat.get(0).toStringWorker_list(0));  
                    
                break;
                
                case 7:
                    
                    DataManager.saveData(myBoat.get(0));
                    
                    System.out.println("\nDatos guardados");
                    
                break;
                
        }
        
        
            
        }
        
        return myBoat.get(0);
        
    }
    
    public static void battleMenu(SeaShip player, SeaShip CPU) throws IOException{
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("\nEnemigo " + CPU.getName() + " está preparado para combatir");
        
        int first = player.getSpeedP() - CPU.getSpeedP();
        
        if(first >= 0){
            
            player.setTurn(0);
            
            CPU.setTurn(1);
            
        }
        else{
            
            player.setTurn(1);
            
            CPU.setTurn(0);
            
        }
        
        while (player.getHealth() > 0 && player.getBeds()[2] > 0 && CPU.getHealth() > 0 && CPU.getBeds()[2] > 0){

            int optionPlayer = 0;
            
            int optionCPU = 0;
                
            if (player.getTurn() <= 0) {

                while (optionPlayer < 1 || optionPlayer > 5) {

                    System.out.println("\n¿Qué quieres hacer? \n1.-Ataque de cañon \n2.-Ataque de mosquete \n3.-Defenderse \n4.-Mostrar datos \n5.-Huir\n");

                    optionPlayer = keyboard.nextInt();

                }

                switch (optionPlayer) {

                    case 1:
                        
                        if (player.getCannon_ammo() > 0){
                            
                            System.out.println("\nAtacas con una andanada al enemigo");

                            player.cannonAttack(CPU);
                            
                        }
                        else{
                            
                            System.out.println("No te queda munición de cañón");
                            
                            player.setTurn(player.getTurn() + 1);
                            
                            CPU.setTurn(CPU.getTurn() + 1);
                            
                        }
                        

                    break;
                    
                    case 2: 
                        
                        if (player.getMusket_ammo() > 0){
                            
                            System.out.println("\nRealizas una ataque de fusileros");
                        
                            player.musketAttack(CPU);
                            
                        }
                        else{
                            
                            System.out.println("No te queda munición de mosquete");
                            
                            player.setTurn(player.getTurn() + 1);
                            
                            CPU.setTurn(CPU.getTurn() + 1);
                            
                        }
                        
                        
                    break;   
                    
                    case 3:
                        
                        System.out.println("\nTe has defendido");

                        player.setDefend(3);
                        
                        player.setTurn();

                    break;
                    
                    case 4:
                        
                        int show = 0;
                        
                        System.out.println(player.toString());
                        
                        while (show != 1 && show != 2){
                            
                            System.out.println(" 1.-Mostrar marineros                      2.-Salir\n");
                         
                            show = keyboard.nextInt();
                            
                        }
                         
                        if (show == 1)
                                
                            System.out.println("\n| Marineros: "
                                 + "\n-----------------");
                    
                            System.out.println(player.toStringWorker_list(0));  
                            
                        player.setTurn(player.getTurn() + 1);
                            
                        CPU.setTurn(CPU.getTurn() + 1);
                        
                    break;
                    
                    case 5:
                        
                        System.out.println("\nIntentas huir");
                        
                        player.runAway(CPU);
                        
                    break;

                }

                if (optionPlayer == 1 || optionPlayer ==2){
                    
                    System.out.println("\n" + player.getName() + ": " + player.getHealth() + "PV --- Tripulación: " + player.getBeds()[2] + "/" + player.getBeds()[0] + "\n" + CPU.getName() + ": " + CPU.getHealth() + "PV --- Tripulación: " + CPU.getBeds()[2] + "/" + CPU.getBeds()[0] + "\n");
                    
                }
                
            }
            
            if (CPU.getTurn() <= 0 && CPU.getBeds() [2] != -999){
                
                optionCPU = (int)(Math.random() *3) + 1;
                
                switch (optionCPU) {

                    case 1:
                        
                        System.out.println(CPU.getName() + " te ha atacado una andanada");

                        CPU.cannonAttack(player);

                    break;
                    
                    case 2: 
                        
                        System.out.println(CPU.getName() + " ha realizado un ataque de fusileros");
                        
                        CPU.musketAttack(player);
                        
                    break;   

                    case 3:
                        
                        System.out.println("\n" + CPU.getName() + "Se ha defendido");

                        CPU.setDefend(CPU.getDefensePlus()[1]);

                    break;
                }
                
                if (optionCPU == 1 || optionCPU ==2){
                    
                    System.out.println("\n" + player.getName() + ": " + player.getHealth() + "PV --- Tripulación: " + player.getBeds()[2] + "/" + player.getBeds()[0] + "\n" + CPU.getName() + ": " + CPU.getHealth() + "PV --- Tripulación: " + CPU.getBeds()[2] + "/" + CPU.getBeds()[0] + "\n");
                    
                }
                
            }
            
            if(player.getDefend()[1] > 0){
                               
                int count = player.getDefend()[1] - 1;
                
                player.setDefend(count);
                
            }
            
            if(CPU.getDefend()[1] > 0){
                               
                int count = CPU.getDefend()[1] - 1;
                
                CPU.setDefend(count);
                
            }

            player.setTurn(player.getTurn()-1);

            CPU.setTurn(CPU.getTurn()-1);

        }
        
        if(player.getHealth() <= 0 || player.getBeds()[2] <= 0){
            
            System.out.print("\nTu navio no puede seguir navegando");
            
            if(player.getHealth() <= 0){
                
                System.out.print(" se va a pique \n");
                
            }
            if(player.getBeds()[2] <= 0){
                
                System.out.print("Te has quedado sin tripulación \n");
                
            }
            
            player = Menu.opening();
            
        }
        else{
            
            if (CPU.getHealth() <= 0 || CPU.getBeds()[2] <= 0 && CPU.getBeds()[2] > -999){
 
                System.out.println("\nEnemigo " + CPU.getName() + " ha caido\nSaqueas " + CPU.getGold() + " doblones de oro de los restos");
                
                player.setGold(player.getGold() + CPU.getGold());
                
            }
            
        }

    }
    
    public static void shopMenu(SeaShip player){
        
        Scanner keyboard = new Scanner(System.in);
        
        int option = 1;
        
        String attackUpdates [] = {"o", "o" ,"o", "o"};
        
        String playerClass = player.getClass().getSimpleName();
        
        while(option != 0){
        
        System.out.println("\n------------------------"
                         + "\n|  Almacén del puerto  |                            Oro: " +  player.getGold()
                         + "\n------------------------                           -------------- \n");
       
        System.out.println(" ¿Qué desea hacer?\n"
                        + "\n1.-Reparar navío: 1PV x " + player.getPrices()[3] + " doblones" + " \n2.-Comprar blindaje: " + player.getLot()[2] + " PD x " + player.getPrices()[4] + " \n3.-Comprar munición de cañón: " + player.getLot()[0] + " balas x " + player.getPrices()[0] + " doblones \n4.-Comprar munición de mosquete: " + player.getLot()[1] + " cargas x "+ player.getPrices()[1] +" doblones \n5.-Contratar marineros: 1 x " + player.getPrices()[2] + " doblones" + " \n6.-Comprar mejoras para el navío \n0.-Salir\n");

            option = keyboard.nextInt();

        switch(option){
            
            case 1:
                
                int fix = 0;
                
                int fixIt = 0;
                
                System.out.print("\nSe regenerarán ");
                
                if((player.getMaxHealth() - player.getHealth()) * player.getPrices()[3] <= player.getGold()){
                    
                    fix = player.getMaxHealth() - player.getHealth();
                    
                    System.out.print(fix);
                    
                }
                else{
                    
                    fix = player.getGold() / player.getPrices()[3];
                    
                    System.out.print(fix);
                    
                }
                
                System.out.print(" puntos de salud por " + fix * player.getPrices()[3] + " doblones.\n");
                
                while(fixIt != 1 && fixIt != 2){
                    
                    System.out.println("\n¿Está de acuedo? \n1.-Sí \n2.-No\n");
                    
                    fixIt = keyboard.nextInt();
                    
                }
                
                if (fixIt == 2){
                    
                    fixIt = fix + 1;
                    
                    while (fixIt > fix && fixIt != 0){
                        
                        System.out.println("\n¿Cuantos puntos de salud desea recuperar?\n");
                        
                        fixIt = keyboard.nextInt();
                        
                    }
                    
                    fix = fixIt;
                    
                }
                
                player.fixBoat(fix);
                
            break;    
            
            case 2:
                
              if (player.getPrices()[4] <= player.getGold()){
                  
                  
                  
                  if(!(player.getArmor() + player.getLot()[2] >= player.getLot()[2] + player.getMaxArmor())){
                      
                    player.buyArmor();
                  
                    System.out.println("\nLa armadura ha aumentado a " + player.getArmor());
                      
                  }
                  else{
                      
                      System.out.println("\nArmadura máxima");
                      
                  }
              }
              else{
                  
                System.out.println("\nNo tienes suficiente oro"); 
                  
              }
                
            break;
            
            case 3:
                
                if(player.getGold() >= player.getPrices()[0]){
                    
                    if(!(player.getCannon_ammo() + player.getLot()[0] >= player.getLot()[0] + player.getMaxAmmo()[0])){
                        
                        player.buyCannonAmmo();
                    
                        System.out.println("\nHas comprado balas de cañon, munición actual: " + player.getCannon_ammo());
                        
                    }
                    else{
                        
                        System.out.println("\nMunición de cañón máxima");
                        
                    }
                    
                }
                else{
                    
                    System.out.println("\nNo tienes suficiente oro");
                    
                }
    
            break;
            
            case 4:
                
                if(player.getGold() >= player.getPrices()[1]){
                    
                    if(!(player.getMusket_ammo() + player.getLot()[1] >= player.getLot()[1] + player.getMaxAmmo()[1])){
                        
                        player.buyMusketAmmo();
                    
                        System.out.println("\nHas comprado cargas de mosquete, munición actual: " + player.getMusket_ammo());
                        
                    }
                    else{
                        
                        System.out.println("\nMunición de mosquete máxima");
                        
                    }
                    
                }
                else{
                    
                    System.out.println("\nNo tienes suficiente oro");
                    
                }
    
            break;
            
            case 5:
                
                int workerNum = -1;
                
                while((workerNum < 0 || workerNum + player.getBeds()[2] > player.getBeds()[0] || workerNum * player.getPrices()[2] > player.getGold()) && workerNum != 0){
                    
                    System.out.println("\n¿Cuantos marineros desea contratar?                               Tripulación actual: " + player.getBeds()[2] + "/" + player.getBeds()[0] + "\n");
                
                    workerNum = keyboard.nextInt();
                    
                    if (workerNum + player.getBeds()[2]  > player.getBeds()[0]){
                        
                        System.out.println("\nNo dispone de suficientes catres en el navío");
                        
                    }
                    else{
                        
                        if (workerNum < 0){
                        
                        System.out.println("\nNosotros no necesitamos más marineros");
                        
                        }
                        else{
                            
                            if(workerNum * player.getPrices()[2] > player.getGold()){
                                
                                System.out.println("\nNo tienes suficiente oro");
                                
                            }
                            
                        }
                        
                    }
                    
                }
                
                player.increaseTripulation(workerNum);
                
            break;
            
            case 6:
                
                String attackUpdate [] = {"o", "o", "o", "o"};
                String defenseUpdate [] = {"o", "o", "o", "o"};
                String bedUpdate [] = {"o", "o", "o", "o"};
                
                int upgrade = 1;
                
                while (upgrade != 0){
                
                for (int i = 1; i < 4; i++) {
                    
                    if(player.getAttackP() >= player.getAttackUpgrade()[i] ){
                        
                        attackUpdate[i] = "x";
                        
                    }
                    
                    if(player.getDefenseP() >= player.getDefenseUpgrade()[i] ){
                        
                        defenseUpdate[i] = "x";
                        
                    }
                    
                    if(player.getBeds()[0] >= player.getBedsUpgrade()[i] ){
                        
                        bedUpdate[i] = "x";
                        
                    }
                    
                }
                
                
                
                System.out.println("\n---------------"
                                 + "\n|   Mejoras   |"
                                 + "\n---------------\n");
                
                System.out.println("----------------------------------------------------------------------------------"
                               + "\n| 1.- Mejora de armamento| " + attackUpdate[1] + " +" + player.getAttackUpgrade()[1] + " Atk x " + player.getAttackUpgrade()[5] + " | " + attackUpdate[2] + " +" + player.getAttackUpgrade()[2] + " Atk x " + player.getAttackUpgrade()[6] + " | " + attackUpdate[3] + " +" + player.getAttackUpgrade()[3] + " Atk x " + player.getAttackUpgrade()[7] + " | "
                               + "\n----------------------------------------------------------------------------------"
                               + "\n| 2.- Mejora de defensa  | " + defenseUpdate[1] + " +" + player.getDefenseUpgrade()[1] + " Atk x " + player.getDefenseUpgrade()[5] + " | " + defenseUpdate[2] + " +" + player.getDefenseUpgrade()[2] + " Atk x " + player.getDefenseUpgrade()[6] + " | " + defenseUpdate[3] + " +" + player.getDefenseUpgrade()[3] + " Atk x " + player.getDefenseUpgrade()[7] + "  | "
                               + "\n----------------------------------------------------------------------------------"
                               + "\n| 3.- Mejora de catres   | " + bedUpdate[1] + " +" + player.getBedsUpgrade()[1] + " Atk x " + player.getBedsUpgrade()[5] + " | " + bedUpdate[2] + " +" + player.getBedsUpgrade()[2] + " Atk x " + player.getBedsUpgrade()[6] + " | " + bedUpdate[3] + " +" + player.getBedsUpgrade()[3] + " Atk x " + player.getBedsUpgrade()[7] + "  | "
                               + "\n----------------------------------------------------------------------------------"
                               + "\n| 0.-Salir  |"
                               + "\n-------------\n");
                
                upgrade = keyboard.nextInt();
                
                
                switch (upgrade){
                    
                    case 1:
                        
                        int i = 1;
                        
                        while ( i < attackUpdate.length && !attackUpdate[i].equals("o")){
                            
                            i++;
                            
                        }
                        
                        if(i < attackUpdate.length ){
                            
                        if(player.getAttackUpgrade()[i+4] <= player.getGold()){
                            
                            System.out.println("\nLa adquisición de esta mejora aumenta el daño a " + player.getAttackUpgrade()[i] + " Atk");
                            
                            player.upgradeAttack(i);
                            
                        }
                        else{
                            
                            System.out.println("\nNo tienes suficiente oro");
                            
                        }
                        
                        }
                        else{
                            
                            System.out.println("\nNo hay mejoras disponibles");
                            
                        }
                        
                    break;
                    
                    case 2:
                        
                        int j = 1;
                        
                        while (j < defenseUpdate.length && !defenseUpdate[j].equals("o")){
                            
                            j++;
                            
                        }
                        
                        if(j < defenseUpdate.length ){                            
                        
                        if(player.getDefenseUpgrade()[j+4] <= player.getGold()){
                            
                            System.out.println("\nLa adquisición de esta mejora aumenta la defensa a " + player.getDefenseUpgrade()[j] + " Def");
                            
                            player.upgradeDefense(j);
                            
                        }
                        else{
                            
                            System.out.println("\nNo tienes suficiente oro");
                            
                        }
                        
                        }
                        else{
                            
                            System.out.println("\nNo hay mejoras disponibles");
                            
                        }
                        
                    break;
                    
                    case 3:
                        
                        int k = 1;
                        
                        while (k < bedUpdate.length && !bedUpdate[k].equals("o")){
                            
                            k++;
                            
                        }
                        
                        if(k < bedUpdate.length ){ 
                        
                        if(player.getBedsUpgrade()[k+4] <= player.getGold()){
                            
                            System.out.println("\nLa adquisición de esta mejora aumenta la capacidad a " + player.getBedsUpgrade()[k] + " catres");
                            
                            player.upgradeBeds(k);
                            
                        }
                        else{
                            
                            System.out.println("\nNo tienes suficiente oro \n");
                            
                        }
                        
                        }
                        else{
                            
                            System.out.println("\nNo hay mejoras disponibles");
                            
                        }
                        
                    break;
                    
                }
                
                }
                
            break;
            
        }
    
        }
        
}
    
}
