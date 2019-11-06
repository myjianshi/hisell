package edu.gyc.hisell.model;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 卖家信息表
 * </p>
 *
 * @author ls
 * @since 2019-11-06
 */
public class SellerInfo implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String username;

    private String password;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SellerInfo{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", openid=" + openid +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
