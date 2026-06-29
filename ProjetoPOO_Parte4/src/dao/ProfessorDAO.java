package dao;

import interfaces.InterfaceProfessorDAO;
import models.Aluno;
import models.Curso;
import models.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO implements InterfaceProfessorDAO {
    List<Professor> listaProfessores = new ArrayList<>();

    public ProfessorDAO() {

    }

    @Override
    public boolean cadastrarProfessor(Professor professor) {
        String sqlPessoa = "INSERT INTO PESSOA (cpf, nome, idade, dataNascimento, telefone, email, estadoCivil, estado, cidade, endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, professor.getCpf());
            stmt.setString(2, professor.getNome());
            stmt.setInt(3, professor.getIdade());
            stmt.setDate(4, Date.valueOf(professor.getDataNascimento()));
            stmt.setString(5, professor.getTelefone());
            stmt.setString(6, professor.getEmail());
            stmt.setString(7, professor.getEstadoCivil());
            stmt.setString(8, professor.getEstado());
            stmt.setString(9, professor.getCidade());
            stmt.setString(10, professor.getEndereco());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas <= 0) {
                return false;
            } else {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idPessoa = generatedKeys.getInt(1);

                        String sqlProfessor = "INSERT INTO Professor( formacao, especializacao, valorHora, horasSemanais, idPessoa_fk) VALUES (?, ?, ?, ?, ?)";

                        try (
                                PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor)
                        ) {
                            stmtProfessor.setString(1, professor.getFormacao());
                            stmtProfessor.setString(2, professor.getEspecializacao());
                            stmtProfessor.setDouble(3, professor.getValorHora());
                            stmtProfessor.setInt(4, professor.getHorasSemanais());
                            stmtProfessor.setInt(5, idPessoa);

                            return stmtProfessor.executeUpdate() > 0;
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
    public List<Professor> listarProfessores() {
        List<Professor> professores = new ArrayList<>();

        String sql = "SELECT p.idPessoa, p.nome, p.cpf, p.idade, p.dataNascimento, p.telefone, p.email, p.estadoCivil, p.estado, p.cidade, p.endereco, pr.formacao, pr.especializacao, pr.valorHora, pr.horasSemanais FROM pessoa p INNER JOIN professor pr ON p.idpessoa = pr.idpessoa_fk";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("idPessoa"));
                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setIdade(rs.getInt("idade"));
                professor.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                professor.setTelefone(rs.getString("telefone"));
                professor.setEmail(rs.getString("email"));
                professor.setEstadoCivil(rs.getString("estadoCivil"));
                professor.setEstado(rs.getString("estado"));
                professor.setCidade(rs.getString("cidade"));
                professor.setEndereco(rs.getString("endereco"));
                professor.setFormacao(rs.getString("formacao"));
                professor.setEspecializacao(rs.getString("especializacao"));
                professor.setValorHora(rs.getDouble("valorHora"));
                professor.setHorasSemanais(rs.getInt("horasSemanais"));

                professores.add(professor);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return professores;
    }

    @Override
    public boolean excluirProfessor(String cpf) {

        String sqlBuscarPessoa = "SELECT p.idPessoa " +
                "FROM pessoa p " +
                "INNER JOIN professor pr " +
                "ON p.idPessoa = pr.idPessoa_fk " +
                "WHERE p.cpf = ?";

        String sqlExcluirProfessor = "DELETE FROM professor WHERE idPessoa_fk = ?";

        String sqlExcluirPessoa = "DELETE FROM pessoa WHERE idPessoa = ?";


        Connection conn = null;

        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);

            try (
                    PreparedStatement stmtBusca = conn.prepareStatement(sqlBuscarPessoa);
                    PreparedStatement stmtExcluirProfessor = conn.prepareStatement(sqlExcluirProfessor);
                    PreparedStatement stmtExcluirPessoa = conn.prepareStatement(sqlExcluirPessoa);
            ) {
                stmtBusca.setString(1, cpf);

                ResultSet rs = stmtBusca.executeQuery();

                if (!rs.next()) {
                    return false;
                } else {
                    int idPessoa = rs.getInt("idPessoa");

                    stmtExcluirProfessor.setInt(1, idPessoa);
                    int linhaProfessor = stmtExcluirProfessor.executeUpdate();

                    stmtExcluirPessoa.setInt(1, idPessoa);
                    int linhaPessoa = stmtExcluirPessoa.executeUpdate();

                    if (linhaProfessor == 1 && linhaPessoa == 1) {
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
    public Professor buscarProfessorPorCPF(String cpf) {
        String sql = "SELECT p.idPessoa, p.nome, p.cpf, p.idade, p.dataNascimento, p.telefone, p.email, p.estadoCivil, p.estado, p.cidade, p.endereco, pr.formacao, pr.especializacao, pr.valorHora, pr.horasSemanais " +
                "FROM pessoa p " +
                "INNER JOIN professor pr " +
                "ON p.idpessoa = pr.idpessoa_fk " +
                "WHERE CPF = ?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Professor professor = new Professor();

                professor.setId(rs.getInt("idPessoa"));
                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setIdade(rs.getInt("idade"));
                professor.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                professor.setTelefone(rs.getString("telefone"));
                professor.setEmail(rs.getString("email"));
                professor.setEstadoCivil(rs.getString("estadoCivil"));
                professor.setEstado(rs.getString("estado"));
                professor.setCidade(rs.getString("cidade"));
                professor.setEndereco(rs.getString("endereco"));
                professor.setFormacao(rs.getString("formacao"));
                professor.setEspecializacao(rs.getString("especializacao"));
                professor.setValorHora(rs.getDouble("valorHora"));
                professor.setHorasSemanais(rs.getInt("horasSemanais"));

                return professor;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean editarProfessor(Professor professor) {


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

        String sqlProfessor = "UPDATE professor SET " +
                "formacao = ?, " +
                "especializacao = ?, " +
                "valorHora = ?, "+
                "horasSemanais = ? "+
                "WHERE idPessoa_fk = ? ";

        Connection conn = null;

        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            try (

                    PreparedStatement stmtAtualizarProfessor = conn.prepareStatement(sqlProfessor);
                    PreparedStatement stmtAtualizarPessoa = conn.prepareStatement(sqlPessoa);

            ) {
                stmtAtualizarPessoa.setString(1, professor.getCpf());
                stmtAtualizarPessoa.setString(2, professor.getNome());
                stmtAtualizarPessoa.setInt(3, professor.getIdade());
                stmtAtualizarPessoa.setDate(4, Date.valueOf(professor.getDataNascimento()));
                stmtAtualizarPessoa.setString(5, professor.getTelefone());
                stmtAtualizarPessoa.setString(6, professor.getEmail());
                stmtAtualizarPessoa.setString(7, professor.getEstadoCivil());
                stmtAtualizarPessoa.setString(8, professor.getEstado());
                stmtAtualizarPessoa.setString(9, professor.getCidade());
                stmtAtualizarPessoa.setString(10, professor.getEndereco());
                stmtAtualizarPessoa.setInt(11, professor.getId());
                stmtAtualizarProfessor.setString(1, professor.getFormacao());
                stmtAtualizarProfessor.setString(2, professor.getEspecializacao());
                stmtAtualizarProfessor.setDouble(3, professor.getValorHora());
                stmtAtualizarProfessor.setInt(4, professor.getHorasSemanais());
                stmtAtualizarProfessor.setInt(5, professor.getId());

                int linhasPessoa = stmtAtualizarPessoa.executeUpdate();
                int linhasProfessor = stmtAtualizarProfessor.executeUpdate();

                if (linhasPessoa == 1 || linhasProfessor == 1) {
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
