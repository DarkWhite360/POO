package models;

import java.time.LocalDate;

abstract public class Pessoa {

    private int id;
    private String cpf;
    private String nome;
    private int idade;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String estadoCivil;
    private String estado;
    private String cidade;
    private String endereco;

    public Pessoa() {
    }

    public Pessoa(String cpf, String nome, int idade, LocalDate dataNascimento, String telefone, String email, String estadoCivil, String estado, String cidade, String endereco) {
        this.setCpf(cpf) ;
        this.setNome(nome) ;
        this.setIdade(idade) ;
        this.setDataNascimento(dataNascimento);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEstadoCivil(estadoCivil) ;
        this.setEstado(estado);
        this.setCidade(cidade);
        this.setEndereco(endereco);
    }

    abstract String listarPessoa();

    public void setId(int id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstadoCivil(String estadoCivil) {
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

    //Getters


    public int getId() {
        return id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEstadoCivil() {
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
