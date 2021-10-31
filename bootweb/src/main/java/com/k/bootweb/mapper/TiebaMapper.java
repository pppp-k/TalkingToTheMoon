package com.k.bootweb.mapper;

import com.k.bootweb.pojo.dao.Tieba;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TiebaMapper {

    List<Tieba> queryTiebaList();

    List<Tieba> queryTiebaByLimit(Map<String,Integer> map);

    Tieba queryTiebaById(int id);

    int getTiebaListNum();

    int updateCommentCount(@Param("id")int id, @Param("commentCount")int commentCount);

    int deleteTieba(int id);

    int addTieba(Tieba tieba);

    int updateTieba(Tieba user);
}
