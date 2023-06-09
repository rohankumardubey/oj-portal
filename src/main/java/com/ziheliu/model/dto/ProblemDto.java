package com.ziheliu.model.dto;

import javax.validation.constraints.NotNull;

public class ProblemDto {
  private Integer id;

  @NotNull(message = "title is required")
  private String title;

  @NotNull(message = "description is required")
  private String description;

  @NotNull(message = "language is required")
  private String language;

  @NotNull(message = "memory is required")
  private int memory;

  @NotNull(message = "timeout is required")
  private int timeout;

  private int score;

  @NotNull(message = "enable is required")
  private boolean enable;

  @NotNull(message = "createTimestamp is required")
  private long createTimestamp;

  @NotNull(message = "updateTimestamp is required")
  private long updateTimestamp;

  public ProblemDto() {}

  public ProblemDto(Integer id, String title, String description, String language, int memory, int timeout, int score, boolean enable, long createTimestamp, long updateTimestamp) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.language = language;
    this.memory = memory;
    this.timeout = timeout;
    this.score = score;
    this.enable = enable;
    this.createTimestamp = createTimestamp;
    this.updateTimestamp = updateTimestamp;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public int getMemory() {
    return memory;
  }

  public void setMemory(int memory) {
    this.memory = memory;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  public long getCreateTimestamp() {
    return createTimestamp;
  }

  public void setCreateTimestamp(long createTimestamp) {
    this.createTimestamp = createTimestamp;
  }

  public long getUpdateTimestamp() {
    return updateTimestamp;
  }

  public void setUpdateTimestamp(long updateTimestamp) {
    this.updateTimestamp = updateTimestamp;
  }
}
