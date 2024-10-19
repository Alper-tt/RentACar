package alp.dev.rentcar.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCarListResponse {
    private List<CustomerCarResponse> customerCars;
}
