package TT.tripTogether.domain.post.dto;

import TT.tripTogether.domain.post.Region;
import TT.tripTogether.domain.route.dto.RouteDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PostCreateReq {
    private String title;
    private String content;
    private int number;
    private Region region;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<RouteDto> routes;

}
