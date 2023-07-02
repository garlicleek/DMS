package com.lihuo.common.service;

import com.lihuo.common.po.ArchiveLeixing;
import com.lihuo.common.vo.Page;

public interface DalxService {
    Page selDalxPage(int page, int limit);

    ArchiveLeixing selDalxById(int id);

    boolean insDalx(ArchiveLeixing archiveLeixing);

    boolean updDalx(ArchiveLeixing archiveLeixing);

    boolean delDalx(int id);
}
