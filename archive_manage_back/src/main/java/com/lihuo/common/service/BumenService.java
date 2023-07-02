package com.lihuo.common.service;

import com.lihuo.common.po.Bumen;
import com.lihuo.common.vo.BumenVo;
import com.lihuo.common.vo.Page;

public interface BumenService {
    Page selBumenPage(int page, int limit, int dengji, int shangji_id);

    BumenVo selBumenVoById(int id);

    boolean insBumen(Bumen bumen);

    boolean updBumen(Bumen bumen);

    boolean delBumen(int id);
}
