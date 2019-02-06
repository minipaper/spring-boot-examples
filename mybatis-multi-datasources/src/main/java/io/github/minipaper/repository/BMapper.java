package io.github.minipaper.repository;

import io.github.minipaper.domain.DatabaseInfo;
import io.github.minipaper.support.BSchema;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@BSchema
@Mapper
@Repository
public interface BMapper {
  List<DatabaseInfo> findAll();

  DatabaseInfo findOne(Long seq);
}
