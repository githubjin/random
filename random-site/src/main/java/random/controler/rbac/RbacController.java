package random.controler.rbac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import random.rbac.Model.RolePermissionResources;
import random.rbac.service.RbacService;
import random.support.JsonPController;

import java.util.List;
import java.util.Optional;

/**
 * Created by DaoSui on 2015/10/18.
 */

@JsonPController
@RequestMapping("rbac")
public class RbacController {

    @Autowired
    private RbacService rbacService;

    @RequestMapping(value = "permissionAndRoles")
    @ResponseBody
    public Optional<List<RolePermissionResources>> rbac(){
        Optional<List<RolePermissionResources>> list = rbacService.listAllPermissionsBindedToRoles();
        return list;
    }
}
