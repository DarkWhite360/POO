package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PessoaView {

    protected Scanner sc = new Scanner(System.in);

    protected String cpf;
    protected String nome;
    protected int idade;
    protected LocalDate dataNascimento;
    protected String telefone;
    protected String email;
    protected String estadoCivil;
    protected String estado;
    protected String cidade;
    protected String endereco;

    public void lerDadosPessoa(){

        System.out.println("CPF: ");
        cpf = sc.nextLine();

        System.out.println("Nome: ");
        nome = sc.nextLine();

        System.out.println("Idade: ");
        idade = sc.nextInt();
        sc.nextLine();

        // Formatar a data de nascimento -------------------------------------------
        DateTimeFormatter dataFormatada = DateTimeFormatter.ISO_LOCAL_DATE;
        dataNascimento = null;

        while (dataNascimento == null){
            System.out.println("Data de Nascimento (AAAA-MM-DD) - Ex: 1999-10-25");
            String dataTexto = sc.nextLine();
            try{
                dataNascimento = LocalDate.parse(dataTexto, dataFormatada);
            } catch (DateTimeParseException e){
                System.out.println("Data com formato inválido! Digite novamente");
            }
        }
        System.out.println("Data capturada: " +dataNascimento);
        // ------------------------------------------------------------------------

        System.out.println("Telefone: ");
        telefone = sc.nextLine();

        System.out.println("Email: ");
        email = sc.nextLine();

        estadoCivil = "";
        int casado;
        do{
            System.out.println("Estado civil (1 para casado || 0 para solteiro): ");
            casado = sc.nextInt();
            if (casado == 1){
                estadoCivil = "Casado";
            } else if(casado == 0){
                estadoCivil = "Solteiro";
            } else {
                System.out.println("Opção inválida, digite novamente.");
            }
        } while (casado != 1 && casado != 0);
        sc.nextLine();

        System.out.println("----- Dados de Endereço -----");

        System.out.println("Unidade Federativa");
        estado = sc.nextLine();

        System.out.println("Cidade: ");
        cidade = sc.nextLine();

        System.out.println("Endereço residencial");
        endereco = sc.nextLine();

    }
}
