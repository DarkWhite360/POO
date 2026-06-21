package interfaces;

import models.Curso;

import java.util.List;

public interface InterfaceCursoDAO {

    public boolean cadastrarCurso(Curso curso);

    public List<Curso> listarCursos();

}
