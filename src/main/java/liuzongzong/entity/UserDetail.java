package liuzongzong.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by liuyz on 2016/12/25.
 */
public class UserDetail implements Serializable{
    public Integer id;
    public Integer userId;
    public String password;
    public Date createDate;
    public Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
