package com.yanll.business.auth.domain;

import com.yanll.framework.data.mysql.domain.VOEntity;

/*
* 当前文件为MybatisGenerator自动生成的VO，请手动剪切到*-service项目。
*/
public class MenuBeanVO extends VOEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_menu.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_menu.parent_id
     *
     * @mbg.generated
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_menu.url
     *
     * @mbg.generated
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_menu.menu_name
     *
     * @mbg.generated
     */
    private String menuName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_menu.idx
     *
     * @mbg.generated
     */
    private Long idx;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_menu.icon
     *
     * @mbg.generated
     */
    private String icon;

    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_menu.id
     *
     * @return the value of m_menu.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_menu.id
     *
     * @param id the value for m_menu.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_menu.parent_id
     *
     * @return the value of m_menu.parent_id
     *
     * @mbg.generated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_menu.parent_id
     *
     * @param parentId the value for m_menu.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_menu.url
     *
     * @return the value of m_menu.url
     *
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_menu.url
     *
     * @param url the value for m_menu.url
     *
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_menu.menu_name
     *
     * @return the value of m_menu.menu_name
     *
     * @mbg.generated
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_menu.menu_name
     *
     * @param menuName the value for m_menu.menu_name
     *
     * @mbg.generated
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_menu.idx
     *
     * @return the value of m_menu.idx
     *
     * @mbg.generated
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_menu.idx
     *
     * @param idx the value for m_menu.idx
     *
     * @mbg.generated
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_menu.icon
     *
     * @return the value of m_menu.icon
     *
     * @mbg.generated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_menu.icon
     *
     * @param icon the value for m_menu.icon
     *
     * @mbg.generated
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}