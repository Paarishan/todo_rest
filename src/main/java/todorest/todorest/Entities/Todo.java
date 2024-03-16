package todorest.todorest.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

	@Column
    private String task;
	
	@Column
    private boolean completed;
	
	public Todo(long id, String task, boolean completed) {
		super();
		this.id = id;
		this.task = task;
		this.completed = completed;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
