package com.lihuo.common.service;

import com.lihuo.common.po.Quanxian;
import com.lihuo.common.vo.Page;

public interface QuanxianService {
    Page selQuanxianPage(int page, int limit);

    Quanxian selQuanxianById(int id);

    boolean insQuanxian(Quanxian quanxian);

    boolean updQuanxian(Quanxian quanxian);

    boolean delQuanxian(int id);
}
