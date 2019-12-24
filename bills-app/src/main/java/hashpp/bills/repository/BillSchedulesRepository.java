package hashpp.bills.repository;

import hashpp.bills.model.BillSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillSchedulesRepository extends JpaRepository<BillSchedules, Long> {

}
