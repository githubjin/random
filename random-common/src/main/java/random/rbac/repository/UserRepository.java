package random.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import random.rbac.Model.RolePermissionResources;
import random.rbac.entity.RbacUser;

import java.util.List;
import java.util.Optional;

/**
 * Created by DaoSui on 2015/10/18.
 * java jpa repository for User entity
 */
@Repository
public interface UserRepository extends JpaRepository<RbacUser, Long> {

    Optional<RbacUser> findOneByUserName(String userName);

    @Query(nativeQuery = true, value = "SELECT role_name FROM rbac_user_role ur,rbac_role r WHERE ur.user_id=:userId AND ur.role_id=r.role_id")
    List<String> listUserAuthoritis(@Param("userId") Long userId);

    @Query(value = "SELECT r.role_name,p.ps_url,p.ps_type FROM rbac_permission p,rbac_role r WHERE p.role_id=r.role_id", nativeQuery = true)
    Optional<List<RolePermissionResources>> listAllPermissionsBindedToRoles();

}
