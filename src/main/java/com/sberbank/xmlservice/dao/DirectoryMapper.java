package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.Directory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DirectoryMapper {
    @Select("SELECT * FROM directory WHERE is_input=TRUE")
    @Results(
            id = "directoryResult",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "path", column = "path"),
                    @Result(property = "isInput", column = "is_input")
            }
    )
    Directory findInputDirectory();

    @Select("SELECT * FROM directory WHERE id=#{directoryId}")
    @ResultMap("directoryResult")
    Directory findById(long directoryId);

    @Select("SELECT d.id, d.path, d.is_input FROM file_directory_link fdl JOIN directory d on fdl.id_directory = d.id WHERE fdl.id_file = #{fileId}")
    @ResultMap("directoryResult")
    List<Directory> getDirectoriesByFileId(long fileId);

    @Insert("INSERT INTO directory(path, is_input) VALUES(#{path}, #{isInput})")
    void save(Directory directory);
}
