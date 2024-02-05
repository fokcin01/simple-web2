package org.example.repository.springJdbc;

import org.example.model.Resource;
import org.example.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ResourceRepositoryImpl implements ResourceRepository {
    private static final BeanPropertyRowMapper ROW_MAPPER = BeanPropertyRowMapper.newInstance(Resource.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insert;
    @Autowired
    public ResourceRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("resources")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Resource> getAll() {
        return jdbcTemplate.query("select * from resources",ROW_MAPPER);
    }

    @Override
    public Resource getById(int id) {
        List<Resource> query = jdbcTemplate.query("select * from resources where id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(query);
    }

    @Override
    public void delete(int id) {
    jdbcTemplate.update("delete  from resources where id=?",id);
    }

    @Override
    public void save(Resource resource) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id",resource.getId())
                .addValue("name",resource.getName())
                .addValue("price",resource.getPrice());
        if(resource.getId() == null){
            Number newKey = insert.executeAndReturnKey(map);
            resource.setId(newKey.intValue());
        }else if(namedParameterJdbcTemplate.update("update resources set name=:name,price=:price where id=:id",map)==0){

        }



    }
}
