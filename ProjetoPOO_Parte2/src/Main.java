import Controller.AlunoController;
import Controller.CursoController;
import Controller.ProfessorController;
import models.Aluno;
import models.Curso;
import models.Pessoa;
import models.Professor;

import java.util.ArrayList;
import java.util.Scanner;

class Main{

    static Scanner sc = new Scanner(System.in);
    static int contadorCurso;

    public static void main (String[] args){
        CursoController cursoController = new CursoController();
        AlunoController alunoController = new AlunoController();
        ProfessorController professorController = new ProfessorController();

        int op;

        do{

            System.out.println("|--------------------Sistema Acadêmico-------------------------|");
            System.out.println("| 1 - Cadastrar Aluno                                          |");
            System.out.println("| 2 - Cadastrar Professor                                      |");
            System.out.println("| 3 - Cadastrar Curso                                          |");
            System.out.println("| 4 - Listar Alunos                                            |");
            System.out.println("| 5 - Listar Professores                                       |");
            System.out.println("| 6 - Listar Cursos                                            |");
            System.out.println("| 0 - Sair                                                     |");
            System.out.println("|--------------------------------------------------------------|");
            System.out.println("|Escolha uma opção:                                            |");
            System.out.println("|--------------------------------------------------------------|");
            op = sc.nextInt();
            sc.nextLine();

            switch(op){

                case 1:
                    Aluno aluno = cadastrarAluno(cursoController);
                    if(aluno != null){
                        alunoController.cadastrarAluno(aluno);
                    }
                    break;
                case 2:
                    professorController.cadastrarProfessor(cadastrarProfessor());
                    break;
                case 3:
                    cursoController.cadastrarCurso(cadastrarCurso());
                    break;
                case 4:
                    alunoController.listarAluno();
                    break;
                case 5:
                    professorController.listarProfessor();
                    break;
                case 6:
                    cursoController.listarCurso();
                    break;

                case 0:
                    System.out.println("Saindo do Sistema");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        } while (op != 0);
    }

    public static Aluno cadastrarAluno(CursoController cursoController){

        ArrayList<Curso> listaCurso = cursoController.getListaCurso();

        //verificar se há cursos cadastrados, cajo não haja, não deve ser possivel cadastrar alunos.
        if(listaCurso.isEmpty()){
            System.out.println("Nenhum curso cadastrado no sistema!");
            return null;
        }

        System.out.println("|-----Informe os dados abaixo para realizar o cadastro do aluno-----|");

        //Dados padrão para qualquer pessoa//
        Pessoa pessoa = cadastrarPessoa();

        //Dados exclusivos do aluno//

        System.out.println("-----Dados de regeistro acadêmico-----");

        System.out.println("Número de Registro Acadêmico (RA)");
        int ra = sc.nextInt();
        sc.nextLine();

        //Escolher o curso
        System.out.println("Cursos cadastrados no sistema:  ");

        for(int i = 0; i < listaCurso.size(); i ++) {
            System.out.println(i+ " ) " +listaCurso.get(i).getNomeCurso());
        }

        int opCurso;
        do {
            System.out.println("Digite o código do curso para cadastrar o aluno:");
            opCurso = sc.nextInt();

            if(opCurso < 0 || opCurso >= listaCurso.size()){
                System.out.println("Código de curso inválido");
            }

        } while (opCurso < 0 || opCurso >= listaCurso.size());
        sc.nextLine();

        Curso curso = listaCurso.get(opCurso);

        int defStatus;
        String status = "";
        do{
            System.out.println("Status atual do aluno (1 - cursando || 2 - concluído || 3 - trancado): ");
            defStatus = sc.nextInt();
            if(defStatus == 1){
                status = "Cursando";
            } else if (defStatus == 2){
                status = "Concluido";
            } else if (defStatus == 3){
                status = "Trancado";
            } else {
                System.out.println("Opção inválida, digite novamente.");
            }
        } while (defStatus != 1 && defStatus != 2 && defStatus!= 3);
        sc.nextLine();

        Aluno aluno = new Aluno(pessoa.getCpf(), pessoa.getNome(), pessoa.getIdade(), pessoa.getDataNascimento(), pessoa.getTelefone() , pessoa.getEmail() , pessoa.getEstadoCivil(), pessoa.getEstado(), pessoa.getCidade(), pessoa.getEndereco(), ra, curso, status);

        curso.getAlunos().add(aluno);

        return aluno;

    }

    public static Professor cadastrarProfessor(){
        System.out.println("|-----Informe os dados abaixo para realizar o cadastro do Professor-----|");

        //Dados padrão para qualquer pessoa//
        Pessoa pessoa = cadastrarPessoa();

        //Dados exclusivos professor
        System.out.println("Formação: ");
        String formacao = sc.nextLine();

        System.out.println("Especialização: ");
        String especializacao = " ";
        double valorHora = 0;

        System.out.println("1 - Mestre ");
        System.out.println("2 - Doutor");
        System.out.println("3 - Especialista");
        System.out.println("4 - Graduado");
        int op = sc.nextInt();
        sc.nextLine();

        switch(op){
            case 1:
                especializacao = "Mestre";
                valorHora = 60.00;
                break;
            case 2:
                especializacao = "Doutor";
                valorHora = 80.00;
                break;
            case 3:
                especializacao = "Especialista";
                valorHora = 60.00;
                break;
            case 4:
                especializacao = "Graduado";
                valorHora = 50.00;
                break;
            default:
                System.out.println("Opção inválida!!");
        }

        System.out.println("Total de aulas semanais: ");
        int horasSemanais;
        do {
            horasSemanais = sc.nextInt();

            if (horasSemanais >60){
                System.out.println("Horas Semanais não pode exceder 60 horas.");
            }
        } while (horasSemanais > 60);

        return new Professor(pessoa.getCpf(), pessoa.getNome(), pessoa.getIdade(), pessoa.getDataNascimento(), pessoa.getTelefone() , pessoa.getEmail() , pessoa.getEstadoCivil(), pessoa.getEstado(), pessoa.getCidade(), pessoa.getEndereco(), formacao, especializacao, valorHora, horasSemanais);

    }

    public static Curso cadastrarCurso(){
        System.out.println("Nome do curso: ");
        String nomeCurso = sc.nextLine();

        return new Curso(++contadorCurso, nomeCurso);
    }

    public static Pessoa cadastrarPessoa(){
        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        System.out.println("Nome: ");
        String nome = sc.nextLine();

        System.out.println("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.println("Data de Nascimento: ");
        String dataNascimento = sc.nextLine();

        System.out.println("Telefone: ");
        String telefone = sc.nextLine();

        System.out.println("Email: ");
        String email = sc.nextLine();

        boolean estadoCivil = false;
        int casado;
        do{
            System.out.println("Estado cívil ( 1 para casado || 0 para solteiro): ");
            casado = sc.nextInt();
            if (casado == 1) {
                estadoCivil = true;
            } else if (casado == 0) {
                estadoCivil = false;
            } else {
                System.out.println("Opção inválida, digite novamente.");
            }
        } while (casado != 1 && casado != 0);
        sc.nextLine();

        System.out.println("-----Dados de endereço-----");

        System.out.println("Unidade Federativa: ");
        String estado = sc.nextLine();

        System.out.println("Cidade: ");
        String cidade = sc.nextLine();

        System.out.println("Endereço residencial");
        String endereco = sc.nextLine();

        return new Pessoa(cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco);
    }
}
