package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.DirectoryHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface DirectoryHistoryMapper {
    @Select("SELECT * FROM directory_history where id_directory = #{id}")
    DirectoryHistory getHistoryByDirectoryId(long id);

    @Insert("INSERT INTO directory_history(message_log, date) VALUES(#{messageLog}, #{date})")
    void save(DirectoryHistory directoryHistory);
}
