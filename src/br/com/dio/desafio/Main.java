package br.com.dio.desafio;

import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Desafio;
import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.dominio.Mentoria;
import br.com.dio.desafio.dominio.Projeto;

import java.time.LocalDate;

/**
 * Classe de entrada que demonstra o domínio do Bootcamp em funcionamento,
 * exercitando os quatro pilares da Orientação a Objetos e as evoluções do
 * projeto: novos tipos de conteúdo, níveis/badges e ranking de devs.
 */
public class Main {

    public static void main(String[] args) {
        // --- Conteúdos (Herança + Polimorfismo) ---
        Curso cursoJava = new Curso();
        cursoJava.setTitulo("Curso de Java");
        cursoJava.setDescricao("Fundamentos da linguagem Java");
        cursoJava.setCargaHoraria(8);

        Curso cursoJs = new Curso();
        cursoJs.setTitulo("Curso de JavaScript");
        cursoJs.setDescricao("Fundamentos de JavaScript");
        cursoJs.setCargaHoraria(4);

        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("Mentoria de Java");
        mentoria.setDescricao("Tira-dúvidas ao vivo sobre Java");
        mentoria.setData(LocalDate.now());

        Desafio desafioColecoes = new Desafio();
        desafioColecoes.setTitulo("Desafio de Collections");
        desafioColecoes.setDescricao("Resolva problemas usando List, Set e Map");
        desafioColecoes.setDificuldade(3);

        Projeto projetoApi = new Projeto();
        projetoApi.setTitulo("Projeto: API REST");
        projetoApi.setDescricao("Construa uma API REST em equipe");
        projetoApi.setTamanhoEquipe(3);

        // --- Bootcamp (Composição + API fluente) ---
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Trilha completa de Java");
        bootcamp.adicionarConteudo(cursoJava, cursoJs, mentoria, desafioColecoes, projetoApi);

        System.out.println("XP total disponível no bootcamp: " + bootcamp.getXpTotalDisponivel());
        System.out.println("=".repeat(60));

        // --- Devs com progressos diferentes ---
        Dev camila = new Dev();
        camila.setNome("Camila");
        camila.inscreverBootcamp(bootcamp);
        camila.progredir();
        camila.progredir();
        System.out.println(camila.resumo());

        Dev joao = new Dev();
        joao.setNome("João");
        joao.inscreverBootcamp(bootcamp);
        joao.concluirTudo();
        System.out.println(joao.resumo());

        Dev alice = new Dev();
        alice.setNome("Alice");
        alice.inscreverBootcamp(bootcamp);
        alice.progredir();
        alice.progredir();
        alice.progredir();
        System.out.println(alice.resumo());

        // --- Ranking e relatório do bootcamp ---
        System.out.println("=".repeat(60));
        System.out.print(bootcamp.relatorio());

        // --- Encapsulamento em ação: validações protegem o estado ---
        System.out.println("=".repeat(60));
        try {
            Curso invalido = new Curso();
            invalido.setCargaHoraria(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Validação impediu estado inválido: " + e.getMessage());
        }
    }
}
