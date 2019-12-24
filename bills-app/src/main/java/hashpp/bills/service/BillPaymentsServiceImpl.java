package hashpp.bills.service;

import hashpp.bills.dto.BillPayments;
import hashpp.bills.repository.BillPaymentsRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BillPaymentsServiceImpl implements BillPaymentsService {

    @Autowired
    BillPaymentsRepository billPaymentsRepository;

    DozerBeanMapper mapper = new DozerBeanMapper();

    @PostConstruct
    private void init() {
        mapper.setMappingFiles(Arrays.asList("billpayments-dozer-mapping.xml"));
    }

    @Override
    public List<BillPayments> getAllBillPayments() {
        List<hashpp.bills.model.BillPayments> bpList = billPaymentsRepository.findAll();
        // transform
        List<BillPayments> bpDtoList = new ArrayList<>();
        for (hashpp.bills.model.BillPayments bp:bpList) {
            hashpp.bills.dto.BillPayments bpDto = mapper.map(bp, hashpp.bills.dto.BillPayments.class);
            bpDtoList.add(bpDto);
        }
        return bpDtoList;
    }

    @Override
    public BillPayments createBillPayment(BillPayments billPaymentsDto) {
        hashpp.bills.model.BillPayments bp = mapper.map(billPaymentsDto, hashpp.bills.model.BillPayments.class);
        hashpp.bills.model.BillPayments bpNew = billPaymentsRepository.save(bp);
        return mapper.map(bpNew, BillPayments.class);
    }

    @Override
    public Optional<BillPayments> getBillPaymentById(Integer billPaymentId) {
        Optional<hashpp.bills.model.BillPayments> bp = billPaymentsRepository.findById(billPaymentId);
        if(bp.isPresent()) {
            hashpp.bills.dto.BillPayments bpDto = mapper.map(bp.get(), hashpp.bills.dto.BillPayments.class);
            return Optional.of(bpDto);
        }
        return Optional.empty();
    }

    @Override
    public BillPayments updateBillPayments(BillPayments billPayments) {
        hashpp.bills.model.BillPayments billPayment = mapper.map(billPayments, hashpp.bills.model.BillPayments.class);
        hashpp.bills.model.BillPayments updatedBp = billPaymentsRepository.save(billPayment);
        return mapper.map(updatedBp, BillPayments.class);
    }

    @Override
    public void deleteBillPayments(Integer billPaymentId) {
        Optional<hashpp.bills.model.BillPayments> billPayments = billPaymentsRepository.findById(billPaymentId);

        billPaymentsRepository.delete(billPayments.get());

    }

}
