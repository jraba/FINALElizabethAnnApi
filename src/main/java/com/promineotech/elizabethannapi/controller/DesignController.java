package com.promineotech.elizabethannapi.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.elizabethannapi.service.DesignService;
import com.promineotech.elizabethannapi.service.OrderService;
import com.promineotech.elizabethannapi.entity.Design;

@RestController
@RequestMapping("product/{id}/design")
public class DesignController {
	
	private static final Logger logger = LogManager.getLogger(DesignController.class);

	@Autowired
	private DesignService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getDesign() {
		return new ResponseEntity<Object>(service.getDesign(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createDesign(@RequestBody Design design) {
		return new ResponseEntity<Object>(service.createDesign(design), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateDesign(@RequestBody Design design, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateDesign(design, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update design.", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteDesign(@PathVariable Long id) {
		try {
			service.deleteDesign(id);
			return new ResponseEntity<Object>("Successfully deleted design: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete design.", HttpStatus.BAD_REQUEST);
		}
	}

}