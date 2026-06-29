package models;

import java.time.LocalDate;

public class Professor extends Pessoa {


    private String formacao;
    private String especializacao;
    private double valorHora;
    private int horasSemanais;

    public Professor(String cpf, String nome, int idade, LocalDate dataNascimento, String telefone, String email, String estadoCivil, String estado, String cidade, String endereco, String formacao, String especializacao, double valorHora, int horasSemanais) {
        super(cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco);
        this.setFormacao(formacao);
        this.setEspecializacao(especializacao);
        this.setValorHora(valorHora);
        this.setHorasSemanais(horasSemanais);
    }

    public Professor() {
        super();
    }

    @Override
    public String listarPessoa() {
        String dados =
            "CPF: " + this.getCpf() + "\n" +
            "Nome: " + this.getNome() + "\n" +
            "Idade: " +this.getIdade() + "\n" +
            "Data de nascimento: " +this.getDataNascimento() + "\n" +
            "Telefone: " + this.getTelefone() + "\n" +
            "Email: " + this.getEmail() +
            "Estado Cívil: " + this.getEstadoCivil() + "\n" +
            "Estado: " + this.getEstado() + "\n" +
            "Cidade: " + this.getCidade() + "\n" +
            "Endereço: " + this.getEndereco() + "\n" +
            "Formação: " + this.getFormacao() + "\n" +
            "Especialização: " + this.getEspecializacao() + "\n" +
            "Valor da Hora aula: "+this.getValorHora()+ "\n" +
            "Total de horas semanais: " + this.getHorasSemanais();
        return dados;
    }
    //setters

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public void setHorasSemanais(int horasSemanais) {
        this.horasSemanais = horasSemanais;
    }


    //getters

    public String getFormacao() {
        return formacao;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public double getValorHora() {
        return valorHora;
    }

    public int getHorasSemanais() {
        return horasSemanais;
    }
}
