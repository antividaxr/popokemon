public class Persona {
    private String nombre;
    private int edad;
    private String ciudad;

    public Persona(String nombre, int edad, String ciudad){
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }
    public String getnombre(){
        return nombre;
    }
    public int getedad() {
        return edad;
    }

    public String getcuidad() {
        return ciudad;
    }
}
