package Controller;

import dao.CursoDAO;
import models.Curso;


import java.util.List;


public class CursoController {

    public static CursoDAO controllerCurso;

    public CursoController(){
        this.controllerCurso = new CursoDAO();
    }

    public static boolean cadastrarCurso(Curso curso){
        return controllerCurso.cadastrarCurso(curso);
    }

    public List<Curso> listarCursos(){
        return controllerCurso.listarCursos();
    }
}

