package com.sberbank.xmlservice.dao;

import com.sberbank.xmlservice.domain.XmlFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface XmlFileMapper {
    @Insert("INSERT INTO xml_file(name, date) VALUES(#{name}, #{date})")
    void insertXmlFile(XmlFile xmlFile);
}
