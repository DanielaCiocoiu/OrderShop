package server.convert;

import lib.dto.AddressDTO;
import lib.dto.OrderDTO;
import lib.dto.UserDTO;
import server.model.Address;
import server.model.Order;
import server.model.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

public class OrderConvertor {
    private OrderConvertor() {}

    public static Order convert(OrderDTO orderDto) {

      User user = UserConvertor.convert(orderDto.getUserDTO());

        Order order = new Order.Builder()
                .setId(orderDto.getId())
                .setTelephones(orderDto.getTelephones())
                .setTimestamp(orderDto.getTimestamp())
                .setTotal(orderDto.getTotal())
                .setUser(user)
                .build();

        Optional.ofNullable(orderDto.getAddress())
                .ifPresent(addressDTO -> {
                    var address = new Address();
                    address.setNumber(addressDTO.getNumber());
                    address.setStreet(addressDTO.getStreets());
                    order.setAddress(address);
                });

        return order;

    }

    public static OrderDTO convert(Order order) {
       UserDTO userDTO = UserConvertor.convert(order.getUser());

        OrderDTO orderDTO = new OrderDTO.Builder()
                .setTotal(order.getTotal())
                .setTimestamp(order.getTimestamp())
                .setTelephones(new HashSet<>(order.getTelephones()))
                .setIdProducts(Collections.emptySet())
                .setUser(userDTO)
                .build();

        Optional.ofNullable(order.getAddress())
                .ifPresent(address -> {
                    orderDTO.setAddress(new AddressDTO(
                            address.getStreet(),
                            address.getNumber()
                    ));
                });

        return orderDTO;
    }
}
