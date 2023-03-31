package ovh.zain.thetennistracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovh.zain.thetennistracker.entity.Tennis;
import ovh.zain.thetennistracker.entity.TennisSet;
import ovh.zain.thetennistracker.repository.TennisMatchRepository;
import ovh.zain.thetennistracker.repository.TennisSetRepository;

import java.util.List;

@Service
public class TennisMatchService {

    @Autowired
    private TennisMatchRepository tennisMatchRepository;
    @Autowired
    private TennisSetRepository tennisSetRepository;

    public Tennis save(Tennis match) {
        tennisMatchRepository.save(match);

        match.getTennisSets().forEach(set -> {
            set.setMatchId(match.getId());
            tennisSetRepository.save(set);
        });
        return match;
    }

    public List<Tennis> findAll() {
        return tennisMatchRepository.findAll();
    }

    public List<Tennis> findAll(long creatorId) {
        return tennisMatchRepository.findAll().stream().filter(m -> m.getCreatorId() == creatorId).toList();
    }

    public String update(long id, Tennis match) {
        Tennis tennisEntity = tennisMatchRepository.findById(id).orElse(null);
        if (tennisEntity != null) {
            if (match.getTennisSets() != null) {

                for (TennisSet set : tennisEntity.getTennisSets()) {
                    tennisSetRepository.deleteById(set.getId());
                }

                match.getTennisSets().forEach(set -> {
                    set.setMatchId(id);
                    tennisSetRepository.save(set);
                });
            }
            return "Match updated";
        } else {
            return "Match not found";
        }
    }

    public String update(long creatorId, long id, Tennis match) {
        Tennis found = findById(creatorId, id);
        if (found.getCreatorId() != creatorId) {
            return "You are not the creator of this match";
        }
        return update(id, match);
    }

    public Tennis findById(long id) {
        return tennisMatchRepository.findById(id).orElse(null);
    }

    public Tennis findById(long creatorId, long id) {
        Tennis m = findById(id);
        if (m != null && m.getCreatorId() == creatorId) {
            return m;
        }
        return null;
    }

    public void deleteById(long id) {
        Tennis m = findById(id);
        if (m != null) {
            for (TennisSet set : m.getTennisSets()) {
                tennisSetRepository.deleteById(set.getId());
            }
        }
        tennisMatchRepository.deleteById(id);
    }

    public void deleteById(long creatorId, long id) {
        Tennis m = findById(creatorId, id);
        if (m != null && m.getCreatorId() == creatorId) {
            deleteById(id);
        }
    }
}
