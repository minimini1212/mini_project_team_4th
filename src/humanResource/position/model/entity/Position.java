package humanResource.position.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
    private int positionId;
    private String positionName;
    private String positionCode;
    private int rankOrder;
    private String description;
}
