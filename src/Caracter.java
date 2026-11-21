public enum Caracter {
    DOCIL(1),
    AMABLE(2),
    OBEDIENTE(3),
    CALMADO(4),
    TRAVIESO(5),
    INDICIPLINADO(6),
    TERCO(7),
    REBELDE(8),
    SALVAJE(9),
    INDOMABLE(10);

    private final int nivel;
    Caracter(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
