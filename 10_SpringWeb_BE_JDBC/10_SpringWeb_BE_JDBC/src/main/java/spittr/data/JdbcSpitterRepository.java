package spittr.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.Spitter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    public static final String SPITTR_SPITTER_SAVE = "insert into Spitter (username, password, first_name, last_name, email) values (?, ?, ?, ?, ?)";
    public static final String SPITTR_SPITTER_FIND_BY_USERNAME = "select id, username, null, first_name, last_name, email from Spitter where username=?";



    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public Spitter save(Spitter spitter) {
        jdbcOperations.update(SPITTR_SPITTER_SAVE,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFirstName(),
                spitter.getLastName(),
                spitter.getEmail());
        return spitter; // TODO: Determine value for id
    }

    public Spitter findByUsername(String username) {
        return jdbcOperations.queryForObject(
                SPITTR_SPITTER_FIND_BY_USERNAME,
                new SpitterRowMapper(),
                username);
    }




    private static class SpitterRowMapper implements RowMapper<Spitter> {
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spitter(
                    rs.getLong("id"),
                    rs.getString("username"),
                    null,
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"));
        }
    }


}