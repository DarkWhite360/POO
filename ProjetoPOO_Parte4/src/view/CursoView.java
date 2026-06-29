package view;

import Controller.CursoController;
import models.Curso;

import java.util.InputMismatchException;
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

    public void excluirCurso(){
        System.out.println("----- Exclusão de Curso -----");

        System.out.println("Informe o código do curso que deseja excluir: ");
        int cdCurso = sc.nextInt();

        if (cursoController.excluirCurso(cdCurso)){
            System.out.println("Curso excluído com sucesso!");
        } else {
            System.out.println("Curso não encontrado ou não foi possível excluir o curso");
        }

    }

    public void buscarCursoPorCodigo(){
        System.out.println("----- Busca de Curso -----");

        System.out.println("Informe o Codigo do curso: ");
        int cdCurso = sc.nextInt();
        try {
            Curso c = cursoController.buscarCursoPorCodigo(cdCurso);
            if (c != null) {

                System.out.println("Código do Curso: " + c.getCdCurso());
                System.out.println("Nome do curso: " + c.getNomeCurso());

            } else {
                System.out.println("Não existe curso cadastrado no sistema com o código " + cdCurso + ".");
            }
        } catch (InputMismatchException e){
            System.out.println("Código informado deve ser numérico ,tente novamente");
        }

    }

    public void editarCurso(){
        System.out.println("----- Editar Curso -----");

        try {
            System.out.println("Informe o código do curso: ");
            int cdCurso = sc.nextInt();

            Curso c = cursoController.buscarCursoPorCodigo(cdCurso);

            if(c != null){
                System.out.println("Código do Curso: " + c.getCdCurso());
                System.out.println("Nome do curso: " + c.getNomeCurso());
                System.out.println("----------------------------------");

                String resp;
                do {
                    System.out.println("Deseja atualizar o Curso acima? s - Sim | n - Não");
                    resp = sc.nextLine().toUpperCase();

                    if (resp.equals("S")){
                        System.out.println("----- Atualize as informações do curso -----");

                        System.out.println("Nome do Curso: ");
                        c.setNomeCurso(sc.nextLine());

                        if(cursoController.editarCurso(c)){
                            System.out.println("Curso atualiZado com sucesso");
                        } else {
                            System.out.println("Erro ao atualizar o curso");
                        }
                    } else if(resp.equals("N"));
                } while (!resp.equals("S") && !resp.equals("N"));
            }

        } catch (InputMismatchException e){
            System.out.println("Código informado deve ser numérico ,tente novamente");
        }
    }
}
