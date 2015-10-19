package random.rbac.entity;

import random.support.BaseEntityClass;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by DaoSui on 2015/10/17.
 */
@Entity
@Table(name = "rbac_permission")
public class RbacPermission extends BaseEntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long psId;
    @Column(name = "role_id")
    private Long rbacRoleId;
    @Column(name = "ps_name", length = 30)
    private String psName;
    @Column(name = "ps_description", length = 100)
    private String psDescription;
    @Column(name = "ps_group", length = 30)
    private String psGroup;
    @Column(name="ps_url", length = 100)
    private String psUrl;
    @Column(name="ps_type", length = 1)
    @NotNull
    private String psType = "1";

    public RbacPermission() {

    }

    public RbacPermission(Date createDate, Long creator, String isValid, String commonts, Long rbacRoleId, String psName, String psDescription, String psGroup, String psUrl) {
        super(createDate, creator, isValid, commonts);
        this.rbacRoleId = rbacRoleId;
        this.psName = psName;
        this.psDescription = psDescription;
        this.psGroup = psGroup;
        this.psUrl = psUrl;
    }

    public void setPsId(Long psId) {
        this.psId = psId;
    }

    public void setRbacRoleId(Long rbacRoleId) {
        this.rbacRoleId = rbacRoleId;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public void setPsDescription(String psDescription) {
        this.psDescription = psDescription;
    }

    public void setPsGroup(String psGroup) {
        this.psGroup = psGroup;
    }

    public void setPsUrl(String psUrl) {
        this.psUrl = psUrl;
    }

    public Long getPsId() {
        return psId;
    }

    public Long getRbacRoleId() {
        return rbacRoleId;
    }

    public String getPsName() {
        return psName;
    }

    public String getPsDescription() {
        return psDescription;
    }

    public String getPsGroup() {
        return psGroup;
    }

    public String getPsUrl() {
        return psUrl;
    }

    public String getPsType() {
        return psType;
    }

    public void setPsType(String psType) {
        this.psType = psType;
    }
}
