package dao;

import interfaces.InterfaceAlunoDAO;
import models.Aluno;
import models.Curso;


import java.sql.*;

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
            while (rs.next()) {
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

    @Override
    public boolean excluirAluno(String ra) {
        String sqlBuscarPessoa = "SELECT idPessoa_fk FROM aluno WHERE ra = ?";

        String sqlExcluirALuno = "DELETE FROM aluno WHERE ra = ?";

        String sqlExcluirPessoa = "DELETE FROM pessoa WHERE idPessoa = ?";


        Connection conn = null;

        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);

            try (
                    PreparedStatement stmtBusca = conn.prepareStatement(sqlBuscarPessoa);
                    PreparedStatement stmtExcluirAluno = conn.prepareStatement(sqlExcluirALuno);
                    PreparedStatement stmtExcluirPessoa = conn.prepareStatement(sqlExcluirPessoa);
            ) {

                stmtBusca.setString(1, ra);

                ResultSet rs = stmtBusca.executeQuery();

                if (!rs.next()) {
                    return false;
                } else {
                    int idPessoa = rs.getInt("idPessoa_fk");


                    stmtExcluirAluno.setString(1, ra);
                    int linhaAluno = stmtExcluirAluno.executeUpdate();

                    stmtExcluirPessoa.setInt(1, idPessoa);
                    int linhaPessoa = stmtExcluirPessoa.executeUpdate();

                    if (linhaAluno == 1 && linhaPessoa == 1) {
                        conn.commit();
                        return true;
                    }

                    conn.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public Aluno buscarAlunoPorRa(String ra) {
        String sql = "SELECT p.idPessoa, p.nome, p.cpf, p.idade, p.dataNascimento, p.telefone, p.email, p.estadoCivil, p.estado, p.cidade, p.endereco, a.ra, a.status, c.nomecurso, c.cdcurso\n" +
                "FROM pessoa p\n" +
                "INNER JOIN aluno a\n" +
                "ON p.idpessoa = a.idpessoa_fk\n" +
                "INNER JOIN curso c\n" +
                "ON c.cdcurso = a.idcurso_fk\n" +
                "WHERE a.ra = ?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setString(1, ra);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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

                return aluno;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean editarAluno(Aluno aluno) {


        String sqlPessoa = "UPDATE pessoa SET " +
                "cpf = ?, " +
                "nome = ?, " +
                "idade = ?, " +
                "dataNascimento = ?, " +
                "telefone = ?, " +
                "email = ?, " +
                "estadoCivil = ?, " +
                "estado = ?, " +
                "cidade = ?, " +
                "endereco = ? " +
                "WHERE idPessoa = ?";

        String sqlAluno = "UPDATE aluno SET " +
                "status = ?, " +
                "idCurso_fk = ? " +
                "WHERE ra = ?";

        Connection conn = null;

        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            try (

                    PreparedStatement stmtAtualizarAluno = conn.prepareStatement(sqlAluno);
                    PreparedStatement stmtAtualizarPessoa = conn.prepareStatement(sqlPessoa);

            ) {
                stmtAtualizarPessoa.setString(1, aluno.getCpf());
                stmtAtualizarPessoa.setString(2, aluno.getNome());
                stmtAtualizarPessoa.setInt(3, aluno.getIdade());
                stmtAtualizarPessoa.setDate(4, Date.valueOf(aluno.getDataNascimento()));
                stmtAtualizarPessoa.setString(5, aluno.getTelefone());
                stmtAtualizarPessoa.setString(6, aluno.getEmail());
                stmtAtualizarPessoa.setString(7, aluno.getEstadoCivil());
                stmtAtualizarPessoa.setString(8, aluno.getEstado());
                stmtAtualizarPessoa.setString(9, aluno.getCidade());
                stmtAtualizarPessoa.setString(10, aluno.getEndereco());
                stmtAtualizarPessoa.setInt(11, aluno.getId());
                stmtAtualizarAluno.setString(1, aluno.getStatus());
                stmtAtualizarAluno.setInt(2, aluno.getCurso().getCdCurso());
                stmtAtualizarAluno.setString(3, aluno.getRa());

                int linhasPessoa = stmtAtualizarPessoa.executeUpdate();
                int linhasAluno = stmtAtualizarAluno.executeUpdate();

                if (linhasPessoa == 1 || linhasAluno == 1) {
                    conn.commit();

                    return true;
                } else {
                    conn.rollback();

                    return false;
                }
            }

        } catch (SQLException e) {
            if (conn != null) {

                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
