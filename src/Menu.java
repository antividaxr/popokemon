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
    private Entrenador entrenadorActual;

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
            System.out.println("3. Escojer Pokemones");
            System.out.println("4. Añadir Pokemon");
            System.out.println("5. Ver equipo");
            System.out.println("6. Cerrar");
            System.out.println("Opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> cargarArchivos();
                case 2 -> escogerEntrenador();
                case 3 -> escogerPokemon();
                case 4 -> agregarPokemon();
                case 5 -> verEquipo();
                case 6 -> System.out.println("Haste luedo FIN");
                default -> System.out.println("Es e 1 a 6, sabes leer?");
            }
        }
        while (opcion != 6);
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
            System.out.println("1.Volver al menu anterior");
            for(int i = 0; i < entrenadores.size(); i++){
                System.out.println((i + 2) + ". " + entrenadores.get(i).getnombre());
            }
            System.out.println("Opcion: ");
            opcion = leerEntero();

            if (opcion >= 2 && opcion <= entrenadores.size() + 1){
                menuEntrenador (entrenadores.get(opcion - 2));
            }
            else if (opcion != 1){
                System.out.println("Opcion invalidaaa");
            }
        }
        while (opcion != 1);
    }
    private void menuEntrenador(Entrenador entrenador){
        entrenadorActual = entrenador;
        System.out.println("Se selecciono a " + entrenador.getnombre());
    }

    private void escogerPokemon(){
        if(entrenadorActual == null){
            System.out.println("primero escoje un entrenador en el primer menu (opcion 2)");
            return;
        }
        if (pokedex.isEmpty()){
            System.out.println("Primero carga los archivos (opcion 1)");
            return;
        }
        int opcion;
        do{
            System.out.println("=== Busca pokemones ===");
            System.out.println("1.Volver al menu anterior");
            for(int i = 0; i < pokedex.size(); i++){
                System.out.println((i + 2) + ". " + pokedex.get(i).getNombre());
            }
            System.out.println("Opcion: ");
            opcion = leerEntero();

            if (opcion >= 2 && opcion <= pokedex.size() + 1){
                menuPokedex(pokedex.get(opcion -2));
            }
            else if(opcion != 1){
                System.out.println("Opcion invalida =(");
            }
        }
        while (opcion != 1);
    }
    private void menuPokedex(Pokemon pokemon){
        if(entrenadorActual.agregarPokemon(pokemon)){
            System.out.println(pokemon.getNombre() +" se agrego al equipo de " + entrenadorActual.getnombre());
        }
        else {
            System.out.println("El equipo esta en el limite(5 pokemones)");
        }
    }

    private void verEquipo(){
        if(entrenadorActual == null) {
            System.out.println("Primero escoge entrenador (opcion 2)");
            return;
        }
        System.out.println("\n" + entrenadorActual);
        for(Pokemon P : entrenadorActual. getEquipo()){
            System.out.println("- " + P);
        }
    }

    private int leerEnRango(String mensaje, int min, int max){
        int valor;
        do{
            System.out.println(mensaje);
            valor = leerEntero();
            if(valor < min || valor > max){
                System.out.println("Escribe un numero entre " + min + " y " + max);
            }
        }
        while (valor < min || valor > max);
        return valor;
    }

    private void mostrartipos(){
        Tipo[] tipos = Tipo.values();
        for(int i = 0 ; i < tipos.length; i++){
            System.out.println((i = 1) + ". " + tipos[1]);
        }
    }

    private void agregarPokemon(){
        if(pokedex.isEmpty()){
            System.out.println("Primero cargar los archivos (opcion 1)");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()){
            System.out.println("El nombre no puede estar vacio");
            return;
        }
        for (Pokemon p : pokedex){
            if(p.getNombre().equalsIgnoreCase(nombre)){
                System.out.println("Ese pokemon ya existe");
                return;
            }
        }
        int cantidad = Tipo.values().length;
        mostrartipos();
        int eleccion1 = leerEnRango("Tipo 1: ", 1, cantidad);
        int eleccion2 = leerEnRango("Tipo 2 (0 = ninguno) : ", 0, cantidad);
        Tipo tipo1 = Tipo.values()[eleccion1 -1];
        Tipo tipo2 = null;
        if(eleccion2 != 0){
            tipo2 = Tipo.values()[eleccion2 -1];
        }

        int nivel = leerEnRango("Caracter (1 DOCIL - 10 INDOMABLE): ",1,10);
        Caracter caracter = Caracter.values()[nivel -1];

        Pokemon nuevo = new Pokemon(pokedex.size() +1,nombre,tipo1,tipo2,caracter);
        pokedex.add(nuevo);
        System.out.println("Se añadio a: "+ nuevo);
    }
}