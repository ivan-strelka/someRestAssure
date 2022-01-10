package podamEx;

import lombok.Data;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.time.LocalDateTime;

@Data
public class TagsItem {
    private String name;
    @PodamStrategyValue(DateTimeStrategy.class)
    private LocalDateTime from;
    private int id;
}