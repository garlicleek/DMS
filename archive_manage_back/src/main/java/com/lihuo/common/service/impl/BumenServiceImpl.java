package com.lihuo.common.service.impl;

import com.lihuo.common.dao.BumenMapper;
import com.lihuo.common.po.Bumen;
import com.lihuo.common.service.BumenService;
import com.lihuo.common.vo.BumenVo;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BumenServiceImpl implements BumenService {
    @Autowired
    private BumenMapper bumenMapper;

    @Override
    public Page selBumenPage(int page, int limit, int dengji, int shangji_id) {
        if (dengji == -1 && shangji_id == -1) {
            String listSql = "select a.*,b.mingcheng as shangji from bumen a left join bumen b on a.shangji_id=b.id limit " + (page - 1) * limit + "," + limit;
            String countSql = "select count(id) from bumen";
            List<BumenVo> list = this.bumenMapper.selBumenVoList(listSql);
            int count = this.bumenMapper.selInt(countSql);
            return new Page("查询成功", count, list);
        } else if (dengji == 3 && shangji_id == -1) {
            String listSql = "select a.*,b.mingcheng as shangji from bumen a left join bumen b on a.shangji_id=b.id where a.dengji=" + dengji + " limit " + (page - 1) * limit + "," + limit;
            String countSql = "select count(id) from bumen where dengji=" + dengji;
            List<BumenVo> list = this.bumenMapper.selBumenVoList(listSql);
            int count = this.bumenMapper.selInt(countSql);
            return new Page("查询成功", count, list);
        }
        String listSql = "select a.*,b.mingcheng as shangji from bumen a left join bumen b on a.shangji_id=b.id where a.dengji=" + dengji + " and a.shangji_id=" + shangji_id + " limit " + (page - 1) * limit + "," + limit;
        String countSql = "select count(id) from bumen where dengji=" + dengji + " and shangji_id=" + shangji_id;
        List<BumenVo> list = this.bumenMapper.selBumenVoList(listSql);
        int count = this.bumenMapper.selInt(countSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public BumenVo selBumenVoById(int id) {
        String sql = "select a.*,b.mingcheng as shangji from bumen a left join bumen b on a.shangji_id=b.id where a.id=" + id;
        BumenVo bumenVo = this.bumenMapper.selBumenVo(sql);
        return bumenVo;
    }

    @Override
    public boolean insBumen(Bumen bumen) {
        String sql = "select count(id) from bumen where mingcheng='" + bumen.getMingcheng() + "'";
        int count = this.bumenMapper.selInt(sql);
        if (count == 0) {
            int rows = this.bumenMapper.insertSelective(bumen);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean updBumen(Bumen bumen) {
        String sql = "select count(id) from bumen where mingcheng='" + bumen.getMingcheng() + "' and id!=" + bumen.getId();
        int count = this.bumenMapper.selInt(sql);
        if (count == 0) {
            int rows = this.bumenMapper.updateByPrimaryKeySelective(bumen);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean delBumen(int id) {
        int rows = this.bumenMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }
}