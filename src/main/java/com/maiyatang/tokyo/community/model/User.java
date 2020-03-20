package com.maiyatang.tokyo.community.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ID
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.NAME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ACCOUNT_ID
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.TOKEN
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.CREATE_TIME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.MODIFIED_TIME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    private Long modifiedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.AVATAR_URL
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ID
     *
     * @return the value of USER.ID
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ID
     *
     * @param id the value for USER.ID
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.NAME
     *
     * @return the value of USER.NAME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.NAME
     *
     * @param name the value for USER.NAME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ACCOUNT_ID
     *
     * @return the value of USER.ACCOUNT_ID
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ACCOUNT_ID
     *
     * @param accountId the value for USER.ACCOUNT_ID
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.TOKEN
     *
     * @return the value of USER.TOKEN
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.TOKEN
     *
     * @param token the value for USER.TOKEN
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.CREATE_TIME
     *
     * @return the value of USER.CREATE_TIME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.CREATE_TIME
     *
     * @param createTime the value for USER.CREATE_TIME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.MODIFIED_TIME
     *
     * @return the value of USER.MODIFIED_TIME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public Long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.MODIFIED_TIME
     *
     * @param modifiedTime the value for USER.MODIFIED_TIME
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.AVATAR_URL
     *
     * @return the value of USER.AVATAR_URL
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.AVATAR_URL
     *
     * @param avatarUrl the value for USER.AVATAR_URL
     *
     * @mbg.generated Fri Mar 20 15:43:03 JST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}