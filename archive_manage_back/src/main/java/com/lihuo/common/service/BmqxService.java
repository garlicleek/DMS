package com.lihuo.common.service;

import com.lihuo.common.po.BumenQuanxian;
import com.lihuo.common.vo.BmqxVo;
import com.lihuo.common.vo.Page;

import java.util.List;

public interface BmqxService {
    Page selBmqxPage(int page, int limit);

    BmqxVo selBmqxVoById(int id);

    boolean insBmqx(BumenQuanxian bmqx);

    boolean updBmqx(BumenQuanxian bmqx);

    boolean delBmqx(int id);

    List<BumenQuanxian> selBmqxListByBumenId(int bumen_id);
}
