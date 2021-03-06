/*
 * Copyright (c) 2012. The Genome Analysis Centre, Norwich, UK
 * MISO project contacts: Robert Davey @ TGAC
 * *********************************************************************
 *
 * This file is part of MISO.
 *
 * MISO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MISO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MISO.  If not, see <http://www.gnu.org/licenses/>.
 *
 * *********************************************************************
 */

package uk.ac.bbsrc.tgac.miso.webapp.controller.rest;

import java.io.IOException;
import java.net.URI;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import uk.ac.bbsrc.tgac.miso.core.data.SampleNumberPerProject;
import uk.ac.bbsrc.tgac.miso.core.service.SampleNumberPerProjectService;
import uk.ac.bbsrc.tgac.miso.dto.Dtos;
import uk.ac.bbsrc.tgac.miso.dto.SampleNumberPerProjectDto;

@Controller
@RequestMapping("/rest")
@SessionAttributes("samplenumberperproject")
public class SampleNumberPerProjectController extends RestController {

  protected static final Logger log = LoggerFactory.getLogger(SampleNumberPerProjectController.class);

  @Autowired
  private SampleNumberPerProjectService sampleNumberPerProjectService;

  @GetMapping(value = "/samplenumberperproject/{id}", produces = { "application/json" })
  @ResponseBody
  public SampleNumberPerProjectDto getSampleNumberPerProject(@PathVariable("id") Long id, UriComponentsBuilder uriBuilder,
      HttpServletResponse response) throws IOException {
    SampleNumberPerProject sampleNumberPerProject = sampleNumberPerProjectService.get(id);
    if (sampleNumberPerProject == null) {
      throw new RestException("No sample number per project found with ID: " + id, Status.NOT_FOUND);
    } else {
      SampleNumberPerProjectDto dto = Dtos.asDto(sampleNumberPerProject);
      dto = writeUrls(dto, uriBuilder);
      return dto;
    }
  }

  private static SampleNumberPerProjectDto writeUrls(SampleNumberPerProjectDto sampleNumberPerProjectDto, UriComponentsBuilder uriBuilder) {

    URI baseUri = uriBuilder.build().toUri();
    sampleNumberPerProjectDto.setUrl(UriComponentsBuilder.fromUri(baseUri).path("/rest/samplenumberperproject/{id}")
        .buildAndExpand(sampleNumberPerProjectDto.getId()).toUriString());
    sampleNumberPerProjectDto.setCreatedByUrl(UriComponentsBuilder.fromUri(baseUri).path("/rest/user/{id}")
        .buildAndExpand(sampleNumberPerProjectDto.getCreatedById()).toUriString());
    sampleNumberPerProjectDto.setUpdatedByUrl(UriComponentsBuilder.fromUri(baseUri).path("/rest/user/{id}")
        .buildAndExpand(sampleNumberPerProjectDto.getUpdatedById()).toUriString());
    return sampleNumberPerProjectDto;
  }

  @GetMapping(value = "/samplenumberperprojects", produces = { "application/json" })
  @ResponseBody
  public Set<SampleNumberPerProjectDto> getSampleNumberPerProjects(UriComponentsBuilder uriBuilder, HttpServletResponse response) 
      throws IOException {
    Set<SampleNumberPerProject> sampleNumberPerProjects = sampleNumberPerProjectService.getAll();
    Set<SampleNumberPerProjectDto> sampleNumberPerProjectDtos = Dtos.asSampleNumberPerProjectDtos(sampleNumberPerProjects);
    for (SampleNumberPerProjectDto sampleNumberPerProjectDto : sampleNumberPerProjectDtos) {
      sampleNumberPerProjectDto = writeUrls(sampleNumberPerProjectDto, uriBuilder);
    }
    return sampleNumberPerProjectDtos;
  }

  @PostMapping(value = "/samplenumberperproject", headers = { "Content-type=application/json" })
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public SampleNumberPerProjectDto createSampleNumberPerProject(@RequestBody SampleNumberPerProjectDto sampleNumberPerProjectDto,
      UriComponentsBuilder b, HttpServletResponse response) throws IOException {
    SampleNumberPerProject sampleNumberPerProject = Dtos.to(sampleNumberPerProjectDto);
    Long id = sampleNumberPerProjectService.create(sampleNumberPerProject, sampleNumberPerProjectDto.getProjectId());
    return Dtos.asDto(sampleNumberPerProjectService.get(id));
  }

  @PutMapping(value = "/samplenumberperproject/{id}", headers = { "Content-type=application/json" })
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public SampleNumberPerProjectDto updateSampleNumberPerProject(@PathVariable("id") Long id,
      @RequestBody SampleNumberPerProjectDto sampleNumberPerProjectDto, HttpServletResponse response) throws IOException {
    SampleNumberPerProject sampleNumberPerProject = Dtos.to(sampleNumberPerProjectDto);
    sampleNumberPerProject.setId(id);
    sampleNumberPerProjectService.update(sampleNumberPerProject);
    return Dtos.asDto(sampleNumberPerProjectService.get(id));
  }

}