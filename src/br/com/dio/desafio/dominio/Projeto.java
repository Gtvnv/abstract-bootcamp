package br.com.dio.desafio.dominio;

/**
 * Conteúdo do tipo Projeto prático. Herda de {@link Conteudo} e recompensa com
 * um XP base alto mais um bônus por entregar em equipe, reforçando o
 * Polimorfismo com uma regra de XP distinta das demais.
 */
public class Projeto extends Conteudo {

    /** Número de integrantes da equipe que entregou o projeto. */
    private int tamanhoEquipe = 1;

    @Override
    public double calcularXp() {
        double bonusEquipe = tamanhoEquipe > 1 ? XP_PADRAO * (tamanhoEquipe - 1) : 0d;
        return XP_PADRAO * 5 + bonusEquipe;
    }

    @Override
    public String tipo() {
        return "Projeto";
    }

    public int getTamanhoEquipe() {
        return tamanhoEquipe;
    }

    public void setTamanhoEquipe(int tamanhoEquipe) {
        if (tamanhoEquipe < 1) {
            throw new IllegalArgumentException("A equipe deve ter ao menos 1 integrante.");
        }
        this.tamanhoEquipe = tamanhoEquipe;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", tamanhoEquipe=" + tamanhoEquipe +
                '}';
    }
}
