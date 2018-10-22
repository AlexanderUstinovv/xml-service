package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.FileDirectoryLink;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileDirectoryLinkMapper {
    @Insert("INSERT INTO file_directory_link(id_file, id_directory) VALUES(#{fileId}, #{directoryId})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void save(FileDirectoryLink fileDirectoryLink);

    @Update("UPDATE file_directory_link SET id_file = #{fileId}, id_directory = #{directoryId} WHERE id = #{id}")
    void update(FileDirectoryLink fileDirectoryLink);

    @Select("SELECT * FROM file_directory_link WHERE id_file = #{fileId}")
    @Results(
            id = "fileResult",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "fileId", column = "id_file"),
                    @Result(property = "directoryId", column = "id_directory")
            }
    )
    FileDirectoryLink findByFileId(long fileId);
}
