package com.lihuo.common.service.impl;

import com.lihuo.common.dao.BumenQuanxianMapper;
import com.lihuo.common.po.BumenQuanxian;
import com.lihuo.common.service.BmqxService;
import com.lihuo.common.vo.BmqxVo;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BmqxServiceImpl implements BmqxService {
    @Autowired
    private BumenQuanxianMapper bumenQuanxianMapper;

    @Override
    public Page selBmqxPage(int page, int limit) {
        String sql = "select a.*,b.mingcheng as bumen,c.mingcheng as quanxian from bumen_quanxian a left join bumen b on a.bumen_id=b.id left join quanxian c on a.quanxian_id=c.id limit " + (page - 1) * limit + "," + limit;
        String cSql = "select count(id) from bumen_quanxian";
        List<BmqxVo> list = this.bumenQuanxianMapper.selBmqxVoList(sql);
        int count = this.bumenQuanxianMapper.selInt(cSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public BmqxVo selBmqxVoById(int id) {
        String sql = "select a.*,b.mingcheng as bumen,c.mingcheng as quanxian from bumen_quanxian a left join bumen b on a.bumen_id=b.id left join quanxian c on a.quanxian_id=c.id where a.id=" + id;
        BmqxVo bmqxVo = this.bumenQuanxianMapper.selBmqxVo(sql);
        return bmqxVo;
    }

    @Override
    public boolean insBmqx(BumenQuanxian bmqx) {
        String sql = "select count(id) from bumen_quanxian where bumen_id=" + bmqx.getBumen_id() + " and quanxian_id=" + bmqx.getQuanxian_id();
        int count = this.bumenQuanxianMapper.selInt(sql);
        if (count == 0) {
            int rows = this.bumenQuanxianMapper.insertSelective(bmqx);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean updBmqx(BumenQuanxian bmqx) {
        String sql = "select count(id) from bumen_quanxian where bumen_id=" + bmqx.getBumen_id() + " and quanxian_id=" + bmqx.getQuanxian_id() + " and id!=" + bmqx.getId();
        int count = this.bumenQuanxianMapper.selInt(sql);
        if (count == 0) {
            int rows = this.bumenQuanxianMapper.updateByPrimaryKeySelective(bmqx);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean delBmqx(int id) {
        int rows = this.bumenQuanxianMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }

    @Override
    public List<BumenQuanxian> selBmqxListByBumenId(int bumen_id) {
        String sql = "select * from bumen_quanxian where bumen_id=" + bumen_id;
        List<BumenQuanxian> list = this.bumenQuanxianMapper.selBmqxList(sql);
        return list;
    }
}
