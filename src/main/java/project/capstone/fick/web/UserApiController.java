package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.web.dto.user.UserLoginRequestDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    @GetMapping("/api/user")
    public ResponseEntity<UserLoginRequestDto> loginRequest(@RequestParam String name, @RequestParam Integer UID) {
        UserLoginRequestDto dto = new UserLoginRequestDto();
        dto.setName(name);
        dto.setUID(UID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
