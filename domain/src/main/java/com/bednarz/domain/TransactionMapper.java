package com.bednarz.domain;

import com.bednarz.domain.transaction.Transaction;
import com.bednarz.ports.model.OrderPort;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {
    Transaction orderInToTransaction(OrderPort orderPort);
    OrderPort transactionToOrderPort(Transaction transaction);

//    OrderPortOut transactionToOrderOut(Transaction transaction);

}
