package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Representa um desenvolvedor que se inscreve em um {@link Bootcamp}, progride
 * nos conteúdos e acumula XP.
 *
 * <p>Ao se inscrever, o dev recebe todos os conteúdos do bootcamp como
 * "inscritos". Conforme progride, cada conteúdo migra para "concluídos" e o
 * XP correspondente passa a contar no total.</p>
 */
public class Dev {

    private String nome;
    private final Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private final Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    /**
     * Inscreve o dev no bootcamp, copiando seus conteúdos para a lista de
     * inscritos e registrando o dev no bootcamp.
     */
    public void inscreverBootcamp(Bootcamp bootcamp) {
        if (bootcamp == null) {
            throw new IllegalArgumentException("O bootcamp é obrigatório para a inscrição.");
        }
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    /**
     * Conclui o próximo conteúdo inscrito, movendo-o para os concluídos.
     *
     * @return {@code true} se avançou em algum conteúdo; {@code false} se não
     *         havia conteúdo pendente.
     */
    public boolean progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if (conteudo.isEmpty()) {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
            return false;
        }
        this.conteudosConcluidos.add(conteudo.get());
        this.conteudosInscritos.remove(conteudo.get());
        return true;
    }

    /**
     * Conclui, de uma vez, todos os conteúdos ainda pendentes.
     *
     * @return a quantidade de conteúdos concluídos nesta chamada.
     */
    public int concluirTudo() {
        int concluidos = conteudosInscritos.size();
        this.conteudosConcluidos.addAll(this.conteudosInscritos);
        this.conteudosInscritos.clear();
        return concluidos;
    }

    /**
     * Soma o XP de todos os conteúdos já concluídos (Polimorfismo: cada
     * conteúdo calcula seu XP à sua maneira).
     */
    public double calcularTotalXp() {
        return this.conteudosConcluidos
                .stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();
    }

    /**
     * Nível atual do dev, derivado do XP acumulado.
     */
    public Nivel getNivel() {
        return Nivel.doXp(calcularTotalXp());
    }

    /**
     * Percentual de conclusão do bootcamp (0 a 100) com base na razão entre
     * conteúdos concluídos e o total (inscritos + concluídos).
     */
    public double getProgressoPercentual() {
        int total = conteudosInscritos.size() + conteudosConcluidos.size();
        if (total == 0) {
            return 0d;
        }
        return (conteudosConcluidos.size() * 100d) / total;
    }

    /**
     * Resumo legível do estado atual do dev, útil para exibir em relatórios.
     */
    public String resumo() {
        return String.format(
                "%s | Nível: %s | XP: %.1f | Progresso: %.0f%% (%d/%d concluídos)",
                nome,
                getNivel(),
                calcularTotalXp(),
                getProgressoPercentual(),
                conteudosConcluidos.size(),
                conteudosInscritos.size() + conteudosConcluidos.size());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do dev é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    @Override
    public String toString() {
        return "Dev{" +
                "nome='" + nome + '\'' +
                ", conteudosInscritos=" + conteudosInscritos.size() +
                ", conteudosConcluidos=" + conteudosConcluidos.size() +
                ", totalXp=" + calcularTotalXp() +
                ", nivel=" + getNivel().name() +
                '}';
    }
}
