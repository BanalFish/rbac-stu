package cn.wolfcode.rbac.web.interceptor;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PermissionInterceptor implements HandlerInterceptor {


    @Autowired
    private IPermissionService permissionService;

    /**
     * 放行：1. 超级管理员  2. 方法上无注解  3. 当前用户有访问权限   返回true
     * 不放行：其他   返回false,并且跳转到无权限的页面
     *
     * 1.获取用户信息
     * 2.判断是否放行
     * 3.返回值
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof DefaultServletHttpRequestHandler){
            return true;
        }

        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("USER_IN_SESSION");
        HandlerMethod handlerMethod= (HandlerMethod) handler;

        //1.
        if (employee!=null && employee.getAdmin()!=null && employee.getAdmin()) {
            return true;
        }
        //2.
        RequiredPermission anno = handlerMethod.getMethodAnnotation(RequiredPermission.class);

        if(anno==null){
            return true;
        }
        //3.
        List<String> expressions = permissionService.selectExpressionsByEmpId(employee.getId());
        if(expressions.contains(anno.expression())){
            return true;
        }

        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp")
                .forward(request,response);
        return false;
    }

}
