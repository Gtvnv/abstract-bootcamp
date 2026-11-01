package br.com.dio.desafio.dominio;

/**
 * Conteúdo do tipo Desafio de código. Herda de {@link Conteudo} e concede XP
 * proporcional à dificuldade, demonstrando mais uma regra polimórfica de
 * {@code calcularXp()}.
 */
public class Desafio extends Conteudo {

    /** Dificuldade do desafio, de 1 (fácil) a 5 (muito difícil). */
    private int dificuldade;

    @Override
    public double calcularXp() {
        return XP_PADRAO * 1.5 * dificuldade;
    }

    @Override
    public String tipo() {
        return "Desafio";
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        if (dificuldade < 1 || dificuldade > 5) {
            throw new IllegalArgumentException("A dificuldade deve estar entre 1 e 5.");
        }
        this.dificuldade = dificuldade;
    }

    @Override
    public String toString() {
        return "Desafio{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", dificuldade=" + dificuldade +
                '}';
    }
}
