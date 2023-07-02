package com.lihuo.common.service.impl;

import com.lihuo.common.dao.ArchiveLaiyuanMapper;
import com.lihuo.common.dao.ArchiveLaiyuanMapper;
import com.lihuo.common.po.ArchiveLaiyuan;
import com.lihuo.common.service.DalyService;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DalyServiceImpl implements DalyService {
    @Autowired
    private ArchiveLaiyuanMapper archiveLaiyuanMapper;

    @Override
    public Page selDalyPage(int page, int limit) {
        String sql = "select * from archive_laiyuan limit " + (page - 1) * limit + "," + limit;
        String countSql = "select count(id) from archive_laiyuan";
        List<ArchiveLaiyuan> list = this.archiveLaiyuanMapper.selArchiveLaiyuanList(sql);
        int count = this.archiveLaiyuanMapper.selInt(countSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public ArchiveLaiyuan selDalyById(int id) {
        ArchiveLaiyuan archiveLaiyuan = this.archiveLaiyuanMapper.selectByPrimaryKey(id);
        return archiveLaiyuan;
    }

    @Override
    public boolean insDaly(ArchiveLaiyuan archiveLaiyuan) {
        String sql = "select count(id) from archive_laiyuan where mingcheng='" + archiveLaiyuan.getMingcheng() + "'";
        int count = this.archiveLaiyuanMapper.selInt(sql);
        if (count == 0) {
            int rows = this.archiveLaiyuanMapper.insertSelective(archiveLaiyuan);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean updDaly(ArchiveLaiyuan archiveLaiyuan) {
        String sql = "select count(id) from archive_laiyuan where mingcheng='" + archiveLaiyuan.getMingcheng() + "' and id!=" + archiveLaiyuan.getId();
        int count = this.archiveLaiyuanMapper.selInt(sql);
        if (count == 0) {
            int rows = this.archiveLaiyuanMapper.updateByPrimaryKeySelective(archiveLaiyuan);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean delDaly(int id) {
        int rows = this.archiveLaiyuanMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }
}
