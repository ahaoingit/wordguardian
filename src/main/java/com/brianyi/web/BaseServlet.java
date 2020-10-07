package com.brianyi.web;

import com.brianyi.domain.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/*
* 所以servlet的父类，通过uri提取方法名进行反射
* */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            //通过用户请求的路径获取客户端提交数据,方法名
            String uri = request.getRequestURI();
            String methodName = uri.substring(uri.lastIndexOf("/") + 1);
            System.out.println("进入方法"+uri);
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //通过反射调用方法
            method.invoke(this, request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            Result result = new Result(  );
            result.setCode( Result.FAILS );
            result.setMessage(  ex.getMessage() );
            if (ex.getCause() != null) {
                result.setMessage(  ex.getCause().getMessage() );
            }
            response.getWriter().print( JSONObject.fromObject( result ) );
        }
    }

          //进行数据转JSON
        public void writeValue(HttpServletResponse response,Object obj)  {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json; charset=utf-8");
            try {
                mapper.writeValue(response.getOutputStream(),obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
