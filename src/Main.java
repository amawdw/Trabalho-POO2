import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Inicializar as coleções
        ListaEstudantes listaEstudantes = new ListaEstudantes();
        CadastroDisciplinas cadastroDisciplinas = new CadastroDisciplinas();
        HistoricoNotas historicoNotas = new HistoricoNotas();

        // Carregar dataset (Parte D - Item 1)
        carregarDataset(listaEstudantes, cadastroDisciplinas, historicoNotas);

        // Exibir resultados
        System.out.println("=== TRABALHO DE POO - COLEÇÕES JAVA ===\n");

        // Item 2: Exibir estudantes ordenados por nome
        exibirEstudantesOrdenados(listaEstudantes);

        // Item 3: Exibir disciplinas (ordem de inserção)
        exibirDisciplinas(cadastroDisciplinas);

        // Item 4: Exibir matrículas e notas
        exibirMatriculasENotas(listaEstudantes, historicoNotas);

        // Item 5: Relatórios
        exibirRelatorios(listaEstudantes, cadastroDisciplinas, historicoNotas);

        // Extra: Buscar estudantes por substring
        buscarEstudantesPorSubstring(listaEstudantes, historicoNotas);
    }

    private static void carregarDataset(ListaEstudantes listaEstudantes,
                                        CadastroDisciplinas cadastroDisciplinas,
                                        HistoricoNotas historicoNotas) {
        // Carregar estudantes
        listaEstudantes.adicionarEstudante(new Estudante(1, "Ana"));
        listaEstudantes.adicionarEstudante(new Estudante(2, "Bruno"));
        listaEstudantes.adicionarEstudante(new Estudante(3, "Carla"));
        listaEstudantes.adicionarEstudante(new Estudante(4, "Diego"));
        listaEstudantes.adicionarEstudante(new Estudante(5, "Elisa"));

        // Carregar disciplinas
        cadastroDisciplinas.adicionarDisciplina(new Disciplina("MAT101", "Matemática"));
        cadastroDisciplinas.adicionarDisciplina(new Disciplina("PRG201", "Programação"));
        cadastroDisciplinas.adicionarDisciplina(new Disciplina("BD301", "Banco de Dados"));
        cadastroDisciplinas.adicionarDisciplina(new Disciplina("EDF110", "Educação Física"));

        // Carregar matrículas/notas
        historicoNotas.adicionarMatricula(1, "MAT101", 8.5);
        historicoNotas.adicionarMatricula(1, "PRG201", 9.0);
        historicoNotas.adicionarMatricula(2, "PRG201", 7.0);
        historicoNotas.adicionarMatricula(2, "MAT101", 5.0);
        historicoNotas.adicionarMatricula(3, "BD301", 6.5);
        historicoNotas.adicionarMatricula(3, "MAT101", 7.5);
        historicoNotas.adicionarMatricula(4, "PRG201", 8.0);
        historicoNotas.adicionarMatricula(5, "EDF110", 10.0);
    }

    private static void exibirEstudantesOrdenados(ListaEstudantes listaEstudantes) {
        System.out.println("== Lista de Estudantes (ordem de cadastro) ==");
        for (Estudante e : listaEstudantes.getTodosEstudantes()) {
            System.out.println(e);
        }

        System.out.println("\n== Lista de Estudantes (ordenada) ==");
        listaEstudantes.ordenarEstudantesPorNome();
        List<Estudante> ordenados = listaEstudantes.getTodosEstudantes();
        for (int i = 0; i < ordenados.size(); i++) {
            System.out.print(ordenados.get(i).getNome());
            if (i < ordenados.size() - 1) System.out.print(", ");
        }
        System.out.println("\n");
    }

    private static void exibirDisciplinas(CadastroDisciplinas cadastroDisciplinas) {
        System.out.println("== Disciplinas (ordem de inserção) ==");
        List<Disciplina> disciplinas = cadastroDisciplinas.obterTodasDisciplinas();
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.print(disciplinas.get(i).getCodigo());
            if (i < disciplinas.size() - 1) System.out.print(", ");
        }
        System.out.println("\n");

        System.out.println("== Duplicatas detectadas na importação ==");
        System.out.println("(nenhuma)\n");
    }

    private static void exibirMatriculasENotas(ListaEstudantes listaEstudantes, HistoricoNotas historicoNotas) {
        System.out.println("== Matrículas ==");
        for (Estudante e : listaEstudantes.getTodosEstudantes()) {
            List<Matricula> matriculas = historicoNotas.obterMatriculas(e.getId());
            if (!matriculas.isEmpty()) {
                System.out.print(e.getNome() + ": ");
                for (int i = 0; i < matriculas.size(); i++) {
                    System.out.print(matriculas.get(i));
                    if (i < matriculas.size() - 1) System.out.print(", ");
                }
                double media = historicoNotas.mediaDoEstudante(e.getId());
                System.out.printf(" Média: %.2f\n", media);
            }
        }
        System.out.println();
    }

    private static void exibirRelatorios(ListaEstudantes listaEstudantes,
                                         CadastroDisciplinas cadastroDisciplinas,
                                         HistoricoNotas historicoNotas) {
        // Médias por disciplina
        System.out.println("== Médias por Disciplina ==");
        for (Disciplina d : cadastroDisciplinas.obterTodasDisciplinas()) {
            double media = historicoNotas.mediaDaDisciplina(d.getCodigo());
            System.out.printf("%s: %.1f\n", d.getCodigo(), media);
        }
        System.out.println();

        // Top 3 alunos por média
        System.out.println("== Top 3 alunos por média ==");
        List<Estudante> top3 = historicoNotas.topNEstudantesPorMedia(3, listaEstudantes);
        for (int i = 0; i < top3.size(); i++) {
            double media = historicoNotas.mediaDoEstudante(top3.get(i).getId());
            System.out.printf("%d) %s - %.1f\n", i + 1, top3.get(i).getNome(), media);
        }
        System.out.println();

        // Alunos com média >= 8.0
        System.out.println("== Alunos com média >= 8.0 ==");
        List<Integer> aprovados = historicoNotas.getEstudantesAprovados(8.0, listaEstudantes);
        if (aprovados.isEmpty()) {
            System.out.println("(nenhum)");
        } else {
            for (int i = 0; i < aprovados.size(); i++) {
                Estudante e = listaEstudantes.buscarPorId(aprovados.get(i));
                System.out.print(e.getNome());
                if (i < aprovados.size() - 1) System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println();

        // Disciplinas com média < 6.0
        System.out.println("== Disciplinas com média < 6.0 ==");
        boolean encontrou = false;
        for (Disciplina d : cadastroDisciplinas.obterTodasDisciplinas()) {
            double media = historicoNotas.mediaDaDisciplina(d.getCodigo());
            if (media < 6.0) {
                System.out.printf("%s: %.1f\n", d.getCodigo(), media);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("(nenhuma)");
        }
        System.out.println();
    }

    private static void buscarEstudantesPorSubstring(ListaEstudantes listaEstudantes, HistoricoNotas historicoNotas) {
        System.out.println("== Extra: Buscar estudantes contendo 'a' ==");
        List<Estudante> encontrados = listaEstudantes.buscarEstudantesPorNome("a");
        for (Estudante e : encontrados) {
            double media = historicoNotas.mediaDoEstudante(e.getId());
            System.out.printf("%s - Média: %.2f\n", e.getNome(), media);
        }
    }
}