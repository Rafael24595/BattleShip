
package battleship;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class DataManager {
    
    public static ArrayList newSaveData(int loadType) throws IOException{
        
        Scanner keyboard = new Scanner(System.in);
        
        ArrayList loadInf = new ArrayList ();
        
        int increase = 1;
        
        int option = 0;
        
        String name = "";
                
                String subname = "";
                
                boolean verify = false;
                
                while(verify == false){
                    
                    System.out.print("\nIntroduce un nombre: ");
                
                    name = keyboard.next();
                    
                    verify = DataManager.validateString(name);
                    
                }
                
                verify = false;
                
                while(verify == false){
                    
                    System.out.print("\nIntroduce un apellido: ");
                
                    subname = keyboard.next(); 
                    
                    verify = DataManager.validateString(subname);
                    
                }
                
                
                
                int country = 0;
                
                while (country < 1 || country > 3){
                    
                    System.out.println("\nAhora elige una de las siguientes naciones: \n1.-España \n2.-Gran Bretaña \n3.-Francia\n");
                    
                    country = keyboard.nextInt();
                    
                }
                
                
                
                int seaShip = 0;
                
                while (seaShip < 1 || seaShip > 3){
                    
                    System.out.println("\nIntroduce la clase de navio que quieres: \n1.-Goleta \n2.-Fragata \n3.-Buque\n");
                    
                    seaShip = keyboard.nextInt();
                    
                }
                
                File file = new File("File1" + ".bin");

                while(file.exists() && increase < 4){
                    
                     increase++;
                     
                     if (increase < 4){
                         
                        file = new File("File" + increase+ ".bin");
                     }

                } 
                
                if (increase==4){
                    
                    String[] slotNames = new String[3];
                    
                    String[] dates = new String [3];
                    
                    for (int i = 0; i < 3; i++) {
                        String slot = "File" + (i+1) + ".bin";
                        File fileExist = new File(slot);
                        if (fileExist.exists()){
                            ArrayList saveSlot = DataManager.readFile(slot);
                            String slotName = (String)saveSlot.get(1);
                            String date = (String)saveSlot.get(14);
                            slotNames [i] = slotName;
                            dates[i] = date;
                        }

                    } 

                    System.out.println("\nTodas las ranuras están ocupadas ¿Quieres sobrescribir alguna? \n1.-" + slotNames [0] + " -> " + dates[0] + "\n2.-" + slotNames [1] + " -> " + dates[1] + "\n3.-" + slotNames [2] + " -> " + dates[2] + "\n4.-Salir\n");
                    
                   while(option < 1 || option > 4) {

                    option = keyboard.nextInt();
                    
                   }

                    switch (option){
                        
                        case 1 :
                            
                            increase=1;
                            
                            break;
                        case 2 :
                            
                            increase=2;
                            
                            break;
                        case 3 :         
                            
                            increase=3;
                            
                        break;
                        case 4 :     
                            
                            increase=4;
                            
                            loadType = 0;
                            
                        break;
                    }
                    
                    file = new File("File" + increase+ ".bin");
                    
                }
                
                if(increase < 4){
                    
                try {
            
                 FileOutputStream fos = new FileOutputStream(file);
            
                 DataOutputStream dos = new DataOutputStream(fos);
                 
                 dos.writeUTF("i_sh:" + (seaShip - 1) + "\n");

                 dos.writeUTF("s_nb:" + name + "\n");
                 
                 dos.writeUTF("s_sn:" + subname + "\n");
                 
                 dos.writeUTF("i_cn:" + (country -1) + "\n");
                 
                 dos.writeUTF("---\n");
                 
                 dos.writeUTF("f_fl:" + increase + "\n");
                     
                 dos.writeUTF("b_nw:" + 0 + "\n");
                     
                 dos.writeUTF("<>");

                 dos.close();

                }catch (IOException e){
   }
                
        }
        
                loadInf.add(0, file);
                
                loadInf.add(1, increase);
                
                loadInf.add(2, loadType);
                
                return loadInf;
                
    }
    
    public static ArrayList loadData(int loadType) throws IOException{
        
        Scanner keyboard = new Scanner(System.in);
        
        ArrayList loadInf = new ArrayList ();
        
        File file;
        
        int increase = 1;
        
        int option = 0;
        
         String[] slotNames = {"Slot-1","Slot-2","Slot-3"};
               
               String[] dates = new String [3];
                    
                    for (int i = 0; i < 3; i++) {
                        String slot = "File" + (i+1) + ".bin";
                        File fileExist = new File(slot);
                        if (fileExist.exists()){
                            ArrayList saveSlot = DataManager.readFile(slot);
                            String slotName = (String)saveSlot.get(1);
                            String date = (String)saveSlot.get(14);
                            slotNames [i] = slotName;
                            dates[i] = date;
                        }

                    } 
                    
                    file = new File("");
                    
                    while(!file.exists() && option != 4){
                        
                        System.out.println("\nPartidas disponibles \n1.-" + slotNames [0] + " -> " + dates[0] + "\n2.-" + slotNames [1] + " -> " + dates[1] + "\n3.-" + slotNames [2] + " -> " + dates[2] + "\n4.-Atras\n");
                    
                        option = keyboard.nextInt();
                    
                    switch (option){
                        
                        case 1 :
                            
                            increase=1;
                            
                            break;
                        case 2 :
                            
                            increase=2;
                            
                            break;
                        case 3 : 
                            
                            increase=3;
                            
                        break;
                        
                        case 4 :                          
                            loadType = 0;                           
                        break;
                    }
                    
                    file = new File("File" + increase + ".bin");
                    
                    }
                                  
                loadInf.add(0, file);
                
                loadInf.add(1, increase);
                
                loadInf.add(2, loadType);
                
                return loadInf;
        
    }
    
    public static SeaShip generateGameData(ArrayList saveData, int loadType) throws IOException{
        
        ArrayList<SeaShip> myBoat = new ArrayList<SeaShip>();
        
        if((int)saveData.get(0) == 2){
            
            SeaShipWar player_War = new SeaShipWar();
            
            if(loadType == 1 || saveData.size() == 6){
                
                player_War = new SeaShipWar((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5));
                
                player_War.createTripulation();          
                
            }
            else{
                
                if(loadType == 2){
                    
                    player_War = new SeaShipWar((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5), (int)saveData.get(6), (int)saveData.get(7),(int)saveData.get(8),(int)saveData.get(9),(int)saveData.get(10),(int [])saveData.get(11), (int)saveData.get(12), (int)saveData.get(13));

                    player_War.loadTripulation();
                    
                    player_War.worker_list.get(0).createCaptain(player_War.getName(), player_War.getSubname());
                    
                }

            }
            
            myBoat.add(0, player_War);
            
        }
        if((int)saveData.get(0) == 1){
            
            SeaShipFrigate player_Frigate = new SeaShipFrigate();
            
            if(loadType == 1 || saveData.size() == 6){
                
                player_Frigate = new SeaShipFrigate((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5));
                
                player_Frigate.createTripulation();          
                
            }
            else{
                
                if(loadType == 2){
                    
                    player_Frigate = new SeaShipFrigate((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5), (int)saveData.get(6), (int)saveData.get(7),(int)saveData.get(8),(int)saveData.get(9),(int)saveData.get(10),(int[])saveData.get(11), (int)saveData.get(12), (int)saveData.get(13));

                    player_Frigate.loadTripulation();
                    
                    player_Frigate.worker_list.get(0).createCaptain(player_Frigate.getName(), player_Frigate.getSubname());
                    
                }

            }
            
            myBoat.add(0, player_Frigate);
            
        }
        if((int)saveData.get(0) == 0){
            
            SeaShipSchooner player_Schooner = new SeaShipSchooner();
            
            if(loadType == 1 || saveData.size() == 6){
                
                player_Schooner = new SeaShipSchooner((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5));
                
                player_Schooner.createTripulation();          
                
            }
            else{
                
                if(loadType == 2){
                    
                    player_Schooner = new SeaShipSchooner((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5), (int)saveData.get(6), (int)saveData.get(7),(int)saveData.get(8),(int)saveData.get(9),(int)saveData.get(10),(int [])saveData.get(11), (int)saveData.get(12), (int)saveData.get(13));

                    player_Schooner.loadTripulation();
                    
                    player_Schooner.worker_list.get(0).createCaptain(player_Schooner.getName(), player_Schooner.getSubname());
                    
                }

            }
            
            myBoat.add(0, player_Schooner);
            
        }
        
        return myBoat.get(0);
        
    }
    
    public static void saveData(SeaShip seaShip) throws IOException{
        
        Scanner keyboard = new Scanner (System.in);
        
        File file;
        
        int saveSlotInt = seaShip.getSaveSlot();
        
        int option = 0;
        
        int typeShip = 0;
        
        Date date = new Date();
        
        String saveDate = date.toString().substring(4, 7) + "-" + date.toString().substring(8, 10) + "-" + date.toString().substring(25, 29);
        
        String[] slotNames = {"Slot-1","Slot-2","Slot-3"};
                    
        for (int i = 0; i < 3; i++) {
            String slot = "File" + (i+1) + ".bin";
            file = new File(slot);
            if (file.exists()){
                ArrayList saveSlot = readFile(slot);
                String slotName = (String)saveSlot.get(1);
                slotNames [i] = slotName;
            }
            
        }
        
        while(option != 1 && option != 2){
            
            System.out.println("\n¿Deseas guardar en la ranura por defecto? \n1.-Sí \n2.-No\n");
            
            option = keyboard.nextInt();  
            
        }           
        
        if (option == 2){
            
            saveSlotInt = -1;
            
            while (saveSlotInt < 0 || saveSlotInt > 3){
                
                System.out.println("\n¿Qué ranura deseas sobreescribir? \n1.-" + slotNames[0] + "\n2.-" + slotNames[1] + "\n3.-" + slotNames[2] + "\n");
            
                saveSlotInt = keyboard.nextInt();  
                
            }

        }
        
        if(seaShip.getClass().getSimpleName().charAt(7) == 83 ){
            
            typeShip = 0;
            
        }
        else{
            
            if(seaShip.getClass().getSimpleName().charAt(7) == 70 ){
            
            typeShip = 1;
            
            }
            else{
                
               if(seaShip.getClass().getSimpleName().charAt(7) == 87 ){
            
                typeShip = 2;
            
                }
               else{
                   
                   System.out.println("\nError en los datos");
                   
               }
                
            }
            
        }

        file = new File("File" + saveSlotInt + ".bin");
        
        try {
            
                FileOutputStream fos = new FileOutputStream(file);
            
                DataOutputStream dos = new DataOutputStream(fos);
                 
                dos.writeUTF("i_sh:" + typeShip + "\n");

                dos.writeUTF("s_nb:" + seaShip.getName() + "\n");
                
                dos.writeUTF("s_sn:" + seaShip.getSubname() + "\n");
                 
                dos.writeUTF("i_cn:" + seaShip.getCountry() + "\n");

                dos.writeUTF("i_hl:" + (int)seaShip.getHealth() + "\n");
                 
                dos.writeUTF("i_ar:" + seaShip.getArmor() + "\n");
                 
                dos.writeUTF("i_ac:" + seaShip.getCannon_ammo() + "\n");
                 
                dos.writeUTF("i_am:" + seaShip.getMusket_ammo() + "\n");
                 
                dos.writeUTF("i_gd:" + seaShip.getGold() + "\n");
                 
                dos.writeUTF("---\n");
                     
                dos.writeUTF("i_ap:" + (int)seaShip.getAttackP() + "\n");
                     
                dos.writeUTF("i_dp:" + (int)seaShip.getDefenseP() + "\n");
                     
                dos.writeUTF("a_bdt:" + seaShip.getBeds()[0] + "\n");
                     
                dos.writeUTF("a_bdm:" + seaShip.getBeds()[1] + "\n");
                     
                dos.writeUTF("a_bda:" + seaShip.getBeds()[2] + "\n");
                     
                dos.writeUTF("f_fl:" + saveSlotInt + "\n");
                     
                dos.writeUTF("b_nw:" + seaShip.getNewGame() + "\n");

                dos.writeUTF("d_dt:" + saveDate + "\n");
                     
                dos.writeUTF("<>\n");

                for (int i = 0; i < seaShip.getBeds()[2]; i++) {
                
                    String worker = "w_";
                    
                    if (i <= 9){
                        
                        worker = worker + "0";
                        
                    }
                    
                    worker = worker + i + ":" + seaShip.getWorker_list().get(i).getName() + " " + seaShip.getWorker_list().get(i).getSubname() + " " + seaShip.getWorker_list().get(i).getRange() + "\n";
                    
                    dos.writeUTF(worker);
                    
                }      
                
                dos.writeUTF("<>");
                
                dos.close();

                }catch (IOException e){
   }
        
    }
    
    public static ArrayList readFile(String file) throws FileNotFoundException, IOException{
        
        ArrayList saveData = new ArrayList();
        
        int a_data [] = new int[] {0,0,0,0};
        
        String lines;
        
        try{
            File readFile = new File(file);
            
            FileInputStream fis = new FileInputStream(readFile);
            
            DataInputStream dis = new DataInputStream(fis);
            
            while (true) {

                lines = dis.readUTF();

                int information = 0;

                String s_data = "";

                int i_data = -1;

                boolean string = false;

                boolean array = false;

                if (lines.charAt(0) == 115 || lines.charAt(0) == 100) {
                    string = true;

                }

                if (lines.charAt(0) == 97) {
                    array = true;

                }

                for (int i = 0; i < lines.length(); i++) {

                    if (lines.charAt(i) == 58 && lines.charAt(0) != 119) {
                        information = 1;
                    }

                    if (information == 1 && lines.charAt(i) != 32 && lines.charAt(i) != 58 && lines.charAt(i) != 10) {

                        s_data = s_data + lines.charAt(i);

                    }

                }

                if (s_data != "") {

                    if (string == false) {

                        i_data = Integer.parseInt(s_data);

                        if (array == false) {

                            saveData.add(i_data);

                        } else {

                            a_data[a_data[3]] = i_data;

                            a_data[3] = a_data[3] + 1;

                            if (a_data[3] == 2) {

                                saveData.add(a_data);

                            }
                        }

                    } else {

                        saveData.add(s_data);

                    }

                }

            }
            
        }catch(IOException e){}

        
        return saveData;
    }
    
    public static boolean validateString(String name){
        
        boolean valid = false;
        
        int i = 0;
        
        while( i < name.length() && ((name.charAt(i) >= 97 && name.charAt(i) <= 122) || (name.charAt(i) >= 65 && name.charAt(i) <= 90))){

            i++;
            
        }
        
        if (i == name.length()){
            
            valid = true;
            
        }
        else{
            
            System.out.println("\nSolo carácteres de la A la Z, en mayúscula o minúscula, sin acentuar");
            
        }
        
        return valid;
    }
    
}
