package todo.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Getter    //getter, setter ,toString,equals, hashCode 컴파일할때 자동 생성해준다.
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoVO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

}
