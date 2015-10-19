package random.rbac.entity;

import random.support.BaseEntityClass;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by DaoSui on 2015/10/17.
 */
@Entity
@Table(name = "rbac_user")
public class RbacUser extends BaseEntityClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "user_name", length = 30)
    private String userName;
    @Column(name = "user_pwd", length = 60)
    private String userPwd;
    @Column(name = "real_name", length = 30)
    private String realName;
    @Column(name = "email", length = 30)
    private String email;
    @Column(name = "tel", length = 20)
    private String tel;
    @Column(name = "validate_date")
    private Date validateDate;
    @Column(name="activated", nullable = false)
    private boolean activated = false;

    public RbacUser() {
    }

    public RbacUser(String userName, String userPwd, String realName, String email, String tel, Date validateDate) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.realName = realName;
        this.email = email;
        this.tel = tel;
        this.validateDate = validateDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getRealName() {
        return realName;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean getActivated() {

        return activated;
    }
}
