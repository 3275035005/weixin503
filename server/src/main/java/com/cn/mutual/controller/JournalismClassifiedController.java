package com.cn.mutual.controller;


import com.cn.mutual.entity.JournalismClassified;
import com.cn.mutual.service.JournalismClassifiedService;
import com.cn.mutual.utils.page.PageResult;
import com.cn.mutual.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  资讯分类
 * </p>
 */
@RestController
@RequestMapping("/journalism-classified")
public class JournalismClassifiedController {

    @Autowired
    private JournalismClassifiedService service;

    /**
     * 分页条件查询
     * @param page   当前页码
     * @param limit 每页的大小
     * @param data 封装查询条件数据
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public R getPageData(
            @PathVariable int page,
            @PathVariable int limit,
            @RequestBody JournalismClassified data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改数据
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody JournalismClassified data){
        service.updateById(data);
        return R.ok();
    }

    /**
     * 新增数据
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody JournalismClassified data){
        service.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        service.removeById(id);
        return R.ok();
    }


    /**
     * 查询所有分类
     * @param
     * @return
     */
    @GetMapping("getClassifiedAll")
    public R getClassifiedAll(){
        List<JournalismClassified> list = service.list(null);
        return R.ok().data("row", list);
    }

}

