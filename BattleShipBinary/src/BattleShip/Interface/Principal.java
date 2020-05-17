
package BattleShip.Interface;

import BattleShip.Classes.Ship.SeaShip;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        ArrayList<SeaShip> myBoat = new ArrayList<>();
        
        System.out.println(new StringBuilder("---------------------------")
                                   .append("\n| Bienvenido a BattleShip |")
                                   .append("\n---------------------------"));
        
        myBoat.add(Menu.opening());
        
        Menu.menu(myBoat.get(0));

    }
    
}
