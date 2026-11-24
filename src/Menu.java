import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private ArrayList<Pokemon> pokedex;
    private ArrayList<Entrenador> entrenadores;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.pokedex = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
    }

private void cargarpokemones(){
        Random azar = new Random();
        try (BufferedReader lector = new BufferedReader(new FileReader("pokemones.csv"))){
            lector.readLine();
            String linea;
            while ((linea = lector.readLine()) ! = null){
                String[] partes = linea.split(",",-1);
                int numeero = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                Tipo tipo1 = Tipo.valueOf(partes[2]);
                Tipo tipo2 = null;
                if (!partes[3])
            }
        }
}
}