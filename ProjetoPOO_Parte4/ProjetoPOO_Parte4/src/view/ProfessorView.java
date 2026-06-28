package view;


import Controller.ProfessorController;
import models.Professor;

import java.util.List;


public class ProfessorView extends PessoaView {

    private ProfessorController professorController = new ProfessorController();

    public void cadastrarProfessor (){
        System.out.println("|-----Informe os dados abaixo para realizar o cadastro do Professor-----|");

        //Dados padrão para qualquer pessoa//
        lerDadosPessoa();

        //Dados exclusivos professor
        System.out.println("Formação: ");
        String formacao = sc.nextLine();

        System.out.println("Especialização: ");
        String especializacao = " ";
        double valorHora = 0;

        int op;
        do {
            System.out.println("1 - Mestre ");
            System.out.println("2 - Doutor");
            System.out.println("3 - Especialista");
            System.out.println("4 - Graduado");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
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
        } while (op < 1 || op > 4);
        System.out.println("Total de aulas semanais: ");
        int horasSemanais;
        do {
            horasSemanais = sc.nextInt();

            if (horasSemanais < 1 || horasSemanais >60){

                System.out.println("Horas Semanais não pode exceder 60 horas.");

            }
        } while (horasSemanais < 1||horasSemanais > 60);

        Professor professor = new Professor(cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco, formacao, especializacao, valorHora , horasSemanais);

        professorController.cadastrarProfessor(professor);
    }

    public void listarProfessores(){
        System.out.println("-----Lista de Professores Cadastrados-----");
        List<Professor> professores = professorController.listarProfessores();

        for(Professor p: professores){
            System.out.println(p.listarPessoa());
            System.out.println("--------------------------------");
        }
    }
}
