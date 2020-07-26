package spring.files.upload.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import spring.files.upload.message.ResponseMessage;
import spring.files.upload.model.FileInfo;
import spring.files.upload.service.FileStorageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@Api(value="/versions/1/files",produces ="application/json")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
@RequestMapping("/versions/1/files")
public class FileController {

  @NonNull FileStorageService storageService;

  @ApiOperation(value = "Upload a file")
  @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseMessage uploadFile(@ApiParam(name = "file", value = "Select the file to Upload", required = true) @RequestPart("file") MultipartFile file) {
    String message = "";
    try {
      storageService.save(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return new ResponseMessage(message);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      throw e;
    }
  }

  @ApiOperation(value = "Get List of Files",response = Iterable.class )
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FileInfo> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return fileInfos;
  }

  @ApiOperation(value = "Download a File")
  @GetMapping("/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}