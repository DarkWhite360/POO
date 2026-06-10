package models;

public class Aluno extends Pessoa{
    private int ra;
    private String status;
    private Curso curso;

    public Aluno(String cpf, String nome, int idade, String dataNascimento, String telefone, String email, boolean estadoCivil, String estado, String cidade, String endereco, int ra, Curso curso,String status ){
        super(cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco);
        this.setRa(ra);
        this.setCurso(curso);
        this.setStatus(status);
    }

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
            "Total de horas semanais: " + curso.getNomeCurso();
        return dados;
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
