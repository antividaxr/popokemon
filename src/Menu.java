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
            while ((linea = lector.readLine()) != null){
                String[] partes = linea.split(",",-1);
                int numero = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                Tipo tipo1 = Tipo.valueOf(partes[2]);
                Tipo tipo2 = null;
                if (!partes[3].isEmpty()){
                    tipo2 = Tipo.valueOf(partes[3]);
                }
                Caracter caracter = Caracter.values()[azar.nextInt(11)];
                pokedex.add(new Pokemon(numero,nombre,tipo1,tipo2,caracter));
            }
            catch (IOException e){
                System.out.println("No se pudo leer pokemones.csv: " + e.getMessage());
            }
            System.out.println(pokedex.size());
        }
        cargarpokemones();
}
private void cargarEntrenadores(){
        Random azar = new Random();
        try (BufferedReader lector1 = new BufferedReader(new FileReader("entrenadores.csv"))){
            lector1.readLine();
            String linea;
            while ((linea = lector1.readLine()) != null){
                String [] partes = linea.split(",", -1);
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                int edad = Integer.parseInt(partes[2]);
                String ciudad = partes[3];
                Medallas medallas = Medallas.valueOf(partes[4]);
                entrenadores.add(new Entrenador(id,nombre,edad,ciudad,medallas));
            }
            catch (IOException i){
                System.out.println("No se pudo leer entrenadores.csv" +i.getMessager());
            }
            System.out.println(entrenadores.size());
        }
        cargarEntrenadores();
}
}