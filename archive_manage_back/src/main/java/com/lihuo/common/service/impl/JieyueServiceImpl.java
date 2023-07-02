package com.lihuo.common.service.impl;

import com.lihuo.common.dao.ArchiveJieyueMapper;
import com.lihuo.common.po.ArchiveJieyue;
import com.lihuo.common.service.JieyueService;
import com.lihuo.common.vo.JieyueVo;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JieyueServiceImpl implements JieyueService {
    @Autowired
    private ArchiveJieyueMapper archiveJieyueMapper;

    @Override
    public Page selJieyuePage(int page, int limit) {
        String sql = "select a.*,b.mingcheng as archive,c.mingcheng as user from archive_jieyue a left join archive b on a.archive_id=b.id left join user c on a.user_id=c.id limit " + (page - 1) * limit + "," + limit;
        String cSql = "select count(id) from archive_jieyue";
        List<JieyueVo> list = this.archiveJieyueMapper.selJieyueVoList(sql);
        int count = this.archiveJieyueMapper.selInt(cSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public JieyueVo selJieyueVoById(int id) {
        String sql = "select a.*,b.mingcheng as archive,c.mingcheng as user from archive_jieyue a left join archive b on a.archive_id=b.id left join user c on a.user_id=c.id where a.id=" + id;
        JieyueVo jieyueVo = this.archiveJieyueMapper.selJIeyueVo(sql);
        return jieyueVo;
    }

    @Override
    public boolean insJieyue(ArchiveJieyue jieyue) {
        int rows = this.archiveJieyueMapper.insertSelective(jieyue);
        return rows > 0;
    }

    @Override
    public boolean updJieyue(ArchiveJieyue jieyue) {
        int rows = this.archiveJieyueMapper.updateByPrimaryKeySelective(jieyue);
        return rows > 0;
    }

    @Override
    public boolean delJieyue(int id) {
        int rows = this.archiveJieyueMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }
}
