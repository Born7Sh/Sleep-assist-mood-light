package org.kpu.mood.controller;



import org.kpu.mood.domain.AuthRequest;
import org.kpu.mood.domain.UserVO;
import org.kpu.mood.security.JwtUtil;
import org.kpu.mood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@ComponentScan(basePackages = {"org.kpu.mood.service","org.kpu.mood.security"})
public class WelcomeController {
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
//	@GetMapping("/")
//	public String welcome() {
//	return "Welcome to javatechie !!";
//	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("invalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}
	
	@PostMapping("/signup")
	public String signup(@RequestBody UserVO userVO) throws Exception{
		userService.signup(userVO);
		return "OK";
	}
}
