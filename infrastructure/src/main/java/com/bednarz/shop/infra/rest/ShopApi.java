package com.bednarz.shop.infra.rest;

import com.bednarz.ports.CustomerDataUseCase;
import com.bednarz.ports.PlaceOrderUseCase;
import com.bednarz.ports.model.CustomerInfo;
import com.bednarz.ports.model.OrderPort;
import com.bednarz.shop.infra.persistence.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopApi {

    private final RestOrderMapper mapper;
    private final PlaceOrderUseCase placeOrderUseCase;
    private final CustomerDataUseCase customerDataUseCase;
    private final JpaCustomerRepository customerRepository;

    @PostMapping("/order")
    public ResponseEntity<String> place(@RequestBody OrderDto orderDto) {
        var orderPort = mapper.toOrderPorts(orderDto);
        placeOrderUseCase.place(orderPort);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/order/{transactionId}")
    public ResponseEntity<String> place(@PathVariable Long transactionId, @RequestBody OrderDto orderDto) {
        orderDto.setTransactionId(transactionId);
        var orderPort = mapper.toOrderPorts(orderDto);
        placeOrderUseCase.change(orderPort);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/order/{transactionId}")
    public ResponseEntity<String> place(@PathVariable Long transactionId) {
        placeOrderUseCase.delete(transactionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reward/{customerId}")
    public ResponseEntity<CustomerInfo> showReward(@PathVariable Long customerId) {
        CustomerInfo info = customerDataUseCase.getInfo(customerId);
        return new ResponseEntity<>(info, HttpStatus.ACCEPTED);
    }
    @GetMapping("/reward")
    public ResponseEntity<List<CustomerInfo>> showRewardForAll() {
        List<CustomerInfo> info = customerDataUseCase.getInfo();
        return new ResponseEntity<>(info, HttpStatus.ACCEPTED);
    }

}
