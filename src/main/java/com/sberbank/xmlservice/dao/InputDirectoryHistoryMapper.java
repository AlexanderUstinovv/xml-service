package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.InputDirectoryHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InputDirectoryHistoryMapper {
    @Insert("INSERT INTO input_directory_history(message, date) VALUES(#{message}, #{date})")
    void insertHistory(InputDirectoryHistory inputDirectoryHistory);
}
