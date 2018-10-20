package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.FileHistory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileHistoryMapper {
    @Select("SELECT * FROM file_history WHERE id_file=#{fileId}")
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "fileId", column = "id_file"),
                    @Result(property = "messageLog", column = "message_log"),
                    @Result(property = "date", column = "date")
            }
    )
    List<FileHistory> getHistoryByFileId(long fileId);

    @Insert("INSERT INTO file_history(id_file, message_log, date) VALUES(#{fileId}, #{messageLog}, #{date})")
    void save(FileHistory fileHistory);
}

