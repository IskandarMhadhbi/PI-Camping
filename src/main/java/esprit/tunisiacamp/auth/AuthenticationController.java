package esprit.tunisiacamp.auth;


import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
  @Autowired
  UserRepository userRepository;
  private final AuthenticationService service;
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
          @RequestBody User request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

    @PostMapping("/authenticate")
  public Authentication authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    User user = userRepository.getUserByUsername(request.getEmail());
    user.setLastCnx(new Date());
    userRepository.save(user);
    return service.authenticate(request);
  }


}
