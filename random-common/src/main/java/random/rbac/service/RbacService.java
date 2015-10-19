package random.rbac.service;

import org.elasticsearch.common.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import random.rbac.Model.RolePermissionResources;
import random.rbac.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by DaoSui on 2015/10/18.
 */
@Service
@Transactional(readOnly = true)
public class RbacService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询所有角色可查看的资源
     * @return
     */
    public Optional<List<RolePermissionResources>> listAllPermissionsBindedToRoles(){
        return userRepository.listAllPermissionsBindedToRoles();
    }

}
