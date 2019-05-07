package com.child.programming.base.model;

import java.util.Date;

public class TbStudentDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.guardian_name
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String guardianName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.guardian_phone
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String guardianPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.name
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.sex
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private Byte sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.age
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private Integer age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.address
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.email
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.photo_url
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String photoUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.openid
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String openid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.accent_token
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String accentToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.status
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.create_id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private Integer createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.create_time
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.last_update_id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private Integer lastUpdateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.last_update_time
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private Date lastUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.password
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.id
     *
     * @return the value of student.id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.id
     *
     * @param id the value for student.id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.guardian_name
     *
     * @return the value of student.guardian_name
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getGuardianName() {
        return guardianName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.guardian_name
     *
     * @param guardianName the value for student.guardian_name
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName == null ? null : guardianName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.guardian_phone
     *
     * @return the value of student.guardian_phone
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getGuardianPhone() {
        return guardianPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.guardian_phone
     *
     * @param guardianPhone the value for student.guardian_phone
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone == null ? null : guardianPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.name
     *
     * @return the value of student.name
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.name
     *
     * @param name the value for student.name
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.sex
     *
     * @return the value of student.sex
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.sex
     *
     * @param sex the value for student.sex
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.age
     *
     * @return the value of student.age
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.age
     *
     * @param age the value for student.age
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.address
     *
     * @return the value of student.address
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.address
     *
     * @param address the value for student.address
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.email
     *
     * @return the value of student.email
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.email
     *
     * @param email the value for student.email
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.photo_url
     *
     * @return the value of student.photo_url
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.photo_url
     *
     * @param photoUrl the value for student.photo_url
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.openid
     *
     * @return the value of student.openid
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.openid
     *
     * @param openid the value for student.openid
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.accent_token
     *
     * @return the value of student.accent_token
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getAccentToken() {
        return accentToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.accent_token
     *
     * @param accentToken the value for student.accent_token
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setAccentToken(String accentToken) {
        this.accentToken = accentToken == null ? null : accentToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.status
     *
     * @return the value of student.status
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.status
     *
     * @param status the value for student.status
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.create_id
     *
     * @return the value of student.create_id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.create_id
     *
     * @param createId the value for student.create_id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.create_time
     *
     * @return the value of student.create_time
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.create_time
     *
     * @param createTime the value for student.create_time
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.last_update_id
     *
     * @return the value of student.last_update_id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public Integer getLastUpdateId() {
        return lastUpdateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.last_update_id
     *
     * @param lastUpdateId the value for student.last_update_id
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setLastUpdateId(Integer lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.last_update_time
     *
     * @return the value of student.last_update_time
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.last_update_time
     *
     * @param lastUpdateTime the value for student.last_update_time
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.password
     *
     * @return the value of student.password
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.password
     *
     * @param password the value for student.password
     *
     * @mbggenerated Tue May 07 18:36:27 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}