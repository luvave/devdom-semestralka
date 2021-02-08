package org.devdom.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Base rest endpoint, just for checking if everything works
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping(value = "/")
public class BaseController {
	
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String healthCheck() {
        return "CONNECTION TO API SUCCESFULL";
    }
}
