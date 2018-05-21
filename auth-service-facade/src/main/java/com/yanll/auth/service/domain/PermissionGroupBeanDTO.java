package com.yanll.auth.service.domain;

import com.yanll.framework.facade.domain.DTOEntity;

/*
* 当前文件为MybatisGenerator生成的DTO，请勿修改。
*/
public class PermissionGroupBeanDTO extends DTOEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_permission_group.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_permission_group.portal_id
     *
     * @mbg.generated
     */
    private Integer portalId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_permission_group.group_name
     *
     * @mbg.generated
     */
    private String groupName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_permission_group.enabled
     *
     * @mbg.generated
     */
    private Integer enabled;

    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_permission_group.id
     *
     * @return the value of m_permission_group.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_permission_group.id
     *
     * @param id the value for m_permission_group.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_permission_group.portal_id
     *
     * @return the value of m_permission_group.portal_id
     *
     * @mbg.generated
     */
    public Integer getPortalId() {
        return portalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_permission_group.portal_id
     *
     * @param portalId the value for m_permission_group.portal_id
     *
     * @mbg.generated
     */
    public void setPortalId(Integer portalId) {
        this.portalId = portalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_permission_group.group_name
     *
     * @return the value of m_permission_group.group_name
     *
     * @mbg.generated
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_permission_group.group_name
     *
     * @param groupName the value for m_permission_group.group_name
     *
     * @mbg.generated
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_permission_group.enabled
     *
     * @return the value of m_permission_group.enabled
     *
     * @mbg.generated
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_permission_group.enabled
     *
     * @param enabled the value for m_permission_group.enabled
     *
     * @mbg.generated
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}