package cn.wolfcode.rbac.util;

import cn.wolfcode.rbac.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
public abstract class UserContext {

	public static final String USER_IN_SESSION = "USER_IN_SESSION";
	public static final String EXPRESSION_IN_SESSION = "EXPRESSION_IN_SESSION";

//	public static HttpSession getSession() {
//		ServletRequestAttributes attri = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//		return attri.getRequest().getSession();
//	}
//
//	public static void setCurrentUser(Employee employee) {
//		getSession().setAttribute(USER_IN_SESSION, employee);
//	}
//
//	public static Employee getCurrentUser() {
//		return (Employee)getSession().getAttribute(USER_IN_SESSION);
//	}
//
//	//session中存取用户权限
//	public static void setPermission(List<String> permissions) {
//		getSession().setAttribute(EXPRESSION_IN_SESSION, permissions);
//	}
//	//session中获取用户权限
//	public static List<String> getPermissions() {
//		return (List<String>) getSession().getAttribute(EXPRESSION_IN_SESSION);
//	}

	//获取session
	public static HttpSession getSession(){
		ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session=servletRequestAttributes.getRequest().getSession();
		return session;
	}

	public static void setEmployee(Employee employee){
		getSession().setAttribute(USER_IN_SESSION,employee);
	}

	public static void removeEmployee(){
		getSession().removeAttribute(USER_IN_SESSION);
	}

	public static Employee getEmployee(){
		Employee employee=(Employee) getSession().getAttribute(USER_IN_SESSION);
		return employee;
	}
}
