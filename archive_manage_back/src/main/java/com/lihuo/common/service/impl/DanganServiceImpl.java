package com.lihuo.common.service.impl;

import com.lihuo.common.dao.ArchiveMapper;
import com.lihuo.common.po.Archive;
import com.lihuo.common.service.DanganService;
import com.lihuo.common.vo.ArchiveVo;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DanganServiceImpl implements DanganService {
    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    public Page selArchivePage(int page, int limit) {
        String sql = "select a.*,b.mingcheng as leixing,c.mingcheng as laiyuan from archive a left join archive_leixing b on a.leixing_id=b.id left join archive_laiyuan c on a.laiyuan_id=c.id limit " + (page - 1) * limit + "," + limit;
        List<ArchiveVo> list = this.archiveMapper.selArchiveVoList(sql);
        String countSql = "select count(id) from archive";
        int count = this.archiveMapper.selInt(countSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public ArchiveVo selArchiveVoById(int id) {
        String sql = "select a.*,b.mingcheng as leixing,c.mingcheng as laiyuan from archive a left join archive_leixing b on a.leixing_id=b.id left join archive_laiyuan c on a.laiyuan_id=c.id where id=" + id;
        ArchiveVo archiveVo = this.archiveMapper.selArchiveVo(sql);
        return archiveVo;
    }

    @Override
    public boolean insArchive(Archive archive) {
        int rows = this.archiveMapper.insertSelective(archive);
        return rows > 0;
    }

    @Override
    public boolean updArchive(Archive archive) {
        int rows = this.archiveMapper.updateByPrimaryKeySelective(archive);
        return rows > 0;
    }

    @Override
    public boolean delArchive(int id) {
        int rows = this.archiveMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }

    @Override
    public boolean updZhuangtai(int id, int zhuangtai) {
        String sql = "update archive set zhuangtai=" + zhuangtai + " where id=" + id;
        int rows = this.archiveMapper.updData(sql);
        return rows > 0;
    }
}
