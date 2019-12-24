package hashpp.bills.repository;

import hashpp.bills.model.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Integer> {

    public Payee findByName(String name);
}
