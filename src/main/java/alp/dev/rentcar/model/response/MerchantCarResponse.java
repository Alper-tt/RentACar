package alp.dev.rentcar.model.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantCarResponse {
    
    private Integer id;
    private String brand;
    private String color;
    private String model;
    private Integer year;

}
