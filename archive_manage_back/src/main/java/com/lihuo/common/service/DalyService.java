package com.lihuo.common.service;

import com.lihuo.common.po.ArchiveLaiyuan;
import com.lihuo.common.vo.Page;

public interface DalyService {
    Page selDalyPage(int page, int limit);

    ArchiveLaiyuan selDalyById(int id);

    boolean insDaly(ArchiveLaiyuan archiveLaiyuan);

    boolean updDaly(ArchiveLaiyuan archiveLaiyuan);

    boolean delDaly(int id);
}
