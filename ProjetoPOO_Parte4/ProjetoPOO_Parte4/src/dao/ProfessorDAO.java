package dao;

import interfaces.InterfaceProfessorDAO;
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

        String sql ="SELECT p.idPessoa, p.nome, p.cpf, p.idade, p.dataNascimento, p.telefone, p.email, p.estadoCivil, p.estado, p.cidade, p.endereco, pr.formacao, pr.especializacao, pr.valorHora, pr.horasSemanais FROM pessoa p INNER JOIN professor pr ON p.idpessoa = pr.idpessoa_fk";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){
            while(rs.next()){
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
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return professores;
    }
}
