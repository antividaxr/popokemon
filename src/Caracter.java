public enum Caracter {
    DOCIL(1),
    AMABLE(2),
    ABEDIENTE(3),
    CALMADO(4),
    TRAVIESO(5),
    INDISIPLINADO(6),
    TERCO(7),
    REVELDE(8),
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
