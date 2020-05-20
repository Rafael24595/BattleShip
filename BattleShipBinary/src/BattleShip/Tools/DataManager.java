
package BattleShip.Tools;

import BattleShip.Classes.Ship.War.SeaShipWar;
import BattleShip.Classes.Ship.Schooner.SeaShipSchooner;
import BattleShip.Classes.Ship.Frigate.SeaShipFrigate;
import BattleShip.Classes.Ship.SeaShip;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataManager {

    public static ArrayList newSaveData(int loadType) throws IOException {

        Scanner keyboard = new Scanner(System.in);
        
        Date date = new Date();
        
        String saveDate = String.format("%s-%s-%s", date.toString().substring(4, 7), date.toString().substring(8, 10), date.toString().substring(25, 29));

        File file = new File("File1.bin");
        
        ArrayList loadInf = new ArrayList();
        
        boolean verify = false;

        int increase = 1;

        String name = "";

        String subname = "";
        
        int country = 0;
        
        int seaShip = 0;

        while (verify == false) {

            System.out.print("\nIntroduce un nombre: ");

            name = keyboard.next();

            verify = DataManager.validateString(name);

        }

        verify = false;

        while (verify == false) {

            System.out.print("\nIntroduce un apellido: ");

            subname = keyboard.next();

            verify = DataManager.validateString(subname);

        }

        while (country < 1 || country > 3) {
                
                System.out.println("\nAhora elige una de las siguientes naciones: \n1.-España \n2.-Gran Bretaña \n3.-Francia\n");

                country = DataManager.catchIntException();

        }

        while (seaShip < 1 || seaShip > 3) {
                
                System.out.println("\nIntroduce la clase de navio que quieres: \n1.-Goleta \n2.-Fragata \n3.-Buque\n");

                seaShip = DataManager.catchIntException();

        }

        while (file.exists() && increase < 4) {

            increase++;

            if (increase < 4) {

                file = new File(String.format("File%d.bin", increase));
            }

        }

        
        
        if (increase == 4) {

            String[] slotNames = DataManager.fileName();

            do {
                
                    System.out.printf("\nTodas las ranuras están ocupadas ¿Quieres sobrescribir alguna? \n1.-%s -> %s\n2.-%s -> %s\n3.-%s -> %s\n0.-Salir\n\n", slotNames[0], slotNames[3], slotNames[1], slotNames[4], slotNames[2], slotNames[5]);

                    increase = DataManager.catchIntException();

            } while (increase < 0 || increase > 3);

            if (increase == 0) {

                loadType = 0;

            }

        }
        
        if (increase != 0 && increase < 4) {

            try {

                FileOutputStream fos = new FileOutputStream(new File(String.format("File%d.bin", increase)));

                DataOutputStream dos = new DataOutputStream(fos);

                dos.writeUTF(String.format("i_sh:%d\n", (seaShip - 1)));

                dos.writeUTF(String.format("s_nb:%s\n", name));

                dos.writeUTF(String.format("s_sn:%s\n", subname));

                dos.writeUTF(String.format("i_cn:%d\n", (country - 1)));

                dos.writeUTF("---\n");

                dos.writeUTF(String.format("f_fl:%d\n", increase));

                dos.writeUTF(String.format("b_nw:%d\n", 0));

                dos.writeUTF(String.format("d_dt:%s\n", saveDate));

                dos.writeUTF("<>");

                dos.close();

                fos.close();

            } catch (IOException e){System.err.println("Error: De entrada o salida");}

        }

        loadInf.add(0, increase);

        loadInf.add(1, loadType);

        return loadInf;

    }

    public static ArrayList loadData(int loadType) throws IOException {
        
        String[] slotNames = DataManager.fileName();

        ArrayList loadInf = new ArrayList();

        int increase = 1;

        do {
                
                System.out.printf("\nPartidas disponibles \n1.-%s -> %s\n2.-%s -> %s\n3.-%s -> %s\n0.-Atras\n\n", slotNames[0], slotNames[3], slotNames[1], slotNames[4], slotNames[2], slotNames[5]);

                increase = DataManager.catchIntException();

        } while (increase != 0 && increase != Integer.parseInt(slotNames[6]) && increase != Integer.parseInt(slotNames[7]) && increase != Integer.parseInt(slotNames[8]));

        if (increase == 0) {

            loadType = 0;

        }

        loadInf.add(0, increase);

        loadInf.add(1, loadType);

        return loadInf;

    }
    
    public static void deleteSaveFile() throws IOException{

        String[] slotNames = DataManager.fileName();

        int delete = 0;
        
        if (Integer.parseInt(slotNames[6]) != 0 || Integer.parseInt(slotNames[7]) != 0 || Integer.parseInt(slotNames[8]) != 0) {

            do {

                    System.out.printf("\n¿Qué partida quieres borrar? \n1.-%s -> %s\n2.-%s -> %s\n3.-%s -> %s\n0.-Atras\n\n", slotNames[0], slotNames[3], slotNames[1], slotNames[4], slotNames[2], slotNames[5]);

                    delete = DataManager.catchIntException();

            } while (delete != 0 && delete != Integer.parseInt(slotNames[6]) && delete != Integer.parseInt(slotNames[7]) && delete != Integer.parseInt(slotNames[8]));

            if(delete != 0){
                
                File file = new File(String.format("File%d.bin", delete));
                
                file.delete();
                
            }
            
        }
        else{
            
            System.out.println("No hay partidas guardadas");
            
        }

    }
    
    public static SeaShip generateGameData(ArrayList saveData, int loadType) throws IOException{
        
        ArrayList<SeaShip> myBoat = new ArrayList<>();
        
        SeaShip player = new SeaShipSchooner();
        
        if((int)saveData.get(0) == 2){
            
            if(loadType == 1 || saveData.size() == 7){
                
                player = new SeaShipWar((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5));        
                
            }
            else if(loadType == 2){
   
                player = new SeaShipWar((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5), (int)saveData.get(6), (int)saveData.get(7),(int)saveData.get(8),(int)saveData.get(9),(int)saveData.get(10),(int [])saveData.get(11), (int)saveData.get(12), (int)saveData.get(13));
 
            }
            
        }
        
        if((int)saveData.get(0) == 1){
            
            if(loadType == 1 || saveData.size() == 7){
                
                player = new SeaShipFrigate((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5));       
                
            }
            else if(loadType == 2){
                    
                player = new SeaShipFrigate((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5), (int)saveData.get(6), (int)saveData.get(7),(int)saveData.get(8),(int)saveData.get(9),(int)saveData.get(10),(int[])saveData.get(11), (int)saveData.get(12), (int)saveData.get(13));

            }
            
        }
        
        if((int)saveData.get(0) == 0){
            
            if(loadType == 1 || saveData.size() == 7){
                
                player = new SeaShipSchooner((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5));      
                
            }
            else if(loadType == 2){
                    
                player = new SeaShipSchooner((String)saveData.get(1), (String)saveData.get(2), (int)saveData.get(3), (int)saveData.get(4), (int)saveData.get(5), (int)saveData.get(6), (int)saveData.get(7),(int)saveData.get(8),(int)saveData.get(9),(int)saveData.get(10),(int [])saveData.get(11), (int)saveData.get(12), (int)saveData.get(13));

            }
            
        }
        
        myBoat.add(0, player);
        
        if(loadType == 1 || saveData.size() == 7){
            
                myBoat.get(0).createTripulation(myBoat.get(0).getBeds()[2], 0);  
                
        }else if(loadType == 2){

                myBoat.get(0).loadTripulation();
                    
                myBoat.get(0).getWorker_list().get(0).createCaptain(myBoat.get(0).getName(), myBoat.get(0).getSubname());

        }
        
        return myBoat.get(0);
        
    }
    
    public static void saveData(SeaShip seaShip) throws IOException{
        
        int saveSlotInt = seaShip.getSaveSlot();
        
        int option = 0;
        
        int typeShip = 0;
        
        Date date = new Date();
        
        String saveDate = new StringBuilder(date.toString().substring(4, 7) + "-" + date.toString().substring(8, 10) + "-" + date.toString().substring(25, 29)).toString();
        
        String[] slotNames = DataManager.fileName();
        
        while(option != 1 && option != 2){
                
                System.out.println("\n¿Deseas guardar en la ranura por defecto? \n1.-Sí \n2.-No\n");
            
                option = DataManager.catchIntException();
            
        }           
        
        if (option == 2){
            
            saveSlotInt = -1;
            
            while (saveSlotInt < 0 || saveSlotInt > 3){
                
                System.out.printf("\n¿Qué ranura deseas sobreescribir? \n1.-%s\n2.-%s\n3.-%s\n\n", slotNames[0], slotNames[1], slotNames[2]);
            
                saveSlotInt = DataManager.catchIntException();
                
            }

        }
        
        if(seaShip.getClass().getSimpleName().charAt(7) == 83 ){
            
            typeShip = 0;
            
        }
        else if(seaShip.getClass().getSimpleName().charAt(7) == 70 ){
            
            typeShip = 1;
            
        }
        else if(seaShip.getClass().getSimpleName().charAt(7) == 87 ){
            
            typeShip = 2;
            
        }
        else{
        
            System.out.println("\nError en los datos");
        
        }
        
        try {
            
            FileOutputStream fos = new FileOutputStream(new File("File" + saveSlotInt + ".bin"));

            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeUTF(String.format("i_sh:%d\n", typeShip));

            dos.writeUTF(String.format("s_nb:%s\n", seaShip.getName()));

            dos.writeUTF(String.format("s_sn:%s\n", seaShip.getSubname()));

            dos.writeUTF(String.format("i_cn:%d\n", seaShip.getCountry()));

            dos.writeUTF(String.format("i_hl:%d\n", (int) seaShip.getHealth()));

            dos.writeUTF(String.format("i_ar:%d\n", seaShip.getArmor()));

            dos.writeUTF(String.format("i_ac:%d\n", seaShip.getCannon_ammo()));

            dos.writeUTF(String.format("i_am:%d\n", seaShip.getMusket_ammo()));

            dos.writeUTF(String.format("i_gd:%d\n", seaShip.getGold()));

            dos.writeUTF("---\n");

            dos.writeUTF(String.format("i_ap:%d\n", (int) seaShip.getAttackP()));

            dos.writeUTF(String.format("i_dp:%d\n", (int) seaShip.getDefenseP()));

            dos.writeUTF(String.format("a_bdt:%d\n", seaShip.getBeds()[0]));

            dos.writeUTF(String.format("a_bdm:%d\n", seaShip.getBeds()[1]));

            dos.writeUTF(String.format("a_bda:%d\n", seaShip.getBeds()[2]));

            dos.writeUTF(String.format("f_fl:%d\n", saveSlotInt));

            dos.writeUTF(String.format("b_nw:%d\n", seaShip.getNewGame()));

            dos.writeUTF(String.format("d_dt:%s\n", saveDate));

            dos.writeUTF("<>\n");
            
            for (int i = 0; i < seaShip.getBeds()[2]; i++) {
                
                StringBuilder data = new StringBuilder();
                
                data.append("w_");
                    
                    if (i <= 9){
                        
                        data.append("0");
                        
                    }
                    
                    data.append(i).append(":").append(seaShip.getWorker_list().get(i).getName()).append(" ").append(seaShip.getWorker_list().get(i).getSubname()).append(" ").append(seaShip.getWorker_list().get(i).getRange()).append("\n");
                    
                    dos.writeUTF(data.toString());
                    
                }  
            
                dos.writeUTF("<>");
                
                dos.close();
                
                fos.close();
                
                }catch (IOException e){System.err.println("Error: De entrada o salida");}
        
    }
    
    public static ArrayList readFile(String file) throws FileNotFoundException, IOException{
        
        ArrayList saveData = new ArrayList();
        
        int a_data [] = new int[] {0,0,0};
        
        String lines;
        
        int cont=0;
        
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file));){       
                
                while (true) {

                lines = dis.readUTF();
                
                boolean string = false;

                boolean array = false;

                String s_data = "";

                int i_data = -1;

                if (lines.charAt(0) == 115 || lines.charAt(0) == 100) {
                    
                    string = true;

                }

                if (lines.charAt(0) == 97) {
                    
                    array = true;

                }

                if (lines.indexOf(":") != -1 && lines.charAt(0) != 119) {

                    s_data = lines.split(":")[1].replaceAll("\n", "");
                    
                    if (string == false) {
                        
                        i_data = Integer.parseInt(s_data);

                        if (array == false) {

                            saveData.add(i_data);

                        } else {

                            a_data[cont] = i_data;

                            cont++;
                            
                            if(cont==2){
                                
                                saveData.add(a_data);
                                
                            }
                            
                        }

                    } else {

                        saveData.add(s_data);

                    }

                }     
                
            }
        }catch(FileNotFoundException e){System.err.println("Error: Archivo no encontrado");
        }catch(EOFException e){System.out.println("\nArchivo leido");   
        }catch(IOException e){System.err.println("Error: De entrada o salida"); }
        
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
 
    public static String[] fileName() throws IOException {
 
        String[] slotNames = {"Slot-1", "Slot-2", "slot-3", "Empty-Date", "Empty-date", "Empty-Date", "0", "0", "0"};

        for (int i = 0; i < 3; i++) {
            String slot = String.format("File%d.bin", i + 1);
            File fileExist = new File(slot);
            if (fileExist.exists()) {
                ArrayList saveSlot = DataManager.readFile(slot);
                String slotName = (String) saveSlot.get(1);
                slotNames[i] = slotName;
                if(saveSlot.size() > 10){
                    String date = (String) saveSlot.get(14);
                    slotNames[i+3] = date;
                }
                else{
                    String date = (String) saveSlot.get(6);
                    slotNames[i+3] = date;
                }
                slotNames[i + 6] = String.valueOf(i+1);
            }

        }
        
        return slotNames;
    }
    
    public static int catchIntException() throws IOException {
 
        Scanner keyboard = new Scanner(System.in);
            
            try{
            
            return keyboard.nextInt();
            
        }catch (InputMismatchException | NumberFormatException e) {keyboard.next(); System.out.println("\nIntroduce uno de los números indicados");}
            
            return -1;
            
    }
    
}
