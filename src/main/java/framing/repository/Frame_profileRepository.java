package framing.repository;

import java.util.List;
import framing.entities.Frame_profile;

public class Frame_profileRepository extends CrudRepository<Frame_profile> {

    private static final String HIBERNATE_SELECT_QUERY = "from Author";

    public Frame_profile findOne(Long id) {
        return super.findOne(id, Frame_profile.class);
    }

    public List<Frame_profile> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Frame_profile.class);
    }
}
