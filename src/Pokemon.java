public class Pokemon {
    private int numero;
    private String nombre;
    private Tipo tipo1;
    private Tipo tipo2;
    private Caracter caracter ;

    public Pokemon(int numero, String nombre, Tipo tipo1, Tipo tipo2, Caracter caracter) {
        this.numero = numero;
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.caracter = caracter;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public Tipo getTipo1() {
        return tipo1;
    }

    public Tipo getTipo2() {
        return tipo2;
    }
    public Caracter getCaracter() {
        return caracter;
    }

    @Override
    public String toString() {
        String res = "#" + numero + " " + nombre + "(" + caracter + ")" +"(" + tipo1;
        if (tipo2 != null){
        res = res + "/" + tipo2;
        }
        res = res + ")";
        return res;
    }
}
