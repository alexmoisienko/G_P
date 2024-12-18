package Home.G_P.Controller;

import Home.G_P.DatabaseWorker;
import Home.G_P.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MainController  {
    @Autowired
    DatabaseWorker worker;

    @GetMapping("/api/main")
    public ResponseEntity<?> GET_main(@RequestParam String login) {
        try {
            User user = worker.Select(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/main")
    public ResponseEntity<?> POST_main(@Valid @RequestBody User user) {
        return new ResponseEntity<>(worker.Insert(user), HttpStatus.OK);
    }

    @PostMapping("/api/main/map")
    public ResponseEntity<?> POST_main_map(@RequestBody Map<String, @NotEmpty String> map) {
        if (map.get("login") != null && map.get("password") != null && map.get("email") != null) {
            User user = new User(map.get("login"), map.get("password"), map.get("email"));
            return new ResponseEntity<>(worker.Insert(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("api/main/files")
    public ResponseEntity<?> GET_files(@RequestParam ) {}
}