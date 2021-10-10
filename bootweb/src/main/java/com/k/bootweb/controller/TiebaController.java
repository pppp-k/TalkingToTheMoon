package com.k.bootweb.controller;

import com.github.pagehelper.PageInfo;
import com.k.bootweb.pojo.dao.Tieba;
import com.k.bootweb.service.TiebaService;
import com.k.bootweb.service.TiebaServiceImpl;
import com.k.bootweb.pojo.dto.TiebaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("贴吧相关接口")
@RestController
@CrossOrigin(origins = "*")
public class TiebaController {
    @Autowired
    private TiebaService tiebaService;

    @GetMapping("/queryTiebaList")
    public List<Tieba> queryTiebaList(){
        List<Tieba> tiebaList = tiebaService.queryTiebaList();
        return tiebaList;
    }



    @ApiOperation("分页查询")
    @GetMapping("/queryTiebaByPage")
    public TiebaDto<Object> queryTiebaByPage3(Integer size, Integer page){
        TiebaDto<Object> p=tiebaService.findAllUserByPage1(page,size);
        return p;
    }


}
