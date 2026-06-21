package dao;

import interfaces.InterfaceAlunoDAO;
import models.Aluno;
import models.Curso;
import models.Pessoa;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO implements InterfaceAlunoDAO {
    List<Aluno> listaAlunos = new ArrayList<>();

    public AlunoDAO() {

    }

    @Override
    public boolean cadastrarAluno(Aluno aluno) {
        String sqlPessoa = "INSERT INTO PESSOA (cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getIdade());
            stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(5, aluno.getTelefone());
            stmt.setString(6, aluno.getEmail());
            stmt.setString(7, aluno.getEstadoCivil());
            stmt.setString(8, aluno.getEstado());
            stmt.setString(9, aluno.getCidade());
            stmt.setString(10, aluno.getEndereco());


            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas <= 0) {
                return false;
            } else {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idPessoa = generatedKeys.getInt(1);

                        String sqlAluno = "INSERT INTO Aluno( ra, status, idcurso_fk, idpessoa_fk) VALUES (?, ?, ?, ?)";

                        try (
                                PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno)
                        ) {
                            stmtAluno.setString(1, aluno.getRa());
                            stmtAluno.setString(2, aluno.getStatus());
                            stmtAluno.setInt(3, aluno.getCurso().getCdCurso());
                            stmtAluno.setInt(4, idPessoa);

                            return stmtAluno.executeUpdate() > 0;
                        }
                    }
                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT p.idPessoa, p.nome, p.cpf, p.idade, p.dataNascimento, p.telefone, p.email, p.estadoCivil, p.estado, p.cidade, p.endereco, a.ra, a.status, c.nomecurso, c.cdcurso " +
                "FROM pessoa p " +
                "INNER JOIN aluno a " +
                "ON p.idpessoa = a.idpessoa_fk " +
                "INNER JOIN curso c " +
                "ON c.cdcurso = a.idcurso_fk";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while(rs.next()){
                Aluno aluno = new Aluno();
                Curso curso = new Curso();
                aluno.setId(rs.getInt("idPessoa"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setEstadoCivil(rs.getString("estadoCivil"));
                aluno.setEstado(rs.getString("estado"));
                aluno.setCidade(rs.getString("cidade"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setRa(rs.getString("ra"));
                aluno.setStatus(rs.getString("status"));
                curso.setNomeCurso(rs.getString("nomeCurso"));
                curso.setCdCurso(rs.getInt("cdCurso"));

                aluno.setCurso(curso);

                alunos.add(aluno);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return alunos;
    }
}
