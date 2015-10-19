package random.support;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by DaoSui on 2015/10/18.
 */
@MappedSuperclass
public class BaseEntityClass implements Serializable {

    @Column(name = "create_date")
    private Date createDate;
    @Column(name="creator")
    private Long creator;
    @Column(name = "isvalid", length = 1)
    private String isValid;
    @Column(name = "commonts", length = 100)
    private String commonts;

    public BaseEntityClass() {
        super();
    }

    public BaseEntityClass(Date createDate, Long creator, String isValid, String commonts) {
        this.createDate = createDate;
        this.creator = creator;
        this.isValid = isValid;
        this.commonts = commonts;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Long getCreator() {
        return creator;
    }

    public String getIsValid() {
        return isValid;
    }

    public String getCommonts() {
        return commonts;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public void setCommonts(String commonts) {
        this.commonts = commonts;
    }
}
