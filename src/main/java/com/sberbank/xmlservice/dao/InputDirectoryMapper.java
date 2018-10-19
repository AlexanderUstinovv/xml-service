package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.InputDirectory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InputDirectoryMapper {
    @Select("SELECT id, path FROM input_directory WHERE id IN (SELECT MAX(id) FROM input_directory)")
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "path", column = "path")
            }
    )
    InputDirectory findLast();
}
