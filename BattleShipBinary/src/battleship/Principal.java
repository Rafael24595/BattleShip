
package battleship;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        ArrayList<SeaShip> myBoat = new ArrayList<>();
        
        System.out.println("---------------------------"
                       + "\n| Bienvenido a BattleShip |"
                       + "\n---------------------------");
        
        myBoat.add(Menu.opening());
        
        Menu.menu(myBoat.get(0));

    }
    
}
