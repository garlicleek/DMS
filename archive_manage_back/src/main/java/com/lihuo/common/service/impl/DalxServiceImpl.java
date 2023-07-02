package com.lihuo.common.service.impl;

import com.lihuo.common.dao.ArchiveLeixingMapper;
import com.lihuo.common.po.ArchiveLeixing;
import com.lihuo.common.service.DalxService;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DalxServiceImpl implements DalxService {
    @Autowired
    private ArchiveLeixingMapper archiveLeixingMapper;

    @Override
    public Page selDalxPage(int page, int limit) {
        String sql = "select * from archive_leixing limit " + (page - 1) * limit + "," + limit;
        String countSql = "select count(id) from archive_leixing";
        List<ArchiveLeixing> list = this.archiveLeixingMapper.selArchiveLeixingList(sql);
        int count = this.archiveLeixingMapper.selInt(countSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public ArchiveLeixing selDalxById(int id) {
        ArchiveLeixing archiveLeixing = this.archiveLeixingMapper.selectByPrimaryKey(id);
        return archiveLeixing;
    }

    @Override
    public boolean insDalx(ArchiveLeixing archiveLeixing) {
        String sql = "select count(id) from archive_leixing where mingcheng='" + archiveLeixing.getMingcheng() + "'";
        int count = this.archiveLeixingMapper.selInt(sql);
        if (count == 0) {
            int rows = this.archiveLeixingMapper.insertSelective(archiveLeixing);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean updDalx(ArchiveLeixing archiveLeixing) {
        String sql = "select count(id) from archive_leixing where mingcheng='" + archiveLeixing.getMingcheng() + "' and id!=" + archiveLeixing.getId();
        int count = this.archiveLeixingMapper.selInt(sql);
        if (count == 0) {
            int rows = this.archiveLeixingMapper.updateByPrimaryKeySelective(archiveLeixing);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean delDalx(int id) {
        int rows = this.archiveLeixingMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }
}
