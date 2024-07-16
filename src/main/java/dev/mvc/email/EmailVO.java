package dev.mvc.email;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class EmailVO {
  private String to;
  private String title;
  private String message;
}
