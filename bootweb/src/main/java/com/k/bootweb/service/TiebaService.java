package com.k.bootweb.service;

import com.k.bootweb.pojo.dao.Tieba;
import com.k.bootweb.pojo.dto.TiebaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TiebaService {

    List<Tieba> queryTiebaList();


    TiebaDto<Object> findAllUserByPage1(int pageNum, int pageSize);

}
