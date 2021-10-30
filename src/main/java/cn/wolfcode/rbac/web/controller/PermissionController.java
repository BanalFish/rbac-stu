package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.qo.JSONResult;
import cn.wolfcode.rbac.service.impl.PermissionService;
import cn.wolfcode.rbac.util.RequiredPermission;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;



    @RequestMapping("/list")
    public ModelAndView list(){
        List<Permission> permissionList = permissionService.selectAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("permissions",permissionList);
        mv.setViewName("/permission/list");
        return mv;
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        permissionService.deleteById(id);
        return "redirect:/permission/list";
    }

    @RequestMapping("/reload")
    @ResponseBody
    public JSONResult reload(){
        try{
            permissionService.reload();
        }catch(Exception e){
            e.printStackTrace();
            new JSONResult(false,"fail");
        }



        return new JSONResult(true,"success");
    }

}
