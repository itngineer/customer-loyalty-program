package com.bednarz.shop.infra.rest;

import com.bednarz.ports.PlaceOrderUseCase;
import com.bednarz.shop.infra.persistence.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopApi {

    private final RestOrderMapper mapper;
    private final PlaceOrderUseCase placeOrderUseCase;
//    private final CustomerDataUseCase customerDataUseCase;
    private final JpaCustomerRepository customerRepository;

    @PostMapping("/order")
    public ResponseEntity<String> place(@RequestBody OrderDto orderDto) {
        var orderPort = mapper.toPorts(orderDto);
        if(!customerRepository.findById(orderDto.getCustomerId()).isPresent()){
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        placeOrderUseCase.place(orderPort);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/reward/{customerId}")
    public ResponseEntity<String> showReward(@PathVariable String customerId) {
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/reward")
    public ResponseEntity<String> showRewardFroAll() {
        return ResponseEntity.accepted().build();
    }
}
