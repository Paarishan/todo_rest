package todorest.todorest.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDto {
 
@NotBlank
private String task;

public String getTask() {
	return task;
}

public void setTask(String task) {
	this.task = task;
}


}
