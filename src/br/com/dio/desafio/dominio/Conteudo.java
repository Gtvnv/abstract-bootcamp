package br.com.dio.desafio.dominio;

/**
 * Classe abstrata que representa um conteúdo de aprendizado.
 *
 * <p>Aplica o pilar da <b>Abstração</b>: define o que todo conteúdo tem em
 * comum (título, descrição e uma forma de calcular XP) sem se comprometer com
 * uma implementação concreta. Serve de base para {@link Curso}, {@link Mentoria},
 * {@link Desafio} e {@link Projeto}, habilitando <b>Herança</b> e
 * <b>Polimorfismo</b>.</p>
 */
public abstract class Conteudo {

    protected static final double XP_PADRAO = 10d;

    private String titulo;
    private String descricao;

    /**
     * Calcula a experiência (XP) que o conteúdo concede ao ser concluído.
     * Cada subclasse define sua própria regra (Polimorfismo).
     *
     * @return a quantidade de XP do conteúdo
     */
    public abstract double calcularXp();

    /**
     * Rótulo curto do tipo de conteúdo, usado em relatórios e resumos.
     * Subclasses sobrescrevem para identificar-se (Polimorfismo).
     */
    public String tipo() {
        return getClass().getSimpleName();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título do conteúdo é obrigatório.");
        }
        this.titulo = titulo.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do conteúdo é obrigatória.");
        }
        this.descricao = descricao.trim();
    }
}
