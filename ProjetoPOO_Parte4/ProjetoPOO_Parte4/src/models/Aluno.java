package models;

import java.time.LocalDate;

public class Aluno extends Pessoa{
    private String ra;
    private String status;
    private Curso curso;

    public Aluno(String cpf, String nome, int idade, LocalDate dataNascimento, String telefone, String email, String estadoCivil, String estado, String cidade, String endereco, String ra, String status, Curso curso) {
        super(cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco);
        this.setRa(ra);
        this.setCurso(curso);
        this.setStatus(status);
    }

    public Aluno() {super();}

    @Override
    public String listarPessoa() {
        String dados =
            "CPF: " + this.getCpf() + "\n" +
            "Nome: " + this.getNome() + "\n" +
            "Idade: " +this.getIdade() + "\n" +
            "Data de nascimento: " +this.getDataNascimento() + "\n" +
            "Telefone: " + this.getTelefone() + "\n" +
            "Email: " + this.getEmail() + "\n" +
            "Estado Cívil: " + this.getEstadoCivil() + "\n" +
            "Estado: " + this.getEstado() + "\n" +
            "Cidade: " + this.getCidade() + "\n" +
            "Endereço: " + this.getEndereco() + "\n" +
            "RA:: " + this.getRa() + "\n" +
            "Status: "+this.getStatus()+ "\n" +
            "Curso: " + curso.getNomeCurso()+ "\n" +
            "Código do Curso:" +curso.getCdCurso();
        return dados;
    }


    //setters

    public void setRa(String ra) {
        this.ra = ra;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    //getters

    public String getRa() {
        return ra;
    }

    public String getStatus() {
        return status;
    }

    public Curso getCurso() {
        return curso;
    }


}
