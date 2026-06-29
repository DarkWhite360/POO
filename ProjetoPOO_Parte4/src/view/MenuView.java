package view;

import java.util.Scanner;

public class MenuView {

    private Scanner sc = new Scanner(System.in);

    private AlunoView alunoView = new AlunoView();
    private ProfessorView professorView = new ProfessorView();
    private CursoView cursoView = new CursoView();


    public void iniciarSistema(){

        int op;


        do{
            mostrarMenu();
            op = sc.nextInt();
            sc.nextLine();

            switch(op){

                case 1:
                    alunoView.cadastrarAluno();
                    break;
                case 2:
                    professorView.cadastrarProfessor();
                    break;
                case 3:
                    cursoView.cadastrarCurso();
                    break;
                case 4:
                    alunoView.listarAlunos();
                    break;
                case 5:
                    professorView.listarProfessores();
                    break;
                case 6:
                    cursoView.listarCursos();
                    break;
                case 7:
                    alunoView.excluirAluno();
                    break;
                case 8:
                    alunoView.buscarAlunoPorRa();
                    break;
                case 9:
                    alunoView.editarAluno();
                    break;
                case 10:
                    professorView.excluirProfessor();
                    break;
                case 11:
                    professorView.buscarProfessorPorCPF();
                    break;
                case 12:
                    professorView.editarProfessor();
                    break;
                case 13:
                    cursoView.excluirCurso();
                    break;
                case 14:
                    cursoView.buscarCursoPorCodigo();
                    break;
                case 15:
                    cursoView.editarCurso();
                    break;
                case 0:
                    System.out.println("Saindo do Sistema");
                    break;

                default:
                    System.out.println("Opção inválida");
            }

        } while (op != 0);
    }

    private void mostrarMenu(){

        System.out.println("|--------------------Sistema Acadêmico-------------------------|");
        System.out.println("| 1 - Cadastrar Aluno                                          |");
        System.out.println("| 2 - Cadastrar Professor                                      |");
        System.out.println("| 3 - Cadastrar Curso                                          |");
        System.out.println("| 4 - Listar Alunos                                            |");
        System.out.println("| 5 - Listar Professores                                       |");
        System.out.println("| 6 - Listar Cursos                                            |");
        System.out.println("| 7 - Excluir Aluno                                            |");
        System.out.println("| 8 - Buscar Aluno por RA                                      |");
        System.out.println("| 9 - Editar Aluno                                             |");
        System.out.println("| 10 - Excluir Professor                                       |");
        System.out.println("| 11 - Buscar Professor por CPF                                |");
        System.out.println("| 12 - Editar Professor                                        |");
        System.out.println("| 13 - Excluir Curso                                           |");
        System.out.println("| 14 - Buscar Curso por Código                                 |");
        System.out.println("| 15 - Editar Curso                                            |");
        System.out.println("| 0 - Sair                                                     |");
        System.out.println("|--------------------------------------------------------------|");
        System.out.print  ("|Escolha uma opção:");

    }

}
