package random.rbac.entity;

import random.support.BaseEntityClass;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DaoSui on 2015/10/17.
 */
@Entity
@Table(name = "rbac_role")
public class RbacRole extends BaseEntityClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(name = "role_name", length = 30)
    private String roleName;
    @Column(name = "role_description", length = 100)
    private String roleDescription;
    @Column(name = "main_page", length = 100)
    private String mainPage;
    @Column(name = "upper_role_id")
    private Long upperRoleId;

    public RbacRole() {
    }

    public RbacRole(Date createDate, Long creator, String isValid, String commonts, String roleName, String roleDescription, String mainPage, Long upperRoleId) {
        super(createDate, creator, isValid, commonts);
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.mainPage = mainPage;
        this.upperRoleId = upperRoleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public void setMainPage(String mainPage) {
        this.mainPage = mainPage;
    }

    public void setUpperRoleId(Long upperRoleId) {
        this.upperRoleId = upperRoleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public String getMainPage() {
        return mainPage;
    }

    public Long getUpperRoleId() {
        return upperRoleId;
    }
}
