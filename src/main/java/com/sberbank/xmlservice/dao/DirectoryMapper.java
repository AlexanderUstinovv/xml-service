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
    List<Directory> findDirectoriesByFileId(long fileId);

    @Select("SELECT * FROM directory WHERE path = #{path}")
    @ResultMap("directoryResult")
    Directory findDirectoryByPath(String path);

    @Select("SELECT * FROM directory WHERE is_output=TRUE")
    @ResultMap("directoryResult")
    Directory findOutputDirectory();

    @Select("SELECT * FROM directory WHERE is_archive=TRUE")
    @ResultMap("directoryResult")
    Directory findArchiveDirectory();

    @Insert("INSERT INTO directory(path, is_input, is_output, is_archive) VALUES(#{path}, #{isInput}, #{isOutput}, #{isArchive})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void save(Directory directory);
}
