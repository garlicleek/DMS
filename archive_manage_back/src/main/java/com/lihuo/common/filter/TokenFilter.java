package com.lihuo.common.filter;

import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lihuo.common.dao.QuanxianMapper;
import com.lihuo.common.po.BumenQuanxian;
import com.lihuo.common.po.Quanxian;
import com.lihuo.common.po.User;
import com.lihuo.common.service.BmqxService;
import com.lihuo.common.service.UserService;
import com.lihuo.common.vo.BmqxVo;
import com.lihuo.common.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebFilter(urlPatterns = "/*", filterName = "tokenFilter")
@CrossOrigin
public class TokenFilter implements Filter {
    @Value("${jwt.key}")
    private String key;
    private final static String AUTH_HEADER = "Authorization";
    private final static String AUTH_HEADER_TYPE = "Bearer";

    @Autowired
    private UserService userService;
    @Autowired
    private BmqxService bmqxService;
    @Autowired
    private QuanxianMapper quanxianMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!httpServletRequest.getMethod().equals("OPTIONS")) {
            String authHeader = httpServletRequest.getHeader(AUTH_HEADER);
            String uri = httpServletRequest.getRequestURI();
            if (uri.contains("login") || uri.contains("file")) {
                chain.doFilter(request, response);
                return;
            }
            if (Objects.isNull(authHeader) || !authHeader.startsWith(AUTH_HEADER_TYPE)) {
                PrintWriter writer = null;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                try {
                    Object obj = JSONObject.toJSON(new Message("token验证失败", 0, null));
                    writer = response.getWriter();
                    writer.print(obj);
                } catch (IOException e) {
                } finally {
                    if (writer != null)
                        writer.close();
                }
                return;
            }

            String authToken = authHeader.split(" ")[1];
            if (!JWTUtil.verify(authToken, key.getBytes(StandardCharsets.UTF_8))) {
                PrintWriter writer = null;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                try {
                    Object obj = JSONObject.toJSON(new Message("token验证失败", 0, null));
                    writer = response.getWriter();
                    writer.print(obj);
                } catch (IOException e) {
                } finally {
                    if (writer != null)
                        writer.close();
                }
                return;
            }

            final String userName = (String) JWTUtil.parseToken(authToken).getPayload("username");
            User user = this.userService.selUserByUsername(userName);
            if (user == null) {
                PrintWriter writer = null;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                try {
                    Object obj = JSONObject.toJSON(new Message("token验证失败", 0, null));
                    writer = response.getWriter();
                    writer.print(obj);
                } catch (IOException e) {
                } finally {
                    if (writer != null)
                        writer.close();
                }
                return;
            } else {
                if (user.getRole() == 0) {
                    chain.doFilter(request, response);
                    return;
                } else if (user.getRole() == 1) {
                    int bumen_id = user.getBumen_id();
                    List<BumenQuanxian> list = this.bmqxService.selBmqxListByBumenId(bumen_id);
                    String quanxiandh = "";
                    for (int i = 0; i < list.size(); i++) {
                        quanxiandh += list.get(i).getQuanxian_id();
                        if (i < list.size() - 1) {
                            quanxiandh += ",";
                        }
                    }
                    String quanxianSql = "select * from quanxian where id in (" + quanxiandh + ")";
                    List<Quanxian> quanxianList = this.quanxianMapper.selQuanxianList(quanxianSql);
                    for (Quanxian quanxian :
                            quanxianList) {
                        if (uri.contains(quanxian.getJiekou())) {
                            chain.doFilter(request, response);
                            return;
                        }
                    }
                    PrintWriter writer = null;
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    try {
                        Object obj = JSONObject.toJSON(new Message("无权访问", 0, null));
                        writer = response.getWriter();
                        writer.print(obj);
                    } catch (IOException e) {
                    } finally {
                        if (writer != null)
                            writer.close();
                    }
                    return;
                } else {
                    if (uri.contains("/dangan/selDanganPage")||uri.contains("/dangan/updJy")) {
                        chain.doFilter(request, response);
                        return;
                    } else {
                        PrintWriter writer = null;
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json; charset=utf-8");
                        try {
                            Object obj = JSONObject.toJSON(new Message("无权访问", 0, null));
                            writer = response.getWriter();
                            writer.print(obj);
                        } catch (IOException e) {
                        } finally {
                            if (writer != null)
                                writer.close();
                        }
                        return;
                    }
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
