package com.fnic.mybatis.thingsboard.model;

public class Asset {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column asset.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column asset.additional_info
     *
     * @mbg.generated
     */
    private String additionalInfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column asset.customer_id
     *
     * @mbg.generated
     */
    private String customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column asset.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column asset.search_text
     *
     * @mbg.generated
     */
    private String searchText;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column asset.tenant_id
     *
     * @mbg.generated
     */
    private String tenantId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column asset.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column asset.id
     *
     * @return the value of asset.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column asset.id
     *
     * @param id the value for asset.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column asset.additional_info
     *
     * @return the value of asset.additional_info
     *
     * @mbg.generated
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column asset.additional_info
     *
     * @param additionalInfo the value for asset.additional_info
     *
     * @mbg.generated
     */
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo == null ? null : additionalInfo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column asset.customer_id
     *
     * @return the value of asset.customer_id
     *
     * @mbg.generated
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column asset.customer_id
     *
     * @param customerId the value for asset.customer_id
     *
     * @mbg.generated
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column asset.name
     *
     * @return the value of asset.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column asset.name
     *
     * @param name the value for asset.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column asset.search_text
     *
     * @return the value of asset.search_text
     *
     * @mbg.generated
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column asset.search_text
     *
     * @param searchText the value for asset.search_text
     *
     * @mbg.generated
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText == null ? null : searchText.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column asset.tenant_id
     *
     * @return the value of asset.tenant_id
     *
     * @mbg.generated
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column asset.tenant_id
     *
     * @param tenantId the value for asset.tenant_id
     *
     * @mbg.generated
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column asset.type
     *
     * @return the value of asset.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column asset.type
     *
     * @param type the value for asset.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table asset
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Asset other = (Asset) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAdditionalInfo() == null ? other.getAdditionalInfo() == null : this.getAdditionalInfo().equals(other.getAdditionalInfo()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSearchText() == null ? other.getSearchText() == null : this.getSearchText().equals(other.getSearchText()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table asset
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAdditionalInfo() == null) ? 0 : getAdditionalInfo().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSearchText() == null) ? 0 : getSearchText().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }
}