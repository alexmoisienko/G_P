package Home.G_P.Controller;

import Home.G_P.DatabaseWorker;
import Home.G_P.FileWorker;
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

    @Autowired
    FileWorker fileWorker;

    @GetMapping("/api/main")
    public ResponseEntity<?> GET_main(@RequestParam String login) {
        try {
            User user = worker.Select(login);
            fileWorker.Write(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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

    @GetMapping("api/main/random_line")
    public ResponseEntity<?> GET_files() throws Exception {
        return new ResponseEntity<>(fileWorker.Read(), HttpStatus.OK);
    }
}