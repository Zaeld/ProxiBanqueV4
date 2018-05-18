package fr.gtm.pbsi.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestService {

	@GetMapping({ "", "/" })
	String read() {
		return "Ce test fonctionne !";
	}

}
