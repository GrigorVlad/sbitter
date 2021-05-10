package me.grigor.sbitter.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/people", produces = MediaType.APPLICATION_JSON_VALUE)
public class PeopleRestControllerV1 {


}
