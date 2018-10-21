package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FileMapper {
    @Select("SELECT * FROM file WHERE id=#{id}")
    @Results(
            id = "fileResult",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "md5Sum", column = "md5_sum"),
                    @Result(property = "content", column = "content"),
                    @Result(property = "date", column = "date")
            }
    )
    File findById(long id);

    @Select("SELECT * FROM file f JOIN file_directory_link fdl ON fdl.id_file = f.id WHERE f.name=#{name} AND fdl.id_directory=#{directoryId}")
    @ResultMap("fileResult")
    File findByNameAndDirectoryId(String name, long directoryId);

    @Select("SELECT * FROM file WHERE md5_sum=#{md5Sum}")
    @ResultMap("fileResult")
    File findByMd5Sum(String md5Sum);

    @Select("SELECT f.id, f.name, f.md5_sum, f.content, f.date FROM file_directory_link fdl JOIN file f ON fdl.id_file = f.id WHERE fdl.id_directory=#{directoryId}")
    @ResultMap("fileResult")
    List<File> findFilesByDirectoryId(long directoryId);

    @Select("SELECT * FROM file_directory_link fdl JOIN file f ON fdl.id_file = f.id WHERE fdl.id_directory=#{directoryId}")
    @ResultMap("fileResult")
    @MapKey("md5Sum")
    Map<byte[], File> findMapFilesByDirectoryId(long directoryId);

    @Select({"<script>SELECT * FROM file WHERE name IN <foreach item='emp' collection='fileNamesList' open='(' separator=', ' close=')'>#{emp}</foreach></script>"})
    @MapKey("name")
    Map<String, byte[]> findFilesByNamesList(@Param("fileNamesList") String[] fileNamesList);

    @Insert("INSERT INTO file(name, md5_sum, content, date) VALUES(#{name}, #{md5Sum}, #{content}, #{date})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void save(File file);

    @Update("UPDATE file SET name=#{name}, md5_sum=#{md5Sum}, content=#{content}, date=#{date} WHERE id=#{id}")
    void update(File file);
}
