void main() {
Entrenador e = new Entrenador(1,"Ash Ketchum", 10 ,"Pueblo Paleta");
Pokemon p = new Pokemon(25, "Pikachu", Tipo.ELECTRICO, null, Caracter.TRAVIESO);
for (int i = 0; i < 6; i++){
    System.out.println(e.agregarPokemon(p));
}
e.agregarMedalla(Medallas.ROCA);
System.out.println(e);
}