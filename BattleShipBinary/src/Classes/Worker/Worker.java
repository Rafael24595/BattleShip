
package Classes.Worker;

public class Worker {

    private int name;
    private String[] nameS = {"William", "James", "Benjamin", "Elijah", "Ethan", "Alexander", "Matthew", "Aiden", "Jackson", "Sebastian", "David", "Carter", "John", "Owen", "Isaac", "Jack", "Christopher", "Lincoln", "Andrew", "Thomas", "---"};
    private int subname;
    private String[] subnameS = {"Smith", "Johnson", "Jones", "Brown", "Miller", "Wilson", "Anderson", "Nelson", "Murphy", "Thompson", "Robinson", "Edwards", "Lee", "Stewart", "Rogers", "Wood", "Price", "Ward", "Torres", "Peterson", "---"};
    private int range;
    private String[] rangeS = {"Capitan", "Soldado", "Trabajador de cubierta"};

    public Worker() {
    }

    public Worker(int name, int subname, int range) {
        this.name = name;
        this.subname = subname;
        this.range = range;
    }

    public int getName() {
        return name;
    }

    public String[] getNameS() {
        return nameS;
    }

    public int getSubname() {
        return subname;
    }

    public String[] getSubnameS() {
        return subnameS;
    }

    public int getRange() {
        return range;
    }

    public String[] getRangeS() {
        return rangeS;
    }

    public void setNameS(String[] nameS) {
        this.nameS = nameS;
    }

    public void setSubnameS(String[] subnameS) {
        this.subnameS = subnameS;
    }

    public void createCaptain(String name, String subname){
        nameS[20]=name;
        subnameS[20]=subname;
    }
    
    public String captainToString() {
        return nameS[20] + " " + subnameS[20];
    }
    
    public String nameToString(int name) {
        return nameS[name];
    }
    
    public String subnameToString(int subname) {
        return subnameS[subname];
    }
    
    public String rangeToString(int range) {
        return rangeS[range];
    }

}

