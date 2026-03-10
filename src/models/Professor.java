package models;

public class Professor extends Pessoa {


    private String formacao;
    private String especializacao;
    private double valorHora;
    private int horasSemanais;

    public Professor(String cpf, String nome, int idade, String dataNascimento, String telefone, boolean estadoCivil, String estado, String cidade, String endereco, String formacao, String especializacao, double valorHora, int horasSemanais) {
        super(cpf, nome, idade, dataNascimento, telefone, estadoCivil, estado, cidade, endereco);
        this.formacao = formacao;
        this.especializacao = especializacao;
        this.valorHora = valorHora;
        this.horasSemanais = horasSemanais;
    }

    public double calcularSalario(){
        return valorHora*horasSemanais;
    }

    //----Setters----//
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

    //----Getters----//
    public String getFormacao() {
        return this.formacao;
    }

    public String getEspecializacao() {
        return this.especializacao;
    }

    public double getValorHora() {
        return this.valorHora;
    }

    public int getHorasSemanais() {
        return this.horasSemanais;
    }


}
