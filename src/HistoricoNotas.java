import java.util.*;
import java.util.stream.Collectors;

public class HistoricoNotas {
    // Map: ID do Estudante -> Lista de Matrículas
    private Map<Integer, List<Matricula>> matriculas;

    public HistoricoNotas() {
        this.matriculas = new HashMap<>(); // HashMap para acesso rápido por ID
    }

    // Métodos obrigatórios da Parte C
    public void adicionarMatricula(int idEstudante, String codigoDisciplina, double nota) {
        Matricula novaMatricula = new Matricula(codigoDisciplina, nota);
        matriculas.computeIfAbsent(idEstudante, k -> new ArrayList<>()).add(novaMatricula);
    }

    public List<Matricula> obterMatriculas(int idEstudante) {
        return matriculas.getOrDefault(idEstudante, new ArrayList<>());
    }

    public Optional<Double> obterNota(int idEstudante, String codigoDisciplina) {
        List<Matricula> matriculasEstudante = obterMatriculas(idEstudante);
        return matriculasEstudante.stream()
                .filter(m -> m.getCodigoDisciplina().equals(codigoDisciplina))
                .map(Matricula::getNota)
                .findFirst();
    }

    public boolean removerMatricula(int idEstudante, String codigoDisciplina) {
        List<Matricula> matriculasEstudante = matriculas.get(idEstudante);
        if (matriculasEstudante != null) {
            return matriculasEstudante.removeIf(m ->
                    m.getCodigoDisciplina().equals(codigoDisciplina));
        }
        return false;
    }

    public double mediaDoEstudante(int idEstudante) {
        List<Matricula> matriculasEstudante = obterMatriculas(idEstudante);
        if (matriculasEstudante.isEmpty()) return 0.0;

        double soma = matriculasEstudante.stream()
                .mapToDouble(Matricula::getNota)
                .sum();
        return soma / matriculasEstudante.size();
    }

    public double mediaDaDisciplina(String codigoDisciplina) {
        List<Double> notas = matriculas.values().stream()
                .flatMap(List::stream)
                .filter(m -> m.getCodigoDisciplina().equals(codigoDisciplina))
                .map(Matricula::getNota)
                .collect(Collectors.toList());

        if (notas.isEmpty()) return 0.0;

        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public List<Estudante> topNEstudantesPorMedia(int N, ListaEstudante listaEstudantes) {
        return listaEstudantes.obterTodosEstudantes().stream()
                .filter(e -> !obterMatriculas(e.getId()).isEmpty()) // Apenas estudantes com notas
                .sorted((e1, e2) -> Double.compare(mediaDoEstudante(e2.getId()), mediaDoEstudante(e1.getId())))
                .limit(N)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares
    public Map<Integer, List<Matricula>> getTodasMatriculas() {
        return new HashMap<>(matriculas);
    }

    public List<Integer> getEstudantesAprovados(double mediaMinima, ListaEstudante listaEstudantes) {
        return listaEstudantes.obterTodosEstudantes().stream()
                .filter(e -> mediaDoEstudante(e.getId()) >= mediaMinima)
                .map(Estudante::getId)
                .collect(Collectors.toList());
    }
}