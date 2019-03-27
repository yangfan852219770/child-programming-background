package com.child.programming.base.model;

import java.util.Date;

public class TbStudentWorkDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.student_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Integer studentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.teacher_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Integer teacherId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.work_url
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private String workUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.work_name
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private String workName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.description
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.page_view
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private String pageView;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.collection_number
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Integer collectionNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.like_count
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private String likeCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.status
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.create_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Integer createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.create_time
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.last_update_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Integer lastUpdateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student_work.last_update_time
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    private Date lastUpdateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.id
     *
     * @return the value of student_work.id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.id
     *
     * @param id the value for student_work.id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.student_id
     *
     * @return the value of student_work.student_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.student_id
     *
     * @param studentId the value for student_work.student_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.teacher_id
     *
     * @return the value of student_work.teacher_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.teacher_id
     *
     * @param teacherId the value for student_work.teacher_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.work_url
     *
     * @return the value of student_work.work_url
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public String getWorkUrl() {
        return workUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.work_url
     *
     * @param workUrl the value for student_work.work_url
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setWorkUrl(String workUrl) {
        this.workUrl = workUrl == null ? null : workUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.work_name
     *
     * @return the value of student_work.work_name
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public String getWorkName() {
        return workName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.work_name
     *
     * @param workName the value for student_work.work_name
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setWorkName(String workName) {
        this.workName = workName == null ? null : workName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.description
     *
     * @return the value of student_work.description
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.description
     *
     * @param description the value for student_work.description
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.page_view
     *
     * @return the value of student_work.page_view
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public String getPageView() {
        return pageView;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.page_view
     *
     * @param pageView the value for student_work.page_view
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setPageView(String pageView) {
        this.pageView = pageView == null ? null : pageView.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.collection_number
     *
     * @return the value of student_work.collection_number
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Integer getCollectionNumber() {
        return collectionNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.collection_number
     *
     * @param collectionNumber the value for student_work.collection_number
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.like_count
     *
     * @return the value of student_work.like_count
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public String getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.like_count
     *
     * @param likeCount the value for student_work.like_count
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount == null ? null : likeCount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.status
     *
     * @return the value of student_work.status
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.status
     *
     * @param status the value for student_work.status
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.create_id
     *
     * @return the value of student_work.create_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.create_id
     *
     * @param createId the value for student_work.create_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.create_time
     *
     * @return the value of student_work.create_time
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.create_time
     *
     * @param createTime the value for student_work.create_time
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.last_update_id
     *
     * @return the value of student_work.last_update_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Integer getLastUpdateId() {
        return lastUpdateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.last_update_id
     *
     * @param lastUpdateId the value for student_work.last_update_id
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setLastUpdateId(Integer lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student_work.last_update_time
     *
     * @return the value of student_work.last_update_time
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student_work.last_update_time
     *
     * @param lastUpdateTime the value for student_work.last_update_time
     *
     * @mbggenerated Mon Mar 25 19:37:56 CST 2019
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}