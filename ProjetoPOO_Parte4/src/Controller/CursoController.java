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

    public static boolean excluirCurso(int cdCurso){
        return controllerCurso.excluirCurso(cdCurso);
    }

    public static Curso buscarCursoPorCodigo(int cdCurso){
        return controllerCurso.buscarCursoPorCodigo(cdCurso);
    }

    public static boolean editarCurso(Curso curso){
        return controllerCurso.editarCurso(curso);
    }
}

