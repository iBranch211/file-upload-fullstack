package spring.files.upload.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

  @ApiModelProperty(notes = "The name of the file")
  private String name;

  @ApiModelProperty(notes = "The download url of the file")
  private String url;
}