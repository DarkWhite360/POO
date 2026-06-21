package Controller;

import dao.ProfessorDAO;

import models.Professor;


import java.util.List;

public class ProfessorController {

    public static ProfessorDAO controllerProfessor;

    public ProfessorController(){ this.controllerProfessor = new ProfessorDAO();}

    public static boolean cadastrarProfessor(Professor professor){
        return controllerProfessor.cadastrarProfessor(professor);
    }

    public List<Professor> listarProfessores(){
        return controllerProfessor.listarProfessores();
    }
}
