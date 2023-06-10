package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.AuthRequest;
import pi.enset.entities.AuthRespense;
import pi.enset.services.IAuth;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final IAuth auth;

    @PostMapping("/login")
    public AuthRespense login(@RequestBody AuthRequest authRequest) {
        log.info("AuthController.login()...");
        return auth.login(authRequest.getUsername(), authRequest.getPassword());
    }
    @GetMapping("/logout/{id}")
    public void logout( @PathVariable Long id) {
        log.info("AuthController.logout()...");
        auth.logout(id);
    }
}
