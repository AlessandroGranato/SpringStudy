package spittr.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.Spitter;
import spittr.Spittle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcSpittleRepository implements SpittleRepository {

    private final static String SPITTR_SPITTLE_SAVE = "INSERT INTO SPITTLE (message, creation_date, latitude, longitude) values (?, ?, ?, ?)";
    private final static String SPITTR_SPITTLE_FIND_BY_ID = "SELECT * FROM SPITTLE WHERE ID = ?";
    private final static String SPITTR_SPITTLE_FIND_SPITTLES = "SELECT * FROM SPITTLE";

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcSpittleRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return jdbcOperations.query(SPITTR_SPITTLE_FIND_SPITTLES,
                new SpittleRowMapper());
    }

    @Override
    public Spittle findOne(long spittleId) {
        return jdbcOperations.queryForObject(
                SPITTR_SPITTLE_FIND_BY_ID,
                new SpittleRowMapper(),
                spittleId);
    }

    @Override
    public Spittle saveSpittle(Spittle spittle) {
        jdbcOperations.update(
                SPITTR_SPITTLE_SAVE,
                spittle.getMessage(),
                new Date(),
                spittle.getLatitude(),
                spittle.getLongitude()
        );

        return spittle;
    }

    private static class SpittleRowMapper implements RowMapper<Spittle> {
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spittle(
                    rs.getLong("id"),
                    rs.getString("message"),
                    rs.getDate("creation_date"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"));
        }
    }
}
