package br.com.dio.desafio.dominio;

/**
 * Faixas de progressão de um {@link Dev} conforme o XP acumulado.
 *
 * <p>Enums em Java são classes especiais: cada constante é uma instância, o que
 * permite guardar dados (XP mínimo, emblema) e comportamento junto do valor.</p>
 */
public enum Nivel {

    INICIANTE(0, "🌱"),
    JUNIOR(100, "🚀"),
    PLENO(300, "⭐"),
    SENIOR(600, "🏆"),
    MESTRE(1000, "👑");

    private final double xpMinimo;
    private final String emblema;

    Nivel(double xpMinimo, String emblema) {
        this.xpMinimo = xpMinimo;
        this.emblema = emblema;
    }

    public double getXpMinimo() {
        return xpMinimo;
    }

    public String getEmblema() {
        return emblema;
    }

    /**
     * Resolve o nível correspondente a uma quantidade de XP, retornando a maior
     * faixa cujo mínimo seja atingido.
     */
    public static Nivel doXp(double xp) {
        Nivel atual = INICIANTE;
        for (Nivel nivel : values()) {
            if (xp >= nivel.xpMinimo) {
                atual = nivel;
            }
        }
        return atual;
    }

    @Override
    public String toString() {
        return emblema + " " + name();
    }
}
