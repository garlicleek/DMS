package com.lihuo.common.service;

import com.lihuo.common.po.ArchiveHistory;
import com.lihuo.common.vo.Page;

public interface DalsService {
    Page selDalsPage(int page, int limit);

    boolean insDals(ArchiveHistory archiveHistory);
}
