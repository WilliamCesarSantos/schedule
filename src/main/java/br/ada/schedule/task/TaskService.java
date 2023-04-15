package br.ada.schedule.task;

import br.ada.schedule.common.ResourceConflictException;
import br.ada.schedule.common.ResourceNotFoundException;
import br.ada.schedule.task.exception.TaskAlreadyClosedException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class TaskService {

    private final TaskRepository repository;

    public TaskService(final TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(@Valid final Task task) {
        if (task.getId() != null) {
            throw new ResourceConflictException();
        }
        return repository.save(task);
    }

    public List<Task> list() {
        return repository.findAll();
    }

    public Optional<Task> findById(final Long id) {
        return repository.findById(id);
    }

    public Task update(@Valid final Task task) {
        Task inDatabase = findById(task.getId())
                .map(it -> {
                            task.setCreatedAt(it.getCreatedAt());
                            return it;
                        }
                ).orElseThrow(ResourceNotFoundException::new);
        canEdit(inDatabase);
        this.updateCloseAt(task, task.getClosedAt());
        return this.repository.save(task);
    }

    private void canEdit(final Task task) {
        if (task.getStatus() == TaskStatus.CLOSE) {
            throw new TaskAlreadyClosedException();
        }
    }

    private void updateCloseAt(final Task task, final LocalDate closedAt) {
        if (task.getStatus() == TaskStatus.CLOSE) {
            final LocalDate newClosedAt = Optional.ofNullable(closedAt).orElse(LocalDate.now());
            task.setClosedAt(newClosedAt);
        } else if (task.getStatus() == TaskStatus.OPEN) {
            task.setClosedAt(null);
        }
    }
}