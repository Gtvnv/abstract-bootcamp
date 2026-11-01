package br.com.dio.desafio.dominio;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Representa um Bootcamp: uma trilha de aprendizado com período definido que
 * agrega diversos {@link Conteudo conteúdos} e os {@link Dev devs} inscritos.
 *
 * <p>Demonstra <b>Encapsulamento</b> (campos privados com acesso controlado)
 * e <b>Composição</b> entre as classes de domínio.</p>
 */
public class Bootcamp {

    private String nome;
    private String descricao;
    private final LocalDate dataInicial = LocalDate.now();
    private final LocalDate dataFinal = dataInicial.plusDays(45);
    private final Set<Dev> devsInscritos = new LinkedHashSet<>();
    private final Set<Conteudo> conteudos = new LinkedHashSet<>();

    /**
     * Adiciona um ou mais conteúdos ao bootcamp. Retorna o próprio bootcamp
     * para permitir encadeamento fluente.
     */
    public Bootcamp adicionarConteudo(Conteudo... novosConteudos) {
        for (Conteudo conteudo : novosConteudos) {
            if (conteudo == null) {
                throw new IllegalArgumentException("Não é possível adicionar um conteúdo nulo.");
            }
            this.conteudos.add(conteudo);
        }
        return this;
    }

    /**
     * XP total que o bootcamp oferece se um dev concluir todos os conteúdos.
     */
    public double getXpTotalDisponivel() {
        return conteudos.stream().mapToDouble(Conteudo::calcularXp).sum();
    }

    /**
     * Devs inscritos ordenados do maior para o menor XP acumulado (ranking).
     */
    public List<Dev> ranking() {
        return devsInscritos.stream()
                .sorted(Comparator.comparingDouble(Dev::calcularTotalXp).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Monta um relatório textual do bootcamp com o ranking de devs.
     */
    public String relatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bootcamp: ").append(nome).append('\n');
        sb.append("Período: ").append(dataInicial).append(" a ").append(dataFinal).append('\n');
        sb.append("Conteúdos: ").append(conteudos.size())
                .append(" | XP disponível: ").append(getXpTotalDisponivel()).append('\n');
        sb.append("Ranking de devs:").append('\n');
        int posicao = 1;
        for (Dev dev : ranking()) {
            sb.append(String.format("  %d. %s%n", posicao++, dev.resumo()));
        }
        return sb.toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do bootcamp é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do bootcamp é obrigatória.");
        }
        this.descricao = descricao.trim();
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public Set<Dev> getDevsInscritos() {
        return devsInscritos;
    }

    public Set<Conteudo> getConteudos() {
        return conteudos;
    }

    @Override
    public String toString() {
        return "Bootcamp{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", devsInscritos=" + devsInscritos.size() +
                ", conteudos=" + conteudos.size() +
                '}';
    }
}
