import java.util.Objects;

public class Disciplina {
    private String codigo;
    private String nomeclatura;

    public Disciplina(String codgo, String nomeclatura) {
        this.codigo = codgo;
        this.nomeclatura = nomeclatura;
    }

    public String getCodgo() {
        return codigo;
    }

    public void setCodgo(String codgo) {
        this.codigo = codgo;
    }

    public String getNomeclatura() {
        return nomeclatura;
    }

    public void setNomeclatura(String nomeclatura) {
        this.nomeclatura = nomeclatura;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disciplina disciplina = (Disciplina) obj;
        return codigo == disciplina.codigo;
    }//Faz ele enchergar disciplinas com codgo igual como a mesma disciplina

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }//Chave é o codgo

    @Override
    public String toString() {
        return nomeclatura + "(" + codigo + ")";
    }
}

