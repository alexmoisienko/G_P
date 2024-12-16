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

    @GetMapping("/api/main")
    public ResponseEntity<?> GET_main(@RequestParam String login) {

        DatabaseWorker worker = new DatabaseWorker();

        try {
            User user = worker.Select(login);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        } else {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
    }

    @PostMapping("/api/main")
    public ResponseEntity<?> POST_main(@Valid @RequestBody User user) {

        DatabaseWorker worker = new DatabaseWorker();

        return new ResponseEntity<>(worker.Insert(user), HttpStatus.OK);

    }

    @PostMapping("/api/main/map")
    public ResponseEntity<?> POST_main_map(@RequestBody Map<String, @NotEmpty String> map) {

        DatabaseWorker worker = new DatabaseWorker();

        if (map.get("login") != null && map.get("password") != null && map.get("email") != null) {
            User user = new User(map.get("login"), map.get("password"), map.get("email"));
            return new ResponseEntity<>(worker.Insert(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}