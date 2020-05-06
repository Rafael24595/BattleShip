
package battleship;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    
    public static SeaShip opening() throws IOException{
        
        Scanner keyboard = new Scanner(System.in);
        
        SeaShip myBoat = new SeaShipFrigate();
        
        ArrayList loadInf = new ArrayList();
        
        ArrayList saveData = new ArrayList();
        
        int loadType =0;
        
        int increase = 1;
        
        while(loadType !=1 && loadType != 2){
            
            try{
                
                System.out.println("\n¿Qué desea hacer? \n1.-Crear partida nueva \n2.-Cargar partida \n3.-Borrar partida \n");

                loadType = keyboard.nextInt();
                
            }catch (InputMismatchException | NumberFormatException e) {keyboard.next();}
 
        
        switch(loadType){
            
            case 1:
                
                loadInf = DataManager.newSaveData(loadType);
                
            break;
            
            case 2:
                
                loadInf = DataManager.loadData(loadType);
                
                loadType = (int)loadInf.get(1);
                
            break;
            
            case 3:
                
                DataManager.deleteSaveFile();
                
            break;
            
        }
        
        }
                
        increase = (int)loadInf.get(0);
                
        loadType = (int)loadInf.get(1);  
        
        saveData = DataManager.readFile(String.format("File%d.bin", increase)); 
        
        myBoat = DataManager.generateGameData(saveData, loadType);
        
        return myBoat;
    }

    public static void menu(SeaShip player) throws IOException{
        
        ArrayList<SeaShip> myBoat = new ArrayList<SeaShip>();
        
        myBoat.add(player);
        
        int option = -1;
        
        Scanner keyboard = new Scanner(System.in);
        
        while(option != 0) {
            
            try{
                
                System.out.println("\n¿Qué deseas hacer? \n1.-Nueva partida \n2.-Combatir con enemigo customizado \n3.-Generar enemigo aleatoriamente \n4.-Modo historia(Cooming_Soon) \n5.-Puerto \n6.-Mostrar datos \n7.-Guardar progreso \n0.-Salir\n");
        
                option = keyboard.nextInt();
                
            }catch (InputMismatchException | NumberFormatException e) {keyboard.next();}

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
                    
                    System.out.println(new StringBuilder("\n| Marineros: ")
                                                 .append("\n-----------------"));
                    
                    System.out.println(myBoat.get(0).toStringWorker_list(0));  
                    
                break;
                
                case 7:
                    
                    DataManager.saveData(myBoat.get(0));
                    
                    System.out.println("\nDatos guardados");
                    
                break;

            }

        }

    }
    
    public static void battleMenu(SeaShip player, SeaShip CPU) throws IOException{
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println(String.format("\nEnemigo %s está preparado para combatir", CPU.getName()));
        
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

            System.out.println(player.getTurn() + "  " + CPU.getTurn());
            
            int optionPlayer = 0;
            
            int optionCPU = 0;
                
            if (player.getTurn() <= 0) {

                while (optionPlayer < 1 || optionPlayer > 5) {
                    
                    try{
                
                        System.out.println("\n¿Qué quieres hacer? \n1.-Ataque de cañon \n2.-Ataque de mosquete \n3.-Defenderse \n4.-Mostrar datos \n5.-Huir\n");

                        optionPlayer = keyboard.nextInt();
                
                    }catch (InputMismatchException | NumberFormatException e) {keyboard.next();}

                }

                Menu.movement(player, CPU, optionPlayer, 0);

                if (optionPlayer == 1 || optionPlayer ==2){
                    
                    System.out.println(String.format("\n%s: %dPV --- Tripulación: %d/%d\n%s: %dPV --- Tripulación: %d/%d\n", player.getName(), player.getHealth(), player.getBeds()[2], player.getBeds()[0], CPU.getName(), CPU.getHealth(), CPU.getBeds()[2], CPU.getBeds()[0]));
   
                }
                
            }
            
            if (CPU.getTurn() <= 0 && CPU.getBeds() [2] != -999){
                
                optionCPU = (int)(Math.random() *3) + 1;
                
                Menu.movement(CPU, player, optionCPU, 1);
                
                if (optionCPU == 1 || optionCPU ==2){
                    
                    System.out.println(String.format("\n%s: %dPV --- Tripulación: %d/%d\n%s: %dPV --- Tripulación: %d/%d\n", player.getName(), player.getHealth(), player.getBeds()[2], player.getBeds()[0], CPU.getName(), CPU.getHealth(), CPU.getBeds()[2], CPU.getBeds()[0]));
                }
                
            }
            
            if(player.getDefend()[1] > 0){
                
                player.setDefend(player.getDefend()[1] - 1);
                
            }
            
            if(CPU.getDefend()[1] > 0){
                
                CPU.setDefend(CPU.getDefend()[1] - 1);
                
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
        else if (CPU.getHealth() <= 0 || CPU.getBeds()[2] <= 0 && CPU.getBeds()[2] > -999){
            
            System.out.println(String.format("\nEnemigo %s ha caido\nSaqueas %d doblones de oro de los restos", CPU.getName(), + CPU.getGold()));
                
            player.setGold(player.getGold() + CPU.getGold());

        }

    }
    
    public static void movement(SeaShip player, SeaShip CPU, int option, int type){
        
        Scanner keyboard = new Scanner(System.in);
        
        String playerType [] = {"Atacas con una andanada al enemigo",String.format("%s te ha atacado una andanada", player.getName()), "Realizas una ataque de fusileros", String.format("%s ha realizado un ataque de fusileros", player.getName()), "Te has defendido", String.format("%s se ha defendido", CPU.getName())};
        
        switch (option) {

                    case 1:
                        
                        if (player.getCannon_ammo() > 0 || type == 1){
                            
                            System.out.println("\n" + playerType[0 + type]);

                            player.cannonAttack(CPU);
                            
                        }
                        else{
                            
                            System.out.println("\nNo te queda munición de cañón");
                            
                            player.setTurn(player.getTurn() + 1);
                            
                            CPU.setTurn(CPU.getTurn() + 1);
                            
                        }

                    break;
                    
                    case 2: 
                        
                        if (player.getMusket_ammo() > 0 || type == 1){
                            
                            System.out.println("\n" + playerType[2 + type]);
                        
                            player.musketAttack(CPU);
                            
                        }
                        else{
                            
                            System.out.println("No te queda munición de mosquete");
                            
                            player.setTurn(player.getTurn() + 1);
                            
                            CPU.setTurn(CPU.getTurn() + 1);
                            
                        }
       
                    break;   
                    
                    case 3:
                        
                        System.out.println("\n" + playerType[4 + type]);
                        
                        player.setDefend(player.getDefensePlus()[1]);
                        
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
                            
                            System.out.println(new StringBuilder("\n| Marineros: ")
                                                         .append("\n-----------------"));
                    
                            System.out.println(player.toStringWorker_list(0));  
                            
                        player.setTurn(player.getTurn() + 1);
                            
                        CPU.setTurn(CPU.getTurn() + 1);
                        
                    break;
                    
                    case 5:
                        
                        System.out.println("\nIntentas huir");
                        
                        player.runAway(CPU);
                        
                    break;

                }
        
    }
    
    public static void shopMenu(SeaShip player){
        
        Scanner keyboard = new Scanner(System.in);
        
        int option = 1;
        
        String playerClass = player.getClass().getSimpleName();
        
        while(option != 0){ 
        
        System.out.println(new StringBuilder("\n------------------------")
                                     .append("\n|  Almacén del puerto  |                            Oro: ").append(player.getGold())
                                     .append("\n------------------------                           -------------- \n"));

        try{
            
            System.out.println(new StringBuilder(" ¿Qué desea hacer?\n")
                                                .append("\n1.-Reparar navío: 1PV x ").append(player.getPrices()[3])
                                                .append(" doblones")
                                                .append(" \n2.-Comprar blindaje: ").append(player.getLot()[2]).append(" PD x ").append(player.getPrices()[4])
                                                .append(" \n3.-Comprar munición de cañón: ").append(player.getLot()[0]).append(" balas x ").append(player.getPrices()[0]).append(" doblones")
                                                .append(" \n4.-Comprar munición de mosquete: ").append(player.getLot()[1]).append(" cargas x ").append(player.getPrices()[1]).append(" doblones")
                                                .append(" \n5.-Contratar marineros: 1 x ").append(player.getPrices()[2]).append(" doblones")
                                                .append(" \n6.-Comprar mejoras para el navío"
                                                      + " \n0.-Salir\n"));

            option = keyboard.nextInt();
                
        }catch (InputMismatchException | NumberFormatException e) {keyboard.next(); option = -1;}

        switch(option){
            
            case 1:
                
                int fix = 0;
                
                int fixIt = 0;
                
                if(player.getHealth() < player.getMaxHealth() ){
                    
                    System.out.print("\nSe regenerarán ");
                
                if((player.getMaxHealth() - player.getHealth()) * player.getPrices()[3] <= player.getGold()){
                    
                    fix = player.getMaxHealth() - player.getHealth();
                    
                    System.out.print(fix);
                    
                }
                else{
                    
                    fix = player.getGold() / player.getPrices()[3];
                    
                    System.out.print(fix);
                    
                }
                
                System.out.print(String.format(" puntos de salud por %d doblones.\n", fix * player.getPrices()[3]));
                
                while(fixIt != 1 && fixIt != 2){
                    
                    try{
                
                         System.out.println("\n¿Está de acuedo? \n1.-Sí \n2.-No\n");
                    
                        fixIt = keyboard.nextInt();
                
                    }catch (InputMismatchException | NumberFormatException e) {keyboard.next();}
  
                }
                
                if (fixIt == 2){
                    
                    do{
                        
                        try{
                
                            System.out.println("\n¿Cuantos puntos de salud desea recuperar?\n");
                        
                            fixIt = keyboard.nextInt();
                
                        }catch (InputMismatchException | NumberFormatException e) {keyboard.next();}
                        
                        if(fixIt > fix){
                            
                            System.out.println("\nNo va a ser posible");
                            
                        }
 
                    }while ((fixIt > fix || fixIt < 0) && fixIt != 0);
                    
                }
                else{
                    
                    fixIt = fix;
                    
                }
                
                player.fixBoat(fixIt);
                    
                }
                else{
                    
                    System.out.println("\nTu salud está al máximo");
                    
                }
  
            break;    
            
            case 2:
                
              Menu.buyMenu(player, 0);
                
            break;
            
            case 3:
                
               Menu.buyMenu(player, 1);
    
            break;
            
            case 4:
                
               Menu.buyMenu(player, 2);
    
            break;
            
            case 5:
                
                int workerNum = -1;
                
                while((workerNum < 0 || workerNum + player.getBeds()[2] > player.getBeds()[0] || workerNum * player.getPrices()[2] > player.getGold()) && workerNum != 0){
                    
                    try{
                
                        System.out.println(String.format("\n¿Cuantos marineros desea contratar?                               Tripulación actual: %d/%d\n", player.getBeds()[2], player.getBeds()[0]));

                        workerNum = keyboard.nextInt();
                
                    }catch (InputMismatchException | NumberFormatException e) {keyboard.next(); option = -1;}
                    
                    if (workerNum + player.getBeds()[2]  > player.getBeds()[0]){
                        
                        System.out.println("\nNo dispone de suficientes catres en el navío");
                        
                    }
                    else{
                        
                        if (workerNum < 0){
                        
                        System.out.println("\nNosotros no contratamos marineros");
                        
                        }
                        else{
                            
                            if(workerNum * player.getPrices()[2] > player.getGold()){
                                
                                System.out.println("\nNo tienes suficiente oro");
                                
                            }
                            
                        }
                        
                    }
                    
                }
                
                player.createTripulation(workerNum, 1);
                
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
                
                System.out.println(new StringBuilder("\n---------------")
                                             .append("\n|   Mejoras   |")
                                             .append("\n---------------\n"));
                
                System.out.println(
                new StringBuilder("----------------------------------------------------------------------------------")
                        .append("\n| 1.- Mejora de armamento| ").append(attackUpdate[1]).append(" +").append(player.getAttackUpgrade()[1]).append(" Atk x ").append(player.getAttackUpgrade()[5]).append(" | ").append(attackUpdate[2]).append(" +").append(player.getAttackUpgrade()[2]).append(" Atk x ").append(player.getAttackUpgrade()[6]).append(" | ").append(attackUpdate[3]).append(" +").append(player.getAttackUpgrade()[3]).append(" Atk x ").append(player.getAttackUpgrade()[7]).append(" | ")
                        .append("\n----------------------------------------------------------------------------------")
                        .append("\n| 2.- Mejora de defensa  | ").append(defenseUpdate[1]).append(" +").append(player.getDefenseUpgrade()[1]).append(" Atk x ").append(player.getDefenseUpgrade()[5]).append(" | ").append(defenseUpdate[2]).append(" +").append(player.getDefenseUpgrade()[2]).append(" Atk x ").append(player.getDefenseUpgrade()[6]).append(" | ").append(defenseUpdate[3]).append(" +").append(player.getDefenseUpgrade()[3]).append(" Atk x ").append(player.getDefenseUpgrade()[7]).append("  | ")
                        .append("\n----------------------------------------------------------------------------------")
                        .append("\n| 3.- Mejora de catres   | ").append(bedUpdate[1]).append(" +").append(player.getBedsUpgrade()[1]).append(" Atk x ").append(player.getBedsUpgrade()[5]).append(" | ").append(bedUpdate[2]).append(" +").append(playerClass).append(player.getBedsUpgrade()[2]).append(playerClass).append(" Atk x ").append(player.getBedsUpgrade()[6]).append(" | ").append(bedUpdate[3]).append(" +").append(player.getBedsUpgrade()[3]).append(" Atk x ").append(player.getBedsUpgrade()[7]).append("  | ")
                        .append("\n----------------------------------------------------------------------------------")
                        .append("\n| 0.-Salir  |")
                        .append("\n-------------\n"));
                
                try{
                
                    upgrade = keyboard.nextInt();
                
                }catch (InputMismatchException | NumberFormatException e) {keyboard.next(); option = -1;}
                
                switch (upgrade){
                    
                    case 1:
                        
                        Menu.upgradeMenu(player, attackUpdate, 0);
                        
                    break;
                    
                    case 2:
                        
                        Menu.upgradeMenu(player, defenseUpdate, 1);
                        
                    break;
                    
                    case 3:
                        
                        Menu.upgradeMenu(player, bedUpdate, 2);
                        
                    break;
                    
                }
                
                }
                
            break;

            }

        }

    }
    
    public static void buyMenu (SeaShip player, int type) {
        
        boolean typeB [] = {player.getGold() >= player.getPrices()[4], player.getGold() >= player.getPrices()[0], player.getGold() >= player.getPrices()[1], player.getArmor() < player.getMaxArmor(), player.getCannon_ammo() < player.getMaxAmmo()[0], player.getMusket_ammo() < player.getMaxAmmo()[1]};

        String typeS [] ={"Armadura máxima", "Munición de cañón máxima", "Munición de mosquete máxima"};
        
        if (typeB[type] == true) {

            if (typeB[type + 3] == true) {

                if (type == 0) {

                    player.buyArmor();

                    System.out.println("\nLa armadura ha aumentado a " + player.getArmor());

                }
                if (type == 1) {

                    player.buyCannonAmmo();

                    System.out.println("\nHas comprado balas de cañon, munición actual: " + player.getCannon_ammo());

                }
                if (type == 2) {

                    player.buyMusketAmmo();

                    System.out.println("\nHas comprado cargas de mosquete, munición actual: " + player.getMusket_ammo());

                }

            } else {

                System.out.println("\n" + typeS[type]);

            }
        } else {

            System.out.println("\nNo tienes suficiente oro");

        }

    }
    
    public static void upgradeMenu(SeaShip player, String[] updateInterface, int type) {

        int i = 1;

        while (i < updateInterface.length && !updateInterface[i].equals("o")) {

            i++;

        }
        
        if (i < updateInterface.length) {
            
            boolean typeB [] = {player.getAttackUpgrade()[i + 4] <= player.getGold(), player.getDefenseUpgrade()[i+4] <= player.getGold(), player.getBedsUpgrade()[i+4] <= player.getGold()};

            if (typeB[type] == true) {

                if(type==0){
                    
                    System.out.println("\nLa adquisición de esta mejora aumenta el daño a " + player.getAttackUpgrade()[i] + " Atk");

                    player.upgradeAttack(i);
                    
                }
                
                if(type==1){
                    
                    System.out.println("\nLa adquisición de esta mejora aumenta la defensa a " + player.getDefenseUpgrade()[i] + " Def");
                            
                    player.upgradeDefense(i);
                    
                }
                
                if(type==2){
                    
                    System.out.println("\nLa adquisición de esta mejora aumenta la capacidad a " + player.getBedsUpgrade()[i] + " catres");
                            
                    player.upgradeBeds(i);
                    
                }

            } else {

                System.out.println("\nNo tienes suficiente oro");

            }

        } else {

            System.out.println("\nNo hay mejoras disponibles");

        }

    }

}
