package models;

public class Pessoa {

    private String cpf;
    private String nome;
    private int idade;
    private String dataNascimento;
    private String telefone;
    private boolean estadoCivil;
    private String estado;
    private String cidade;
    private String endereco;

    public Pessoa(String cpf, String nome, int idade, String dataNascimento, String telefone, boolean estadoCivil, String estado, String cidade, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.estadoCivil = estadoCivil;
        this.estado = estado;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    //------Setters-------//

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEstadoCivil(boolean estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    //-----getters-----//

    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public boolean getEstadoCivil() {
        return this.estadoCivil;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getEndereco() {
        return this.endereco;
    }
}
