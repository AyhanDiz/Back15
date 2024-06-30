package htwberlin.webtech.web;

import htwberlin.webtech.web.Beitrag;
import htwberlin.webtech.web.BeitragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beiträge")
public class BeitragController {

    @Autowired
    private BeitragRepository beitragRepository;

    @PostMapping
    public ResponseEntity<String> addFavorite(@RequestBody Beitrag beitrag) {
        beitragRepository.save(beitrag);
        return ResponseEntity.ok("Beitrag erfolgreich hinzugefügt");
    }

    @GetMapping
    public List<Beitrag> getFavorites() {
        return beitragRepository.findAll();
    }
}
