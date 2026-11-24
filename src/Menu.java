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
        cargarPokemones();
        cargarEntrenadores();
    }

private void cargarPokemones() {
    Random azar = new Random();
    try (BufferedReader lector = new BufferedReader(new FileReader("pokemones.csv"))) {
        lector.readLine();
        String linea;
        while ((linea = lector.readLine()) != null) {
            String[] partes = linea.split(",", -1);
            int numero = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            Tipo tipo1 = Tipo.valueOf(partes[2]);
            Tipo tipo2 = null;
            if (!partes[3].isEmpty()) {
                tipo2 = Tipo.valueOf(partes[3]);
            }
            Caracter caracter = Caracter.values()[azar.nextInt(10)];
            pokedex.add(new Pokemon(numero, nombre, tipo1, tipo2, caracter));
        }
        System.out.println(pokedex.size());
    }
    catch(IOException e){
        System.out.println("No se pudo leer pokemones.csv: " + e.getMessage());
    }
}
private void cargarEntrenadores(){
        try (BufferedReader lector = new BufferedReader(new FileReader("entrenadores.csv"))){
            lector.readLine();
            String linea;
            while ((linea = lector.readLine()) != null){
                String [] partes = linea.split(",", -1);
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                int edad = Integer.parseInt(partes[2]);
                String ciudad = partes[3];
                Entrenador entrenador = new Entrenador(id,nombre,edad,ciudad);
                if (!partes[4].isEmpty()){
                    for(String nombremedalla : partes[4].split(";")){
                        entrenador.agregarMedalla(Medallas.valueOf(nombremedalla));
                    }
                }
                entrenadores.add(entrenador);
            }
            System.out.println(entrenadores.size());
        }
        catch (IOException i) {
            System.out.println("No se pudo leer entrenadores.csv:" + i.getMessage());
        }
}
}