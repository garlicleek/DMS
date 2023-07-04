package com.lihuo.common.service.impl;

import com.lihuo.common.dao.ArchiveHistoryMapper;
import com.lihuo.common.po.ArchiveHistory;
import com.lihuo.common.service.DalsService;
import com.lihuo.common.vo.ArchiveHistoryVo;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DalsServiceImpl implements DalsService {
    @Autowired
    private ArchiveHistoryMapper archiveHistoryMapper;

    @Override
    public Page selDalsPage(int page, int limit) {
        String sql = "select a.*,b.mingcheng as archive,c.mingcheng as user from archive_history a left join archive b on a.archive_id=b.id left join user c on a.user_id=c.id limit " + (page - 1) * limit + "," + limit;
        String cSql = "select count(id) from archive";
        List<ArchiveHistoryVo> list = this.archiveHistoryMapper.selArchiveHistoryVoList(sql);
        int count = this.archiveHistoryMapper.selInt(cSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public boolean insDals(ArchiveHistory archiveHistory) {
        int rows = this.archiveHistoryMapper.insertSelective(archiveHistory);
        return rows > 0;
    }
}
