package Controller;

import models.Aluno;
import models.Professor;

import java.util.ArrayList;

public class ProfessorController {
    ArrayList<Professor> listaProfessor = new ArrayList<Professor>();

    public void cadastrarProfessor(Professor professor){
        listaProfessor.add(professor);
        System.out.println("Professor cadastrado com sucesso!!");
    }

    public void listarProfessor(){

        if(listaProfessor.isEmpty()){
            System.out.println("Não há professores cadastrados no sistema");
            return;
        }
        System.out.println("---- Professores cadastrados ----");
        for(Professor professor : listaProfessor){


            professor.listarPessoa();

            System.out.println("Formação " +professor.getFormacao());
            System.out.println("Especialização: " +professor.getEspecializacao());
            System.out.println("Valor da hora aula: " +professor.getValorHora());
            System.out.println("Total de aulas semanais: " +professor.getHorasSemanais());
            System.out.println("Salário semanal: R$" +professor.getValorHora()*professor.getHorasSemanais());

            System.out.println("------------------------------------------");

        }
    }
}
