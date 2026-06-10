package Controller;

import models.Curso;

import java.util.ArrayList;
import java.util.Scanner;

public class CursoController {
    Scanner sc = new Scanner(System.in);

    public ArrayList<Curso> listaCurso = new ArrayList<Curso>();

    public void cadastrarCurso(Curso curso){
        listaCurso.add(curso);
        System.out.println("Curso cadastrado com sucesso!!");
    }

    public ArrayList<Curso> getListaCurso(){
        return listaCurso;
    }

    public void listarCurso(){

        if (listaCurso.isEmpty()){
            System.out.println("Não há cursos cadastrados!!");
            return;
        }

        System.out.println("Lista de Cursos Cadastrados");
        for(Curso curso : listaCurso){
            System.out.println("Código: " +curso.getCdCurso()+ " | Nome: " + curso.getNomeCurso());
        }
    }
}
