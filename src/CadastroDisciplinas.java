import java.util.*;
import java.util.stream.Collector;
public class CadastroDisciplinas {
    private Set<Disciplina> disciplinas;

    public void cadastrarDisciplinas() {
        this.disciplinas = new LinkedHashSet<>();
    }

    public void adicionarDisciplinas(Disciplina d) {
        disciplinas.add(d);
    }

    public boolean verificarDisciplina(String codigo) {
        return disciplinas.stream().anyMatch(d -> d.getCodgo().equals(codigo));
    }

    public List<Disciplina> obterTodasDisciplinas() {
        return new ArrayList<>(disciplinas);
    }

    public Disciplina buscaPorCodigo(String codigo) {
        return disciplinas.stream()
                .filter(d -> d.getCodgo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public int getSize() {
        return disciplinas.size();
    }
}