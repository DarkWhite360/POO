import models.Aluno;
import models.Pessoa;

import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Aluno[] cadastrarAluno = new Aluno[2];
        int casado, op, defStatus;
        String cpf ;
        String nome;
        int idade;
        String dataNascimento;
        String telefone;
        boolean estadoCivil = false;
        String estado;
        String cidade;
        String endereco;
        int ra;
        int i = 0;
        String status ="";

        do{
            System.out.println("<<<<Sistema de cadastro de alunos>>>>");
            System.out.println("1- Matricular aluno: ");
            System.out.println("2- Lista de alunos matriculados: ");
            System.out.println("3- Sair do sistema");
            op = sc.nextInt();

            switch (op){
                case 1:
                    System.out.println("Informe os dados pedidos para realizar o cadastro do aluno");
                        sc.nextLine();

                        System.out.println("CPF: ");
                        cpf = sc.nextLine();

                        System.out.println("Nome: ");
                        nome = sc.nextLine();

                        System.out.println("Idade: ");
                        idade = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Data de Nascimento: ");
                        dataNascimento = sc.nextLine();

                        System.out.println("Telefone: ");
                        telefone = sc.nextLine();

                        System.out.println("Estado cívil ( 1 para sim || 0 para não): ");
                        casado = sc.nextInt();
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
                        estado = sc.nextLine();

                        System.out.println("Cidade: ");
                        cidade = sc.nextLine();

                        System.out.println("Endereço: ");
                        endereco = sc.nextLine();

                        System.out.println("Número do registro academico (RA): ");
                        ra = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Status atual do aluno (1 - cursando || 2 - concluído || 3 - trancado): ");
                        do{
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

                        Aluno aluno = new Aluno (cpf, nome, idade, dataNascimento, telefone, estadoCivil, estado, cidade, endereco, ra, status);

                        cadastrarAluno[i] = aluno;
                        i++;
                        break;


                case 2:
                    int j;
                    System.out.println("<<<<Dados dos Alunos>>>>");
                    for (j=0; j<i; j++){
                        System.out.println("CPF: " +cadastrarAluno[j].getCpf());
                        System.out.println("Nome: " +cadastrarAluno[j].getNome());
                        System.out.println("Idade: " +cadastrarAluno[j].getIdade());
                        System.out.println("Data de Nascimento: " +cadastrarAluno[j].getDataNascimento());
                        System.out.println("Telefone: " +cadastrarAluno[j].getTelefone());
                        System.out.println("Estado cívil: " +cadastrarAluno[j].getEstadoCivil());
                        System.out.println("Estado: " +cadastrarAluno[j].getEstado());
                        System.out.println("Cidade: " +cadastrarAluno[j].getCidade());
                        System.out.println("Endereço: " +cadastrarAluno[j].getEndereco());
                        System.out.println("Número do registro academico (RA): " +cadastrarAluno[j].getRa());
                        System.out.println("Status atual do aluno: " +cadastrarAluno[j].getStatus());
                    }
                    break;

                case 3:
                    System.out.println("Fechando sistema de .");
                    break;

                default:
                    System.out.println("opção inválida, digite novamente. ");
            }

        } while (op != 3);



    }
}
