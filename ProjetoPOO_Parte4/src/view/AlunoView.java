package view;

import Controller.AlunoController;
import Controller.CursoController;
import models.Aluno;
import models.Curso;

import java.util.InputMismatchException;
import java.util.List;


public class AlunoView extends PessoaView {

    private CursoController cursoController = new CursoController();
    private AlunoController alunoController = new AlunoController();

    public void cadastrarAluno() {

        //verificar se há cursos cadastrados no sistema
        List<Curso> listaCurso = cursoController.listarCursos();
        if (listaCurso.isEmpty()) {
            System.out.println("Nenhum curso cadastrado no sistema!");
            return;
        }
        System.out.println("|-----Informe os dados abaixo para realizar o cadastro do aluno-----|");

        //Dados comuns a qualquer cadastro
        lerDadosPessoa();

        //Dados exclusivos do aluno

        System.out.println("----- Dados de Registro Acadêmico -----");

        System.out.println("Número de Registro Acadêmico: ");
        String ra = sc.nextLine();

        //Escolher o curso ----------------------------------------------------------------------
        System.out.println("Cursos cadastrados no Sistema: ");

        for (Curso curso : listaCurso) {
            System.out.println("Código: " + curso.getCdCurso() + " - " + curso.getNomeCurso());
        }

        Curso cursoSelecionado = null;

        System.out.println("Digite o código do curso: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        for (Curso curso : listaCurso) {
            if (curso.getCdCurso() == codigo) {
                cursoSelecionado = curso;
                break;
            }
        }
        if (cursoSelecionado == null) {
            System.out.println("Curso inexistente.");
            return;
        }

        //------------------------------------------------------------------------------------

        int defStatus;
        String status = "";
        do {
            System.out.println("Status atual do aluno (1 - cursando || 2 - concluído || 3 - trancado): ");
            defStatus = sc.nextInt();
            if (defStatus == 1) {
                status = "Cursando";
            } else if (defStatus == 2) {
                status = "Concluido";
            } else if (defStatus == 3) {
                status = "Trancado";
            } else {
                System.out.println("Opção inválida, digite novamente.");
            }
        } while (defStatus != 1 && defStatus != 2 && defStatus != 3);
        sc.nextLine();

        Aluno aluno = new Aluno(cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco, ra, status, cursoSelecionado);

        alunoController.cadastrarAluno(aluno);
    }

    public void listarAlunos() {
        System.out.println("-----Lista de Alunos Cadastrados-----");
        List<Aluno> alunos = alunoController.listarAlunos();

        for (Aluno a : alunos) {
            System.out.println(a.listarPessoa());
            System.out.println("--------------------------------");
        }
    }

    public void excluirAluno() {
        System.out.println("-----Exclusão de Aluno-----");

        System.out.print("Informe o RA do aluno: ");
        String ra = sc.nextLine();

        if (alunoController.excluirAluno(ra)) {
            System.out.println("Aluno excluido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado ou não foi possível realizar a exclusão!");
        }
    }

    public void buscarAlunoPorRa() {
        System.out.println("----- Busca de alunos -----");

        System.out.print("Informe o RA do aluno: ");
        String ra = sc.nextLine();

        Aluno a = alunoController.buscarAlunoPorRa(ra);
        if (a != null) {
            System.out.println(a.listarPessoa());
        } else {
            System.out.println("Não existe aluno com o RA " + ra + " solicitado.");
        }
    }

    public void editarAluno() {
        System.out.println("----- Editar Aluno -----");

        System.out.print("Informe o RA do aluno: ");
        String ra = sc.nextLine();

        Aluno a = alunoController.buscarAlunoPorRa(ra);
        if (a != null) {
            System.out.println(a.listarPessoa());

            String resp;
            do {
                System.out.println("Deseja atualizar o Aluno acima? s - Sim | n - Não");
                resp = sc.nextLine().toUpperCase();

                if (resp.equals("S")) {
                    atualizarDadosPessoa(a);

                    System.out.println("----- Dados de Registro Acadêmico -----");


                    //Escolher o curso ----------------------------------------------------------------------
                    System.out.println("Cursos cadastrados no Sistema: ");

                    List<Curso> listaCurso = cursoController.listarCursos();

                    for (Curso curso : listaCurso) {
                        System.out.println("Código: " + curso.getCdCurso() + " - " + curso.getNomeCurso());
                    }

                    Curso cursoSelecionado = null;

                    System.out.println("Digite o código do curso: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();
                    for (Curso curso : listaCurso) {
                        if (curso.getCdCurso() == codigo) {
                            cursoSelecionado = curso;
                            break;
                        }
                    }
                    if (cursoSelecionado == null) {
                        System.out.println("Curso inexistente.");
                        return;
                    }
                    a.setCurso(cursoSelecionado);

                    //------------------------------------------------------------------------------------

                    int defStatus;
                    String status = "";
                    do {
                        System.out.println("Status atual do aluno (1 - cursando || 2 - concluído || 3 - trancado): ");
                        defStatus = sc.nextInt();
                        if (defStatus == 1) {
                            status = "Cursando";
                        } else if (defStatus == 2) {
                            status = "Concluido";
                        } else if (defStatus == 3) {
                            status = "Trancado";
                        } else {
                            System.out.println("Opção inválida, digite novamente.");
                        }
                    } while (defStatus != 1 && defStatus != 2 && defStatus != 3);
                    sc.nextLine();
                    a.setStatus(status);

                    if (alunoController.editarAluno(a)) {
                        System.out.println("Aluno editado com sucesso!!");
                    } else {
                        System.out.println("Não foi possível atualizar o aluno!!");
                    }

                } else if(resp.equals("N")){
                    System.out.println("Atualização Cancelada!!");
                }
            } while (!resp.equals("S") && !resp.equals("N"));
        } else {
            System.out.println("Não existe aluno com o RA " + ra + " informado.");
            return;
        }
    }
}
