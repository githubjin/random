package random.rbac.entity;

import random.support.BaseEntityClass;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DaoSui on 2015/10/17.
 */
@Entity
@Table(name = "rbac_user_role")
public class RbacUserRole extends BaseEntityClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private Long roleId;

    public RbacUserRole() {
    }

    public RbacUserRole(Date createDate, Long creator, String isValid, String commonts, Long userId, Long roleId) {
        super(createDate, creator, isValid, commonts);
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getRid() {
        return rid;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
