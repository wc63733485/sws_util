package com.sws.base.Entity;

import com.sws.base.annotations.Column;
import com.sws.base.annotations.Entity;
import com.sws.base.annotations.GenerateValue;
import com.sws.base.annotations.Id;

@Entity
public class BaseEntity {

    @Id
    @GenerateValue
    private Long id;
    @Column("create_user_id")
    private Integer createUserId;
    @Column("update_user_id")
    private Integer updateUserId;
    @Column("update_time")
    private String updateTime;
    @Column("create_time")
    private String createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
