package hashpp.bills.repository;

import hashpp.bills.model.BillPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPaymentsRepository extends JpaRepository<BillPayments, Integer> {

}
