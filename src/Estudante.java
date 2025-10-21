import java.util.Objects;

public class Estudante {
    private int id;
    private String nome;

    public Estudante(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estudante estudante = (Estudante) obj;
        return id == estudante.id;
    }//Faz ele enchergar estudantes com id igual como o mesmo estudante

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }//Chave é o ID

    @Override
    public String toString() {
        return nome + "(" + id + ")";
    }
}

