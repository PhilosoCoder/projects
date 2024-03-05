package hu.geralt.mappers.beer;

import hu.geralt.entities.beer.Customer;
import hu.geralt.dtos.beer.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto dto);

    CustomerDto customerToCustomerDto(Customer customer);

}
