package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.qo.JSONResult;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private IEmployeeService iEmployeeService;


    @RequestMapping("/userLogin")
    @ResponseBody
    public JSONResult userlogin(String username, String password, HttpServletRequest httpServletRequest){
       Employee employee= iEmployeeService.selectByNameAndPass(username,password);
        HttpSession httpSession=httpServletRequest.getSession();
        httpSession.setAttribute("USER_IN_SESSION",employee);
       if(employee!=null){
           return new JSONResult(true,"success");
       }
       return new JSONResult(false,"fail");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session= request.getSession();
        session.removeAttribute("USER_IN_SESSION");

        return "redirect:/login.html";
    }

}
