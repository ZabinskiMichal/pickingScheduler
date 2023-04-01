package solution.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StoreModel {
    private List<String> pickers;
    private LocalTime pickingStartTime;
    private LocalTime pickingEndTime;

}
