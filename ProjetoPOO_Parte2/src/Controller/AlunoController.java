package Controller;

import models.Aluno;
import models.Pessoa;

import java.util.ArrayList;

public class AlunoController {
    public ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();

    public void cadastrarAluno(Aluno aluno){
        listaAluno.add(aluno);
        System.out.println("Aluno cadastrado com sucesso!!");
    }

    public void listarAluno(){

        if(listaAluno.isEmpty()){
            System.out.println("Não há alunos cadastrados no sistema");
            return;
        }
        System.out.println("----Alunos Cadastrados----");
        for(Aluno aluno : listaAluno){

            aluno.listarPessoa();

            System.out.println("Registro Acadêmico: " +aluno.getRa());
            System.out.println("Curso: " +aluno.getCurso().getNomeCurso());
            System.out.println("Status do curso: " +aluno.getStatus());

            System.out.println("------------------------------------------");

        }
    }
}
