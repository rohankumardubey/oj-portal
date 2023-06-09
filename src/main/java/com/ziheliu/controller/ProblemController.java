package com.ziheliu.controller;

import com.ziheliu.exception.TesterConfigException;
import com.ziheliu.model.dto.MessageDto;
import com.ziheliu.model.dto.ProblemDto;
import com.ziheliu.security.HasRole;
import com.ziheliu.service.ProblemService;
import com.ziheliu.utils.ZipUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/problems")
@HasRole("BASIC")
public class ProblemController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProblemController.class);

  private final ProblemService problemService;

  @Autowired
  public ProblemController(ProblemService problemService) {
    this.problemService = problemService;
  }

  @HasRole("ADMIN")
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> create(@Valid ProblemDto problemDto,
                                  @RequestParam(value = "tester") MultipartFile tester) {
    Map<String, byte[]> testerMap;
    try {
      testerMap = ZipUtils.uncompress(tester.getInputStream());
      int score = problemService.validTesterZip(testerMap);
      problemDto.setScore(score);

      problemDto = problemService.createProblem(problemDto);
      problemService.writeProblem2Disk(testerMap, problemDto);
    } catch (IOException | TesterConfigException e) {
      LOGGER.error(e.getMessage());
      return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.ok(problemDto);
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<ProblemDto> getList() {
    return problemService.getProblemList();
  }

  @RequestMapping(path = "/{problemId}", method = RequestMethod.GET)
  public ProblemDto get(@PathVariable("problemId") int problemId) {
    return problemService.getProblemById(problemId);
  }
}
