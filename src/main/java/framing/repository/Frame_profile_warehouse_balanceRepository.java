package framing.repository;

import java.util.List;
import framing.entities.Frame_profile_warehouse_balance;

public class Frame_profile_warehouse_balanceRepository extends CrudRepository<Frame_profile_warehouse_balance> {

    private static final String HIBERNATE_SELECT_QUERY = "from Book";

    public Frame_profile_warehouse_balance findOne(Long id) {
        return super.findOne(id, Frame_profile_warehouse_balance.class);
    }

    public List<Frame_profile_warehouse_balance> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Frame_profile_warehouse_balance.class);
    }
}
