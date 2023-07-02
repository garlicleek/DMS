package com.lihuo.common.service;

import com.lihuo.common.po.Archive;
import com.lihuo.common.vo.ArchiveVo;
import com.lihuo.common.vo.Page;

public interface DanganService {
    Page selArchivePage(int page, int limit);

    ArchiveVo selArchiveVoById(int id);

    boolean insArchive(Archive archive);

    boolean updArchive(Archive archive);

    boolean delArchive(int id);

    boolean updZhuangtai(int id, int zhuangtai);
}
