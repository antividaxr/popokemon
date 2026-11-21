import java.util.ArrayList;

public class Entrenador {
private int id;
private ArrayList<Medallas> medallas;
private ArrayList<Pokemon> equipo;

public Entrenador(int id, String nombre, int edad, String ciudad) {
    super(nombre, edad, ciudad);
    this.id = id;
    this.medallas = new ArrayList<>();
    this.equipo = new ArrayList<>();
    }
}
