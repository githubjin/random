package random.rbac.Model;

import java.io.Serializable;

/**
 * Created by DaoSui on 2015/10/18.
 */
public class RolePermissionResources implements Serializable {
//    role_name,p.ps_url,p.is_need_certified
    private String roleName;
    private String psUrl;
    private String psType;

    public RolePermissionResources() {
    }

    public RolePermissionResources(String roleName, String psUrl, String psType) {
        this.roleName = roleName;
        this.psUrl = psUrl;
        this.psType = psType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPsUrl() {
        return psUrl;
    }

    public void setPsUrl(String psUrl) {
        this.psUrl = psUrl;
    }

    public String getPsType() {
        return psType;
    }

    public void setPsType(String psType) {
        this.psType = psType;
    }
}
