package random.rbac.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import random.support.BaseEntityClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Size(min = 2, max = 5)
    @Column(name = "lang_key", length = 5)
    private String langKey;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "reset_date", nullable = true)
    private DateTime resetDate = null;

    public RbacUser() {
    }

    public RbacUser(Date createDate, Long creator, String isValid, String commonts, String userName, String userPwd, String realName, String email, String tel, Date validateDate, boolean activated, String langKey, String activationKey, String resetKey, DateTime resetDate) {
        super(createDate, creator, isValid, commonts);
        this.userName = userName;
        this.userPwd = userPwd;
        this.realName = realName;
        this.email = email;
        this.tel = tel;
        this.validateDate = validateDate;
        this.activated = activated;
        this.langKey = langKey;
        this.activationKey = activationKey;
        this.resetKey = resetKey;
        this.resetDate = resetDate;
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

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public DateTime getResetDate() {
        return resetDate;
    }

    public void setResetDate(DateTime resetDate) {
        this.resetDate = resetDate;
    }
}
