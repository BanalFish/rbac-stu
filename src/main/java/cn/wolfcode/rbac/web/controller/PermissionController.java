package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.service.impl.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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


}
