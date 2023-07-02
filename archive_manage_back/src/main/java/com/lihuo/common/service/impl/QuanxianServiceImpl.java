package com.lihuo.common.service.impl;

import com.lihuo.common.dao.QuanxianMapper;
import com.lihuo.common.po.Quanxian;
import com.lihuo.common.service.QuanxianService;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuanxianServiceImpl implements QuanxianService {
    @Autowired
    private QuanxianMapper quanxianMapper;

    @Override
    public Page selQuanxianPage(int page, int limit) {
        String sql = "select * from quanxian limit " + (page - 1) * limit + "," + limit;
        String cSql = "select count(id) from quanxian";
        List<Quanxian> list = this.quanxianMapper.selQuanxianList(sql);
        int count = this.quanxianMapper.selInt(cSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public Quanxian selQuanxianById(int id) {
        Quanxian quanxian = this.quanxianMapper.selectByPrimaryKey(id);
        return quanxian;
    }

    @Override
    public boolean insQuanxian(Quanxian quanxian) {
        String sql = "select count(id) from quanxian where jiekou='" + quanxian.getJiekou() + "'";
        int count = this.quanxianMapper.selInt(sql);
        if (count == 0) {
            int rows = this.quanxianMapper.insertSelective(quanxian);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean updQuanxian(Quanxian quanxian) {
        String sql = "select count(id) from quanxian where jiekou='" + quanxian.getJiekou() + "' and id!=" + quanxian.getId();
        int count = this.quanxianMapper.selInt(sql);
        if (count == 0) {
            int rows = this.quanxianMapper.updateByPrimaryKeySelective(quanxian);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean delQuanxian(int id) {
        int rows = this.quanxianMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }
}
