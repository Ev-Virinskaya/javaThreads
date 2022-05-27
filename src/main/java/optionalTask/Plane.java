package optionalTask;

public class Plane{
    private String name;


    public Plane() {
    }

    public Plane(String name) {
        this.name = name;
    }


    public void runwayExit() {
        System.out.println("Самолет " + name + " начал выход на полосу");
    }
    public void departure(){
        System.out.println("Самолет " + name + " взлетел");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
