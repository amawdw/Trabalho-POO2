import java.util.Objects;

public class Matricula {
    private String codigoDisciplina;
    private double nota;

    public Matricula(String codigoDisciplina, double nota) {
        this.codigoDisciplina = codigoDisciplina;
        this.nota = nota;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public boolean aaprovado() {
        return nota<=6.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matricula matricula = (Matricula) obj;
        return codigoDisciplina == matricula.codigoDisciplina;
    }//Faz ele enchergar matriculas com codgo da disciplina igual como a mesma matricula

    @Override
    public int hashCode() {
        return Objects.hash(codigoDisciplina);
    }//Chave é o codigo da disciplina

    @Override
    public String toString(){
        return codigoDisciplina + "(" + nota + ")";
    }
}

