package esprit.tunisiacamp.auth;


import esprit.tunisiacamp.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

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
    return service.authenticate(request);
  }


}
