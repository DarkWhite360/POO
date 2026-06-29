package interfaces;

import models.Curso;

import java.util.List;

public interface InterfaceCursoDAO {

    public boolean cadastrarCurso(Curso curso);

    public List<Curso> listarCursos();

    public boolean excluirCurso(int cdCurso);
    public Curso buscarCursoPorCodigo (int cdCurso);
    public boolean editarCurso(Curso curso);

}
