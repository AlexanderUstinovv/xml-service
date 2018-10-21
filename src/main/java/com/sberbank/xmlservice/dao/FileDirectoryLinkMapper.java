package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.FileDirectoryLink;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileDirectoryLinkMapper {
    @Insert("INSERT INTO file_directory_link(id_file, id_directory) VALUES(#{fileId}, #{directoryId})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void save(FileDirectoryLink fileDirectoryLink);
}
