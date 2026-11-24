import java.io.BufferedReader;
import java.io.File;
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
        } catch (IOException e) {
            System.out.println("No se pudo leer pokemones.csv: " + e.getMessage());
        }
    }

    private void cargarEntrenadores() {
        try (BufferedReader lector = new BufferedReader(new FileReader("entrenadores.csv"))) {
            lector.readLine();
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",", -1);
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                int edad = Integer.parseInt(partes[2]);
                String ciudad = partes[3];
                Entrenador entrenador = new Entrenador(id, nombre, edad, ciudad);
                if (!partes[4].isEmpty()) {
                    for (String nombremedalla : partes[4].split(";")) {
                        entrenador.agregarMedalla(Medallas.valueOf(nombremedalla));
                    }
                }
                entrenadores.add(entrenador);
            }
            System.out.println(entrenadores.size());
        } catch (IOException i) {
            System.out.println("No se pudo leer entrenadores.csv:" + i.getMessage());
        }
    }

    private int leerEntero(){
        try{
            return Integer.parseInt(scanner.nextLine().trim());
        }
        catch (NumberFormatException e){
            return -1;
        }
    }

    private boolean existenArchivos(){
        File archivoPokemones = new File("pokemones.csv");
        File archivoEntrenadores = new File("entrenadores.csv");
        return archivoPokemones.exists() && archivoEntrenadores.exists();
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("=== POPOKEMON ===");
            System.out.println("1. Cargar archivos csv");
            System.out.println("2. Escojer Entrenador");
            System.out.println("3. Añadir Entrenador");
            System.out.println("4. Añadir Pokemon");
            System.out.println("5. Cerrar");
            System.out.println("Opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> cargarArchivos();
                case 2 -> escogerEntrenador();
                case 3 -> System.out.println("En proseso");
                case 4 -> System.out.println("En proseso");
                case 5 -> System.out.println("Hasta luego FIN");
                default -> System.out.println("Es e 1 a 5, sabes leer?");
            }
        }
        while (opcion != 5);
    }
    private void cargarArchivos () {
        if (!existenArchivos()) {
            System.out.println("Faltan los archivos tipo csv de la raiz del proyecto");
            return;
        }
        pokedex.clear();
        entrenadores.clear();
        cargarPokemones();
        cargarEntrenadores();
        System.out.println("Los archivos porkemones " + pokedex.size() + " y entrenadores " + entrenadores.size() + " ya estan cargados");
    }
    private void escogerEntrenador(){
        if(entrenadores.isEmpty()){
            System.out.println("Primero carga los archivos (opcion 1)");
            return;
        }
        int opcion;
        do{
            System.out.println("=== Entrenadores ===");
            System.out.println("1.Menu anterior");
            for(int i = 0; i < entrenadores.size(); i++){
                System.out.println((i + 2) + ". " +entrenadores.get(i).getnombre());
            }
            System.out.println("Opcion: ");
            opcion = leerEntero();

            if (opcion >= 2 && opcion <= entrenadores.size() + 1){
                menuEntrenador(entrenadores.get(opcion - 2));
            }
            else if (opcion != 1){
                System.out.println("Opcion invalidaaa");
            }
            while (opcion != 1);
        }
    }
    private void menuEntrenador(){
        System.out.println("Se selecciono a " + entrenadores);
    }
}