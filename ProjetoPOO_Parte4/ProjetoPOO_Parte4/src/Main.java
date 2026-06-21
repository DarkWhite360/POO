import Controller.AlunoController;
import Controller.CursoController;
import Controller.ProfessorController;
import models.Aluno;
import models.Curso;
import models.Professor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;



class Main{

    static Scanner sc = new Scanner(System.in);
    static int contadorCurso, idade;
    static String cpf, nome, telefone, email, estado, cidade, endereco, estadoCivil;
    static LocalDate dataNascimento;
    static CursoController cursoController = new CursoController();
    static AlunoController alunoController = new AlunoController();
    static ProfessorController professorController = new ProfessorController();


    public static void main (String[] args){


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
            System.out.print  ("|Escolha uma opção:");
            op = sc.nextInt();
            sc.nextLine();

            switch(op){

                case 1:
                    System.out.println(dataNascimento);
                    Aluno aluno = cadastrarAluno(cursoController);
                    if(aluno != null){
                        alunoController.cadastrarAluno(aluno);
                        System.out.println("Aluno cadastrado com sucesso.");
                    } else {
                        System.out.println("Erro ao cadastrar o aluno");
                    }
                    break;

                case 2:
                    if (professorController.cadastrarProfessor(cadastrarProfessor())){
                        System.out.println("professor Cadastrado com sucesso!!!");
                    } else {
                        System.out.println("Erro ao cadastrar professor!!!");
                    }
                    break;
                case 3:
                    if(cursoController.cadastrarCurso(cadastrarCurso())) {
                        System.out.println("Curso cadastrado com sucesso!!");
                    } else {
                        System.out.println("Erro ao cadastrar curso");
                    }
                    break;
                case 4:
                    System.out.println("-----Lista de Alunos Cadastrados-----");
                    List<Aluno> alunos =alunoController.listarAlunos();

                    for (Aluno a : alunos){
                        System.out.println(a.listarPessoa());
                        System.out.println("--------------------------------");
                    }

                    break;
                case 5:
                    System.out.println("-----Lista de Proessores Cadastrados-----");
                    List<Professor> professores = professorController.listarProfessores();

                    for(Professor p: professores){
                        System.out.println(p.listarPessoa());
                        System.out.println("--------------------------------");
                    }
                    break;
                case 6:
                    for(Curso c: cursoController.listarCursos()){
                        System.out.println("----------------------------------");
                        System.out.println("Código Curso: " +c.getCdCurso());
                        System.out.println("Nome do Curso: " +c.getNomeCurso());
                    }
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

        //verificar se há cursos cadastrados, cajo não haja, não deve ser possivel cadastrar alunos.
        List<Curso> listaCurso = cursoController.listarCursos();
        if(listaCurso.isEmpty()){
            System.out.println("Nenhum curso cadastrado no sistema!");
            return null;
        }

        //-------------------------------------------------------------------------------------------

        System.out.println("|-----Informe os dados abaixo para realizar o cadastro do aluno-----|");

        //Dados padrão para qualquer pessoa//
        cadastrarPessoa();

        //Dados exclusivos do aluno//

        System.out.println("-----Dados de registro acadêmico-----");

        System.out.println("Número de Registro Acadêmico (RA)");
        String ra = sc.nextLine();


        //Escolher o curso
        System.out.println("Cursos cadastrados no sistema:  ");

        for(int i = 0; i < listaCurso.size(); i ++) {
            System.out.println( i+" ) " +listaCurso.get(i).getNomeCurso());
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

        Aluno aluno = new Aluno(cpf, nome, idade, dataNascimento, telefone , email , estadoCivil, estado, cidade, endereco, ra, status , curso);

        return aluno;

    }


    public static Professor cadastrarProfessor(){
        System.out.println("|-----Informe os dados abaixo para realizar o cadastro do Professor-----|");

        //Dados padrão para qualquer pessoa//
        cadastrarPessoa();

        //Dados exclusivos professor
        System.out.println("Formação: ");
        String formacao = sc.nextLine();

        System.out.println("Especialização: ");
        String especializacao = " ";
        double valorHora = 0;

        do {
            System.out.println("1 - Mestre ");
            System.out.println("2 - Doutor");
            System.out.println("3 - Especialista");
            System.out.println("4 - Graduado");
            int op = sc.nextInt();
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
        } while (especializacao == null);
        System.out.println("Total de aulas semanais: ");
        int horasSemanais;
        do {
            horasSemanais = sc.nextInt();

            if (horasSemanais >60){
                System.out.println("Horas Semanais não pode exceder 60 horas.");
            }
        } while (horasSemanais > 60);

        return new Professor(cpf, nome, idade, dataNascimento, telefone , email , estadoCivil, estado, cidade, endereco, formacao, especializacao, valorHora, horasSemanais);

    }

    public static Curso cadastrarCurso(){
        System.out.println("Nome do curso: ");
        String nomeCurso = sc.nextLine();

        return new Curso(nomeCurso);
    }

    public static void cadastrarPessoa(){
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
        while (dataNascimento == null) {
            System.out.println("Data de Nascimento  (AAAA-MM-DD) - Ex: 1999-10-25");
            String dataTexto =sc.nextLine();
            try{
                dataNascimento = LocalDate.parse(dataTexto, dataFormatada);
            } catch (DateTimeParseException e){
                System.out.println("Data com formato inválido! Digite novamente");
            }
        }
        System.out.println("Data Capturada: " +dataNascimento);
        // ------------------------------------------------------------------------

        System.out.println("Telefone: ");
        telefone = sc.nextLine();

        System.out.println("Email: ");
        email = sc.nextLine();

        String estadoCivil = "";
        int casado;
        do{
            System.out.println("Estado cívil ( 1 para casado || 0 para solteiro): ");
            casado = sc.nextInt();
            if (casado == 1) {
                estadoCivil = "Casado";
            } else if (casado == 0) {
                estadoCivil = "Solteiro";
            } else {
                System.out.println("Opção inválida, digite novamente.");
            }
        } while (casado != 1 && casado != 0);
        sc.nextLine();

        System.out.println("-----Dados de endereço-----");

        System.out.println("Unidade Federativa: ");
        estado = sc.nextLine();

        System.out.println("Cidade: ");
        cidade = sc.nextLine();

        System.out.println("Endereço residencial");
        endereco = sc.nextLine();
    }
}
