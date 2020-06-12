package com.google.sps.data;

/** contains a comment . */
public final class Comment {

  private final long id;
  private final String content;
  private final long timestamp;

  public Comment(long id, String content, long timestamp) {
    this.id = id;
    this.content = content;
    this.timestamp = timestamp;
  }
}
