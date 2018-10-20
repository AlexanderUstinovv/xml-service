package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.FileDirectoryLink;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileDirectoryLinkMapper {
    @Insert("INSERT INTO file_directory_link(id_file, id_directory) VALUES(#{fileId}, #{directoryId})")
    void save(FileDirectoryLink fileDirectoryLink);
}
