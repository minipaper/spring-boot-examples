package io.github.minipaper.repository;

import io.github.minipaper.domain.DatabaseInfo;
import io.github.minipaper.support.ASchema;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@ASchema
@Mapper
@Repository
public interface AMapper {
  List<DatabaseInfo> findAll();

  DatabaseInfo findOne(Long seq);
}
