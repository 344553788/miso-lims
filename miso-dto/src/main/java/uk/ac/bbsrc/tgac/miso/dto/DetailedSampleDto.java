package uk.ac.bbsrc.tgac.miso.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailedSampleDto extends SampleDto {

  private Long parentId;
  private String parentAlias;
  private Long parentTissueSampleClassId;
  private Long sampleClassId;
  private String sampleClassAlias;
  private String sampleCategory;
  private Long detailedQcStatusId;
  private String detailedQcStatusNote;
  private Long subprojectId;
  private String subprojectAlias;
  private Boolean subprojectPriority;
  private String groupId;
  private String groupDescription;
  private Boolean isSynthetic;
  private boolean nonStandardAlias;
  private Long identityId;
  private String identityConsentLevel;
  private String externalNames;
  private String effectiveExternalNames;
  private String effectiveGroupId;
  private String effectiveGroupIdSample;
  private String creationDate;

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getParentAlias() {
    return parentAlias;
  }

  public void setParentAlias(String parentAlias) {
    this.parentAlias = parentAlias;
  }

  public Long getParentTissueSampleClassId() {
    return parentTissueSampleClassId;
  }

  public void setParentTissueSampleClassId(Long parentSampleClassId) {
    this.parentTissueSampleClassId = parentSampleClassId;
  }

  public Long getDetailedQcStatusId() {
    return detailedQcStatusId;
  }

  @JsonInclude(JsonInclude.Include.ALWAYS)
  public void setDetailedQcStatusId(Long detailedQcStatusId) {
    this.detailedQcStatusId = detailedQcStatusId;
  }

  public Long getSubprojectId() {
    return subprojectId;
  }

  public void setSubprojectId(Long subprojectId) {
    this.subprojectId = subprojectId;
  }

  public String getSubprojectAlias() {
    return subprojectAlias;
  }

  public void setSubprojectAlias(String subprojectAlias) {
    this.subprojectAlias = subprojectAlias;
  }

  public Boolean getSubprojectPriority() {
    return subprojectPriority;
  }

  public void setSubprojectPriority(Boolean subprojectPriority) {
    this.subprojectPriority = subprojectPriority;
  }

  public Long getSampleClassId() {
    return sampleClassId;
  }

  public void setSampleClassId(Long sampleClassId) {
    this.sampleClassId = sampleClassId;
  }

  public String getSampleClassAlias() {
    return sampleClassAlias;
  }

  public void setSampleClassAlias(String sampleClassAlias) {
    this.sampleClassAlias = sampleClassAlias;
  }

  public String getSampleCategory() {
    return sampleCategory;
  }

  public void setSampleCategory(String sampleCategory) {
    this.sampleCategory = sampleCategory;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getGroupDescription() {
    return groupDescription;
  }

  public void setGroupDescription(String groupDescription) {
    this.groupDescription = groupDescription;
  }

  public Boolean getSynthetic() {
    return isSynthetic;
  }

  public void setSynthetic(Boolean isSynthetic) {
    this.isSynthetic = isSynthetic;
  }

  public boolean getNonStandardAlias() {
    return nonStandardAlias;
  }

  public void setNonStandardAlias(boolean nonStandardAlias) {
    this.nonStandardAlias = nonStandardAlias;
  }

  public String getDetailedQcStatusNote() {
    return detailedQcStatusNote;
  }

  public void setDetailedQcStatusNote(String detailedQcStatusNote) {
    this.detailedQcStatusNote = detailedQcStatusNote;
  }

  public Long getIdentityId() {
    return identityId;
  }

  public void setIdentityId(Long identityId) {
    this.identityId = identityId;
  }

  public String getIdentityConsentLevel() {
    return identityConsentLevel;
  }

  public void setIdentityConsentLevel(String identityConsentLevel) {
    this.identityConsentLevel = identityConsentLevel;
  }

  public String getEffectiveGroupId() {
    return effectiveGroupId;
  }

  public void setEffectiveGroupId(String effectiveGroupId) {
    this.effectiveGroupId = effectiveGroupId;
  }

  public String getEffectiveGroupIdSample() {
    return effectiveGroupIdSample;
  }

  public void setEffectiveGroupIdSample(String effectiveGroupIdSample) {
    this.effectiveGroupIdSample = effectiveGroupIdSample;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

 public String getExternalNames() {
    return externalNames;
  }

  public void setExternalNames(String externalNames) {
    this.externalNames = externalNames;
  }

  public String getEffectiveExternalNames() {
    return effectiveExternalNames;
  }

  public void setEffectiveExternalNames(String effectiveExternalNames) {
    this.effectiveExternalNames = effectiveExternalNames;
  }

}
