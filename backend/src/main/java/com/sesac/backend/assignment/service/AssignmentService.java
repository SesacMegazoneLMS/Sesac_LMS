package com.sesac.backend.assignment.service;

import com.sesac.backend.entity.Assignment;
import com.sesac.backend.assignment.dto.AssignmentDto;
import com.sesac.backend.assignment.repository.AssignmentDao;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {

    private final AssignmentDao assignmentDao;

    public AssignmentService(AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public AssignmentDto save(AssignmentDto assignmentDto) {
        Assignment assignment = assignmentDao.save(
            new Assignment(assignmentDto.getAssignId(), assignmentDto.getCourse(),
                assignmentDto.getTitle(), assignmentDto.getDescription()));

        return new AssignmentDto(assignment.getAssignId(), assignment.getCourse(),
            assignment.getTitle(), assignment.getDescription());
    }

    public List<AssignmentDto> getAll() {
        return assignmentDao.findAll().stream().map(entity -> new AssignmentDto(entity.getAssignId(),
            entity.getCourse(), entity.getTitle(), entity.getDescription())).toList();
    }

    public AssignmentDto findById(UUID id) {
        Assignment entity = assignmentDao.findById(id).orElse(null);

        return entity == null ? null
            : new AssignmentDto(entity.getAssignId(), entity.getCourse(), entity.getTitle(),
                entity.getDescription());
    }

    public void delete(UUID id) {
        assignmentDao.deleteById(id);
    }
}