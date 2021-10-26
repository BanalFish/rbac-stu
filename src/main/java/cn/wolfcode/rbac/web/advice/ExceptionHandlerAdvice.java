package cn.wolfcode.rbac.web.advice;

import cn.wolfcode.rbac.qo.JSONResult;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Spring MVC在这里会捕捉异常
@ControllerAdvice
public class ExceptionHandlerAdvice {

    //Model
    //Servlet
    //RuntimeException 参数
    //HandlerMethod method
    //目前处理方法 返回数据两大类 HTML JSON
    //原来处理方法响应什么类型数据，出异常还是还是返回直接前类型的数据
    @ExceptionHandler(RuntimeException.class)
    public String handlerException(HandlerMethod method,
                                   Model model,
                                   RuntimeException e,
                                   HttpServletResponse response) throws IOException {
        System.out.println("捕获异常 ==>" + "ExceptionHandlerAdvice");
        //打印错误信息
        e.printStackTrace();
        //方式1
        ResponseBody anno = method.getMethodAnnotation(ResponseBody.class);
        if (anno == null){ //响应html
            model.addAttribute("errorMsg", e.getMessage());
            return "/common/error";
        }else {//响应json
            //JSON.toJSONString()
            //MethodParameter returnType = method.getReturnType();
            JSONResult jsonResult = new JSONResult(false, e.getMessage());
            String jsonString = JSON.toJSONString(jsonResult);
            response.getWriter().write(jsonString);
            return null;
        }

    }

}
