package uk.ac.bbsrc.tgac.miso.webapp.controller.rest;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import uk.ac.bbsrc.tgac.miso.core.data.ChangeLog;
import uk.ac.bbsrc.tgac.miso.core.data.Study;
import uk.ac.bbsrc.tgac.miso.core.util.PaginatedDataSource;
import uk.ac.bbsrc.tgac.miso.core.util.PaginationFilter;
import uk.ac.bbsrc.tgac.miso.dto.ChangeLogDto;
import uk.ac.bbsrc.tgac.miso.dto.DataTablesResponseDto;
import uk.ac.bbsrc.tgac.miso.dto.Dtos;
import uk.ac.bbsrc.tgac.miso.dto.StudyDto;
import uk.ac.bbsrc.tgac.miso.service.ChangeLogService;
import uk.ac.bbsrc.tgac.miso.service.StudyService;

@Controller
@RequestMapping("/rest/study")
public class StudyRestController extends RestController {
  private final JQueryDataTableBackend<Study, StudyDto> jQueryBackend = new JQueryDataTableBackend<Study, StudyDto>() {
    @Override
    protected StudyDto asDto(Study model) {
      return Dtos.asDto(model);
    }

    @Override
    protected PaginatedDataSource<Study> getSource() throws IOException {
      return studyService;
    }
  };

  @Autowired
  private StudyService studyService;
  @Autowired
  private ChangeLogService changeLogService;

  @GetMapping(value = "/dt", produces = "application/json")
  @ResponseBody
  public DataTablesResponseDto<StudyDto> dataTable(HttpServletRequest request, HttpServletResponse response,
      UriComponentsBuilder uriBuilder) throws IOException {
    return jQueryBackend.get(request, response, uriBuilder);
  }

  @GetMapping(value = "/dt/project/{id}", produces = "application/json")
  @ResponseBody
  public DataTablesResponseDto<StudyDto> dataTableByProject(@PathVariable("id") Long id, HttpServletRequest request,
      HttpServletResponse response,
      UriComponentsBuilder uriBuilder) throws IOException {
    return jQueryBackend.get(request, response, uriBuilder, PaginationFilter.project(id));
  }

  @GetMapping(value = "/{studyId}")
  public @ResponseBody StudyDto get(@PathVariable Long studyId) throws IOException {
    return Dtos.asDto(studyService.get(studyId));
  }

  @DeleteMapping(value = "/{id}", produces = "application/json")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id, HttpServletRequest request,
      HttpServletResponse response,
      UriComponentsBuilder uriBuilder) throws IOException {
    Study study = studyService.get(id);
    if (study == null) {
      throw new RestException("Study not found.", Status.NOT_FOUND);
    }
    studyService.delete(study);
  }

  @GetMapping(produces = "application/json")
  public @ResponseBody List<StudyDto> list() throws IOException {
    return studyService.list(0, 0, true, "id").stream().map(Dtos::asDto).collect(Collectors.toList());
  }

  @GetMapping(value = "/changes", produces = "application/json")
  public @ResponseBody List<ChangeLogDto> getChanges() throws IOException {
    Collection<ChangeLog> changeLogs = changeLogService.listAll("Study");
    return changeLogs.stream().map(Dtos::asDto).collect(Collectors.toList());
  }
}
