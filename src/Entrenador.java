import java.util.ArrayList;

public class Entrenador extends Persona {
    private int id;
    private ArrayList<Medallas> medallas;
    private ArrayList<Pokemon> equipo;

    public Entrenador(int id, String nombre, int edad, String ciudad) {
        super(nombre, edad, ciudad);
        this.id = id;
        this.medallas = new ArrayList<>();
        this.equipo = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Medallas> getMedallas() {
        return new ArrayList<>(medallas);
    }

    public ArrayList<Pokemon> getEquipo() {
        return new ArrayList<>(equipo);
    }

    private static final int LIMITE = 5;

    public boolean agregarpokemon(Pokemon pokemon) {
        if (equipo.size() >= LIMITE) {
            return false;
        }
        equipo.add(pokemon);
        return true;
    }

    public boolean agregarMedalla(Medallas medalla) {
        if (medallas.contains(medalla)) {
            return false;
        }
        medallas.add(medalla);
        return true;
    }
}