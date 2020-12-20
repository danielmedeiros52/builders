package com.builders.validation.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class SwaggerController {

  @GetMapping("/docs")
  public void redirectTodDocs(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html#/");
  }
}
