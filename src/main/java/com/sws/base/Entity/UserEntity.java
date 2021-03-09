package com.sws.base.Entity;

import com.sws.base.annotations.Column;
import com.sws.base.annotations.Entity;
import com.sws.base.annotations.IgnoreColumn;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.List;

@Entity("sys_user")
public class UserEntity {

    //用户ID
    @Column("user_id")
    private Integer userId;

    @IgnoreColumn
    private Integer roleId;
    //部门ID
    @Column("dept_id")
    private Integer deptId;
    //登录账号
    @Column("login_name")
    private String loginName;
    //用户昵称
    @Column("user_name")
    private String userName;
    //用户类型（00系统用户 01注册用户）
    @Column("user_type")
    private String userType;
    //用户邮箱
    private String email;
    //手机号码
    private String phonenumber;
    //用户性别（0男 1女 2未知）
    private Integer sex;
    //头像路径
    private String avatar;
    //密码
    private String password;
    //盐加密
    private String salt;
    //帐号状态（0正常 1停用）
    private String status;
    //删除标志（0代表存在 2代表删除）
    @Column("del_flag")
    private Integer delFlag;
    //最后登陆IP
    @Column("login_ip")
    private String loginIp;
    //最后登陆时间
    @Column("login_date")
    private String loginDate;
    //创建者
    @Column("create_by")
    private String createBy;
    //创建时间
    @Column("create_time")
    private String createTime;
    //更新者
    @Column("update_by")
    private String updateBy;
    //更新时间
    @Column("update_time")
    private String updateTime;
    //更新时间
    @IgnoreColumn
    private String projectIds;
    //更新时间
    @IgnoreColumn
    private String projectNames;
    //备注
    private String remark;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
