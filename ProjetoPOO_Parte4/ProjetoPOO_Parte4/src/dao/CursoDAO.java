package dao;

import interfaces.InterfaceCursoDAO;
import models.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements InterfaceCursoDAO {

    List<Curso> listaCursos = new ArrayList<>();

    @Override
    public boolean cadastrarCurso(Curso curso) {
        String sqlCurso = "INSERT INTO curso (nomeCurso) VALUES (?)";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sqlCurso);

        ) {
            stmt.setString(1, curso.getNomeCurso());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<>();

        String sql = "SELECT cdcurso, nomeCurso FROM curso";

        try(
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){
            while (rs.next()){
                Curso curso = new Curso();
                curso.setCdCurso(rs.getInt("cdCurso"));
                curso.setNomeCurso(rs.getString("nomeCurso"));

                cursos.add(curso);
            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return cursos;
    }
}
