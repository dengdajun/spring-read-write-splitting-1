package liuzongzong.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by liuyz on 2016/12/25.
 */
public class User implements Serializable{
    public Integer id;
    public String mobile;
    public Date createDate;
    public Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
