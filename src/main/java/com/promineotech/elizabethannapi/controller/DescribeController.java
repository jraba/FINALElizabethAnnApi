package com.promineotech.elizabethannapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/describe")
public class DescribeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, String> describe() {
		String description = "Hi! I'm Elizabeth Ann, but most people call me Lizzie. "
				+ "I've been sewing for years on end, starting off with sewing aprons "
				+ "and bags. However, I'm drawn to sewing rag quilts! I love anything soft "
				+ "and snuggly, which is exactly what these quilts are. Besides sewing, I love "
				+ "really good tacos, a nice cold Dr. Pepper and spending some good quality time with family! " 
				+ "The Story Behind The Lori Original" 
				+ "The idea of sewing these beautiful rag quilts was "
				+ "inspired by our dear family friend Lori Udy. My mom and I fell "
				+ "totally in love with them, and she was kind enough to teach us how to sew them. "
				+ "They are even more dear to my heart, after Lori passed away following her inspiring and "
				+ "courageous battle with breast cancer years ago. These quilts help remind us to be bold, "
				+ "fearless, kind, and not to take life too seriously."
				+ "The other quilts mentioned are also named after other influential "
				+ "seamstresses in my life. The Renee, is named after my Mom. I owe all "
				+ "I know about sewing to that woman! The Bonnie, is named after my grandmother."
				+ " Although I never had the opportunity to meet her, I feel like I've come to know and "
				+ "understand her love and creative genius through the hours I spend on my machine.";
		Map<String, String> result = new HashMap<>();
		result.put("message", description);
		return result;
	}
}
