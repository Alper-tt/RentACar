package alp.dev.rentcar.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantCarListResponse {

    private List<MerchantCarResponse> merchantCars;

}
