package Controller;

import dao.AlunoDAO;
import models.Aluno;

import java.util.List;



public class AlunoController {



    public static AlunoDAO controllerAluno;

    public AlunoController() {
        this.controllerAluno = new AlunoDAO();
    }

    public static boolean cadastrarAluno(Aluno aluno){
        return controllerAluno.cadastrarAluno(aluno);
    }


    public List<Aluno> listarAlunos(){
        return controllerAluno.listarAlunos();
    }

    public static boolean excluirAluno(String ra){ return controllerAluno.excluirAluno(ra);}

    public static Aluno buscarAlunoPorRa (String ra){
        return controllerAluno.buscarAlunoPorRa(ra);
    }

    public static boolean editarAluno (Aluno aluno){
        return controllerAluno.editarAluno(aluno);
    }
}
