package com.lihuo.common.service;

import com.lihuo.common.po.ArchiveJieyue;
import com.lihuo.common.vo.JieyueVo;
import com.lihuo.common.vo.Page;

public interface JieyueService {
    Page selJieyuePage(int page, int limit);

    JieyueVo selJieyueVoById(int id);

    boolean insJieyue(ArchiveJieyue jieyue);

    boolean updJieyue(ArchiveJieyue jieyue);

    boolean delJieyue(int id);
}
