package models;

public class Aluno extends Pessoa {

    private int ra;
    private String status;

    public Aluno(String cpf, String nome, int idade, String dataNascimento, String telefone, boolean estadoCivil, String estado, String cidade, String endereco, int ra, String status) {
        super(cpf, nome, idade, dataNascimento, telefone, estadoCivil, estado, cidade, endereco);
        this.ra = ra;
        this.status = status;
    }

    //------setters------//

    public void setRa(int ra) {
        this.ra = ra;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //------getters------//

    public int getRa() {
        return ra;
    }

    public String getStatus() {
        return status;
    }
}
