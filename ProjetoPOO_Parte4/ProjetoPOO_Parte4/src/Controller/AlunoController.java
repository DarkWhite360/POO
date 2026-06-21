package Controller;

import dao.AlunoDAO;
import models.Aluno;
import models.Pessoa;

import java.util.ArrayList;
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
}
