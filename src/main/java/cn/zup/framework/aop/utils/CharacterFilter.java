package cn.zup.framework.aop.utils;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.zup.framework.aop.service.BusLog;

/**
 * 字符串特殊字符过滤器
 * @author Andot
 * @date 2017-12-18 08:27:03
 * 
 * */
@WebFilter(urlPatterns = "/*",filterName = "myfilter")
public class CharacterFilter  implements Filter {

	private static Logger log = Logger.getLogger(CharacterFilter.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	 public void doFilter(ServletRequest args0, ServletResponse args1,
	            FilterChain chain) throws IOException, ServletException {
		System.out.println("进入过滤器>>>>>>>>>>>");
        HttpServletRequest req=(HttpServletRequest)args0;
        HttpServletResponse res=(HttpServletResponse)args1;
         //获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        System.out.println("============================SQL"+sql);
        //有sql关键字，跳转到error.html
        if (sqlValidate(sql)) {
            throw new IOException("您发送请求中的参数中含有非法字符");
            //String ip = req.getRemoteAddr();
        } else {
            chain.doFilter(args0,args1);
        }
    }
     
    //效验
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|like'|and|exec|execute|insert|create|drop|sleep|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|--|++|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
