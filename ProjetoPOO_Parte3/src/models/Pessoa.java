package models;

abstract public class Pessoa {

    private String cpf;
    private String nome;
    private int idade;
    private String dataNascimento;
    private String telefone;
    private String email;
    private boolean estadoCivil;
    private String estado;
    private String cidade;
    private String endereco;

    public Pessoa(String cpf, String nome, int idade, String dataNascimento, String telefone, String email, boolean estadoCivil, String estado, String cidade, String endereco) {
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
  /*
    public void listarPessoa(){
        System.out.println("CPF: " +this.cpf);
        System.out.println("Nome: " +this.nome);
        System.out.println("Idade: " +this.idade);
        System.out.println("Data de Nascimento: " +this.dataNascimento);
        System.out.println("Telefone: " +this.telefone);
        System.out.println("Email: " +this.email);
        System.out.println("Estado Civil: " +this.estadoCivil);
        System.out.println("Unidade Federativa: " +this.estado);
        System.out.println("Cidade: " +this.cidade);
        System.out.println("Endereco: " +this.endereco);

    }
   */
//Setters

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

    public void setEmail(String email) {
        this.email = email;
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

    //Getters


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

    public String getEmail() {
        return email;
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
