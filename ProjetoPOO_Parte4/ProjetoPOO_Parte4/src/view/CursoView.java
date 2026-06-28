package view;

import Controller.CursoController;
import models.Curso;

import java.util.Scanner;

public class CursoView {

    protected Scanner sc = new Scanner(System.in);

    private CursoController cursoController = new CursoController();

    public void cadastrarCurso(){
        System.out.println("----- Informe os dados solicitados para realizar o cadastro do curso -----");

        System.out.println("Nome curso: ");
        String nomeCurso = sc.nextLine();

        Curso curso = new Curso(nomeCurso);

        cursoController.cadastrarCurso(curso);
    }

    public void listarCursos(){
        for(Curso c: cursoController.listarCursos()){
            System.out.println("----------------------------------");
            System.out.println("Código Curso: " +c.getCdCurso());
            System.out.println("Nome do Curso: " +c.getNomeCurso());
        }
    }
}
