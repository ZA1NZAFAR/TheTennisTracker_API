package ovh.zain.thetennistracker.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ovh.zain.thetennistracker.entity.Tennis;
import ovh.zain.thetennistracker.service.TennisMatchService;

import java.util.List;

@RequestMapping("/api/tracker")
@RestController
public class TennisMatchController {

    @Autowired
    private TennisMatchService tennisMatchService;


    @PostMapping
    public Tennis createMatch(@RequestBody Tennis match) {
        return this.tennisMatchService.save(match);
    }

    @PostMapping("/{creatorId}")
    public Tennis createMatch(@PathVariable long creatorId, @RequestBody Tennis match) {
        match.setCreatorId(creatorId);
        return this.tennisMatchService.save(match);
    }


    @PutMapping("/{id}")
    public String updateMatch(@PathVariable long id, @RequestBody Tennis match) {
        return tennisMatchService.update(id, match);
    }

    @GetMapping("/{creatorId}/{id}")
    public Tennis getMatch(@PathVariable long creatorId, @PathVariable long id) {
        return tennisMatchService.findById(creatorId, id);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable long id) {
        tennisMatchService.deleteById(id);
    }

    @PutMapping("/{creatorId}/{id}")
    public String updateMatch(@PathVariable long creatorId, @PathVariable long id, @RequestBody Tennis match) {
        return tennisMatchService.update(creatorId, id, match);
    }

    @DeleteMapping("/{creatorId}/{id}")
    public void deleteMatch(@PathVariable long creatorId, @PathVariable long id) {
        tennisMatchService.deleteById(creatorId, id);
    }

    @GetMapping
    public List<Tennis> getAll() {
        System.out.println("Get all matches");
        return tennisMatchService.findAll();
    }

    @GetMapping("/{creatorId}")
    public List<Tennis> getAll(@PathVariable long creatorId) {
        return tennisMatchService.findAll(creatorId);
    }

}

