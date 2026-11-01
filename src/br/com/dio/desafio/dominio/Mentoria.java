package br.com.dio.desafio.dominio;

import java.time.LocalDate;

/**
 * Conteúdo do tipo Mentoria. Herda de {@link Conteudo} e concede um XP fixo
 * acrescido de um bônus, demonstrando uma regra distinta da do {@link Curso}.
 */
public class Mentoria extends Conteudo {

    private LocalDate data;

    @Override
    public double calcularXp() {
        return XP_PADRAO + 20d;
    }

    @Override
    public String tipo() {
        return "Mentoria";
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Mentoria{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", data=" + data +
                '}';
    }
}
