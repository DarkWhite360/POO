package models;

import java.util.ArrayList;

public class Curso {
    private int cdCurso;
    private String nomeCurso;
    private ArrayList<Aluno> alunos;

    public Curso(int cdCurso, String nomeCurso) {
        this.setCdCurso(cdCurso) ;
        this.setNomeCurso(nomeCurso) ;
        this.alunos = new ArrayList<>();
    }

    public Curso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Curso() {
    }

    public void adicionarAluno(Aluno aluno){
        alunos.add(aluno);
    }

    //setters

    public void setCdCurso(int cdCurso) {
        this.cdCurso = cdCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    //getters

    public int getCdCurso() {
        return cdCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }
}
