package interfaces;

import models.Professor;

import java.util.List;

public interface InterfaceProfessorDAO {

    public boolean cadastrarProfessor(Professor professor);
    public List<Professor> listarProfessores();
    public boolean excluirProfessor (String cpf);
    public Professor buscarProfessorPorCPF (String cpf);
    public boolean editarProfessor (Professor professor);
}
