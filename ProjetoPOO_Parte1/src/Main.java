import models.Aluno;
import models.Pessoa;
import models.Professor;

import java.util.Scanner;

class Main{
    static Scanner sc = new Scanner(System.in);
    static Aluno[] cadastroAluno = new Aluno[2];
    static Professor[] cadastroProfessor = new Professor[2];
    public static void main(String[] args){


        //Aluno[] cadastroAluno = new Aluno[2];
        //Professor[] cadastroProfessor = new Professor[2];
        //Pessoa//
        int casado, op, defStatus;
        int op2 = 0, op3 = 0;
        String cpf ;
        String nome;
        int idade;
        String dataNascimento;
        String telefone;
        boolean estadoCivil = false;
        String estado;
        String cidade;
        String endereco;

        //Aluno//
        int ra;
        int i = 0;
        String status ="";

        //Professor//
        String formacao;
        String especializacao;
        double valorHora;
        int horasSemanais;

        do{
            System.out.println("<<<<Sistema de cadastro do RH>>>>");
            System.out.println("1- Cadastro.");
            System.out.println("2- Lista de Pessoas Cadastradas.");
            System.out.println("3- Sair do sistema.");
            op = sc.nextInt();

            switch (op){
                case 1:
                    System.out.println("Cadastro: ");
                    System.out.println("1- Aluno.");
                    System.out.println("2- Professor.");
                    op2 = sc.nextInt();

                    switch (op2){
                        case 1:
                            System.out.println("Aluno cadastrado com sucesso");

                            Aluno aluno = lerAluno();
                            cadastroAluno[i] = aluno;
                            i++;
                            break;

                        case 2:
                            System.out.println("Professor cadastrado com sucesso!!");

                            Professor professor = lerProfessor();
                            cadastroProfessor [i] = professor;
                            i++;
                            break;

                        default:
                            System.out.println("Opção inválida, digite novamente.");
                    }
                    break;

                case 2:
                    System.out.println("Lista: ");
                    System.out.println("1- Alunos.");
                    System.out.println("2- Professores.");
                    int j;
                    op3 = sc.nextInt();
                    
                    switch(op3) {
                        
                        case 1: 
                            System.out.println("<<<<Dados dos Alunos>>>>");
                            listaAluno();
                            break;
                            
                        case 2:
                            System.out.println("<<<<Dados dos professores>>>>");
                            listaProfessor();
                            break;

                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 3:
                    System.out.println("Fechando sistema de RH.");
                    break;

                default:
                    System.out.println("Opção inválida, digite novamente. ");
            }

        } while (op != 3);

    }

    public static Aluno lerAluno(){

        System.out.println("Informe os dados pedidos para realizar o cadastro do aluno.");
        sc.nextLine();

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

        System.out.println("Estado cívil ( 1 para casado || 0 para solteiro): ");
        boolean estadoCivil = false;
        int casado = sc.nextInt();
        do{
            if (casado == 1) {
                estadoCivil = true;
            } else if (casado == 0) {
                estadoCivil = false;
            } else {
                System.out.println("Opção inválida, digite novamente.");
            }
        } while (casado != 1 && casado != 0);
        sc.nextLine();

        System.out.println("Estado: ");
        String estado = sc.nextLine();

        System.out.println("Cidade: ");
        String cidade = sc.nextLine();

        System.out.println("Endereço: ");
        String endereco = sc.nextLine();

        System.out.println("Número do registro academico (RA): ");
        int ra = sc.nextInt();
        sc.nextLine();

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

        return new Aluno (cpf, nome, idade, dataNascimento, telefone, estadoCivil, estado, cidade, endereco, ra, status);
    }

    public static Professor lerProfessor(){
        System.out.println("Informe os dados pedidos para realizar o cadastro do professor");
        sc.nextLine();

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

        System.out.println("Estado cívil ( 1 para casado || 0 para solteiro: )");
        boolean estadoCivil = false;
        int casado = sc.nextInt();
        do{
            if (casado == 1) {
                estadoCivil = true;
            } else if (casado == 0) {
                estadoCivil = false;
            } else {
                System.out.println("Opção inválida, digite novamente.");
            }
        } while (casado != 1 && casado != 0);
        sc.nextLine();

        System.out.println("Estado: ");
        String estado = sc.nextLine();

        System.out.println("Cidade: ");
        String cidade = sc.nextLine();

        System.out.println("Endereço: ");
        String endereco = sc.nextLine();

        System.out.println("Formação: ");
        String formacao = sc.nextLine();

        System.out.println("Especialização: ");
        String especializacao = sc.nextLine();

        System.out.println("Valor da hora aula: ");
        double valorHora = sc.nextDouble();

        System.out.println("Total de horas semanais: ");
        int horasSemanais = sc.nextInt();

        return new Professor(cpf, nome, idade, dataNascimento, telefone, estadoCivil, estado, cidade, endereco,formacao, especializacao, valorHora, horasSemanais);
    }
    
    public static void listaAluno(){
        int j;
        for (j = 0; j < cadastroAluno.length; j++) {
            if (cadastroAluno[j]!=null) {
                System.out.println("CPF: " + cadastroAluno[j].getCpf());
                System.out.println("Nome: " + cadastroAluno[j].getNome());
                System.out.println("Idade: " + cadastroAluno[j].getIdade());
                System.out.println("Data de Nascimento: " + cadastroAluno[j].getDataNascimento());
                System.out.println("Telefone: " + cadastroAluno[j].getTelefone());
                System.out.println("Estado cívil: " + cadastroAluno[j].getEstadoCivil());
                System.out.println("Estado: " + cadastroAluno[j].getEstado());
                System.out.println("Cidade: " + cadastroAluno[j].getCidade());
                System.out.println("Endereço: " + cadastroAluno[j].getEndereco());
                System.out.println("Número do registro academico (RA): " + cadastroAluno[j].getRa());
                System.out.println("Status atual do aluno: " + cadastroAluno[j].getStatus());
                System.out.println("");
            }
        }
    }
    public static void listaProfessor(){
        int j;
        for (j = 0; j < cadastroProfessor.length; j++) {
            if (cadastroProfessor[j]!= null) {
                System.out.println("CPF: " + cadastroProfessor[j].getCpf());
                System.out.println("Nome: " + cadastroProfessor[j].getNome());
                System.out.println("Idade: " + cadastroProfessor[j].getIdade());
                System.out.println("Data de Nascimento: " + cadastroProfessor[j].getDataNascimento());
                System.out.println("Telefone: " + cadastroProfessor[j].getTelefone());
                System.out.println("Estado cívil: " + cadastroProfessor[j].getEstadoCivil());
                System.out.println("Estado: " + cadastroProfessor[j].getEstado());
                System.out.println("Cidade: " + cadastroProfessor[j].getCidade());
                System.out.println("Endereço: " + cadastroProfessor[j].getEndereco());
                System.out.println("Formação: " + cadastroProfessor[j].getFormacao());
                System.out.println("Especialização: " + cadastroProfessor[j].getEspecializacao());
                System.out.println("Valor da hora-aula: " + cadastroProfessor[j].getValorHora());
                System.out.println("Total de horas semanais: " + cadastroProfessor[j].getHorasSemanais());
                System.out.println("Salário Semanal: R$ " + cadastroProfessor[j].calcularSalario());
                System.out.println("");
            }
        }
    }


}
