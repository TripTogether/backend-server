package TT.tripTogether.domain.route.dto;

import TT.tripTogether.domain.attraction.dto.AttractionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteDto {
    private List<AttractionDto> attractions;
}
