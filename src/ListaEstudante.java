import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListaEstudante {
    private List<Estudante> estudantes;

    public ListaEstudante() {
        this.estudantes = new ArrayList<>();
    }

    public void adicionarEstudante(Estudante e) {
        estudantes.add(e);
    }

    public void removerEstudantePorId(int id) {
        estudantes.removeIf(estudante -> estudante.equals(id));
    }

    public Estudante obterEstudantePorIndice(int indice) {
        if (indice < estudantes.size() && indice >= 0) {
            return estudantes.get(indice);
            //Só busca se o indice for menor q o tamanho da lista ou for um numero positivo
        }
        return null;
    }

    public List<Estudante> obterEstudantePorNome(String subnome) {
        return estudantes.stream()
                .filter(e -> e.getNome().toLowerCase().contains(subnome.toLowerCase()))
                .collect(Collectors.toList());//linha de código gigantestca, é bom quebrar!
    }

    public Estudante obterEstudantePorId(int id) {
        return estudantes.stream()
                .filter(e -> e.equals(id))
                .findFirst()
                .orElse(null);
    }

    public void ordenarPorNome() {
        estudantes.sort(Comparator.comparing(Estudante::getNome));
    }

    public int getSize() {
        return estudantes.size();
    }

    public List<Estudante> obterTodosEstudantes() {
        return new ArrayList<>(estudantes);
    }
}

