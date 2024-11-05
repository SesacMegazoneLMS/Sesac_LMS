package com.sesac.backend.assignment.controller;

import com.sesac.backend.assignment.dto.AssignmentDto;
import com.sesac.backend.assignment.service.AssignmentService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RequestMapping("/assignments/detail")
@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("")
    public List<AssignmentDto> getAllAssignments() {
        return assignmentService.getAll();
    }

    @GetMapping("/{id}")
    public Map<String, AssignmentDto> getAssignmentById(@PathVariable UUID id) {
        return Map.of("assignment", assignmentService.findById(id));
    }

    @PostMapping("")
    public Map<String, AssignmentDto> addAssignment(AssignmentDto assignmentDto) {
        AssignmentDto saved = assignmentService.save(assignmentDto);

        return Map.of("assignment", new AssignmentDto(
            saved.getAssignId(), saved.getCourse(), saved.getTitle(), saved.getDescription()
        ));
    }

    @PutMapping("")
    public Map<String, AssignmentDto> updateAssignment(AssignmentDto assignmentDto) {
        AssignmentDto saved = assignmentService.findById(assignmentDto.getAssignId());
        saved.setCourse(assignmentDto.getCourse());
        saved.setTitle(assignmentDto.getTitle());
        saved.setDescription(assignmentDto.getDescription());
        assignmentService.save(saved);
        return Map.of("assignment", new AssignmentDto(
            saved.getAssignId(), saved.getCourse(), saved.getTitle(), saved.getDescription()
        ));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteAssignment(@PathVariable UUID id) {
        assignmentService.delete(id);
        return Map.of("deleted", true);
    }
}