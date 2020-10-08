package com.promineotech.elizabethannapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.elizabethannapi.entity.Design;
import com.promineotech.elizabethannapi.repository.DesignRepository;

@Service
public class DesignService {

	private static final Logger logger = LogManager.getLogger(DesignService.class);

	@Autowired
	private DesignRepository repo;

	public Iterable<Design> getDesign() {
		return repo.findAll();
	}

	public Design createDesign(Design design) {
		return repo.save(design);
	}

	public Design updateDesign(Design design, Long id) throws Exception {
		try {
			Design oldDesign = repo.findOne(id);
			oldDesign.setColor(design.getColor());
			oldDesign.setPattern(design.getPattern());
			oldDesign.setType(design.getType());
			return repo.save(oldDesign);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to update design: " + id, e);
			throw new Exception("Update design unsuccessful.");
		}
	}

	public void deleteDesign(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to delete design: " + id, e);
			throw new Exception("Delete design unsuccessful.");

		}
	}

}
