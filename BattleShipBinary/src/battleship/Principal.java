
package battleship;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Principal {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        ArrayList<SeaShip> myBoat = new ArrayList<SeaShip>();
        
        System.out.println("---------------------------"
                       + "\n| Bienvenido a BattleShip |"
                       + "\n---------------------------");
        
        myBoat.add(Menu.opening());
        
        Menu.menu(myBoat.get(0));
        
        Date date = new Date();
        
        String ds = date.toString();
        
        String d = date.toString().substring(4, 7) + "-" + date.toString().substring(8, 10) + "-" + date.toString().substring(25, 29) + "-" + date.toString().substring(11, 16);
        
        System.out.println(ds);
        
        System.out.println(d);
    }
    
}
