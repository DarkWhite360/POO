package view;

import Controller.AlunoController;
import Controller.CursoController;
import models.Aluno;
import models.Curso;

import java.util.List;


public class AlunoView extends PessoaView{

    private CursoController cursoController = new CursoController();
    private AlunoController alunoController = new AlunoController();

    public void  cadastrarAluno(){

        //verificar se há cursos cadastrados no sistema
        List<Curso> listaCurso = cursoController.listarCursos();
        if(listaCurso.isEmpty()){
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

        for (Curso curso : listaCurso){
            System.out.println("Código: " + curso.getCdCurso()+ " - " +curso.getNomeCurso());
        }

        Curso cursoSelecionado = null;

        System.out.println("Digite o código do curso: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        for (Curso curso : listaCurso){
            if(curso.getCdCurso() == codigo){
                cursoSelecionado = curso;
                break;
            }
        }
        if(cursoSelecionado == null){
            System.out.println("Curso inexistente.");
            return;
        }

        //------------------------------------------------------------------------------------

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

        Aluno aluno = new Aluno(cpf, nome, idade, dataNascimento, telefone , email , estadoCivil, estado, cidade, endereco, ra, status , cursoSelecionado);

        alunoController.cadastrarAluno(aluno);
    }

    public void listarAlunos(){
        System.out.println("-----Lista de Alunos Cadastrados-----");
        List<Aluno> alunos = alunoController.listarAlunos();

        for (Aluno a : alunos){
            System.out.println(a.listarPessoa());
            System.out.println("--------------------------------");
        }
    }

}
