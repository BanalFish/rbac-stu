package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public List<String> selectExpressions() {
        return permissionMapper.selectExpressions();
    }

    @Override
    public List<String> selectExpressionsByEmpId(Long EmpId) {
        return permissionMapper.selectExpressionsByEmpId(EmpId);
    }

    @Override
    public void insert(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public void insertBatch(List<Permission> permissions) {
        permissionMapper.insertBatch(permissions);
    }

    @Override
    public void deleteById(Long id) {
        permissionMapper.deleteById(id);
    }

    @Override
    public void reload() {
        //获取Controller
        //beansMap的key是controller名字，value是里面用到的Controller
        Map<String, Object> beansMap = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> values = beansMap.values();
        List<Permission> permissionList=new ArrayList<>();
        List<String> permissions=this.selectExpressions();


        for(Object controller:values){
            //获取controller的字节码
            Class<?> clazz = controller.getClass();
            //通过字节码获取controller里面的方法
            Method[] methods = clazz.getDeclaredMethods();

            for(Method method:methods){
                //获取方法上的注解
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
            this.insertBatch(permissionList);

        }

    }


}
