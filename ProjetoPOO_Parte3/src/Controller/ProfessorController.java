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


            System.out.println(professor.listarPessoa());

            System.out.println("------------------------------------------");

        }
    }
}
