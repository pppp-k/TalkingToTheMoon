package com.k.bootweb.service;

import com.k.bootweb.mapper.TiebaMapper;
import com.k.bootweb.pojo.dao.Tieba;
import com.k.bootweb.pojo.dto.TiebaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TiebaServiceImpl implements TiebaService{
    @Autowired
    private TiebaMapper tiebaMapper;

    @Override
    public List<Tieba> queryTiebaList() {
        return tiebaMapper.queryTiebaList();
    }


    public TiebaDto<Object> findAllUserByPage1(int pageNum, int pageSize){
        int sum=tiebaMapper.getTiebaListNum();
        int maxpage=(int)Math.ceil(sum*1.0/pageSize);
        int i=sum-(pageNum-1)*pageSize;
        int size= i>pageSize ? pageSize : i;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        int start,end;
        //start为偏移量，end为查询个数
        start=(pageNum-1)*pageSize;
        end=pageSize;
        map.put("start",start);
        map.put("end",end);
        if(pageNum<1||pageNum>maxpage){
            return TiebaDto.failed(null,"查询页数越界",maxpage);
        }
        else{
            return TiebaDto.ok(tiebaMapper.queryTiebaByLimit(map),"查询成功",maxpage,size);
        }

    }

    public int updateCommentCount(int id, int commentCount) {
        return tiebaMapper.updateCommentCount(id, commentCount);
    }
}
