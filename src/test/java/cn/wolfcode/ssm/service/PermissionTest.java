package cn.wolfcode.ssm.service;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.service.impl.DepartmentServiceImpl;
import cn.wolfcode.rbac.service.impl.PermissionService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mvc.xml")
public class PermissionTest {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 需求：获取控制器里面的方法贴有注解的方法里的属性，插入数据库
     *
     * 1. 获取容器中的Controller
     * 2. 获取里面的方法
     * 2. 判断里面的方法是否贴有注解，没有贴注解的随便访问
     * 3. 获取注释里的属性
     * 4. 插入数据库
     */

    @Test
    public void testReload() throws NoSuchMethodException {
        Map<String, Object> beansMap = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> values = beansMap.values();
        List<Permission> permissionList=new ArrayList<>();
        List<String> permissions=permissionService.selectExpressions();


        for(Object controller:values){
            Class<?> clazz = controller.getClass();
            Method[] methods = clazz.getDeclaredMethods();

            for(Method method:methods){
                RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                if(annotation!=null){
                    String name = annotation.name();
                    String expression = annotation.expression();

                    //避免插入重复的权限
                    if(!permissions.contains(expression)){
                        Permission permission=new Permission();
                        permission.setName(name);
                        permission.setExpression(expression);
                        System.out.println("插入数据->"+permission);
                        permissionList.add(permission);
                    }
                }
            }
        }

        if(permissionList!=null && permissionList.size()>0){
            permissionService.insertBatch(permissionList);

        }
    }

}
