package com.sws.base.Entity;

import com.sws.base.annotations.Column;
import com.sws.base.annotations.Entity;
import com.sws.base.annotations.GenerateValue;
import com.sws.base.annotations.Id;
import lombok.Data;

@Data
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

}
