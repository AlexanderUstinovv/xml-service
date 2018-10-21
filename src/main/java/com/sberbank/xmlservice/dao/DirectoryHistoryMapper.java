package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.DirectoryHistory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DirectoryHistoryMapper {
    @Select("SELECT * FROM directory_history where id_directory = #{id}")
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "directoryId", column = "id_directory"),
                    @Result(property = "messageLog", column = "message_log"),
                    @Result(property = "date", column = "date")
            }
    )
    DirectoryHistory findHistoryByDirectoryId(long id);

    @Insert("INSERT INTO directory_history(message_log, date) VALUES(#{messageLog}, #{date})")
    void save(DirectoryHistory directoryHistory);
}
