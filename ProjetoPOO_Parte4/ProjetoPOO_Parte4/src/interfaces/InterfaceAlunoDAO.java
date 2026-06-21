package interfaces;

import models.Aluno;

import java.util.List;

public interface InterfaceAlunoDAO {
    public boolean cadastrarAluno(Aluno aluno);
    public List<Aluno> listarAlunos();
}
