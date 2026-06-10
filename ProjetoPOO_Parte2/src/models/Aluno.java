package models;

public class Aluno extends Pessoa{
    private int ra;
    private String status;
    private Curso curso;

    public Aluno(String cpf, String nome, int idade, String dataNascimento, String telefone, String email, boolean estadoCivil, String estado, String cidade, String endereco, int ra, Curso curso,String status ){
        super(cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco);
        this.ra = ra;
        this.curso = curso;
        this.status = status;
    }

    //setters

    public void setRa(int ra) {
        this.ra = ra;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    //getters

    public int getRa() {
        return ra;
    }

    public String getStatus() {
        return status;
    }

    public Curso getCurso() {
        return curso;
    }
}
