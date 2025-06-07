package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.custom.response.*;
import com.ruanmoraes.student_management_api.dtos.request.GradeRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.GradeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GradeControllerDocs {
    @Operation(summary = "Find all grades",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved all grades",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = GradeWithStudentAndDisciplineResponseDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<GradeWithStudentAndDisciplineResponseDTO>> findAll();

    @Operation(summary = "Find all grades by student ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved grades for student",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentGradesResponseDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Student not found",
                            content = @Content())
            }
    )
    public ResponseEntity<StudentGradesResponseDTO> findAllGradesByStudentId(@PathVariable Long studentId);

    @Operation(summary = "Find average grades for each student",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved average grades for each student",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AverageStudentGradesResponseDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<AverageStudentGradesResponseDTO>> findAverageForEachStudent();

    @Operation(summary = "Find average grades for a specific student",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved average grades for student",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AverageStudentGradesResponseDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Student not found",
                            content = @Content())
            }
    )
    public ResponseEntity<AverageStudentGradesResponseDTO> findAverageStudentById(@PathVariable Long studentId);

    @Operation(summary = "Find students with above average grades",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved students with above average grades",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AverageStudentGradesResponseDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<AverageStudentGradesResponseDTO>> findAboveAverageStudents();

    @Operation(summary = "Calculate average of all grades",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully calculated average of all grades",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AverageOfAllGradesResponseDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<AverageOfAllGradesResponseDTO> calculateAverageAllGrades();

    @Operation(summary = "Calculate average grades by discipline",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully calculated average grades by discipline",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AverageByDisciplineResponseDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<AverageByDisciplineResponseDTO> calculateAverageGradesByDiscipline();

    @Operation(summary = "Create a new grade",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created grade",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = GradeResponseDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Enrollment not found",
                            content = @Content())
            }
    )
    public ResponseEntity<GradeResponseDTO> create
            (
                    @RequestParam(value = "studentId", required = true) Long studentId,
                    @RequestParam(value = "disciplineId", required = true) Long disciplineId,
                    @Valid @RequestBody GradeRequestDTO gradeRequestDTO
            );

    @Operation(summary = "Update an existing grade",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated grade",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = GradeResponseDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Enrollment not found",
                            content = @Content())
            }
    )
    public ResponseEntity<GradeResponseDTO> update(
            @RequestParam(value = "studentId", required = true) Long studentId,
            @RequestParam(value = "disciplineId", required = true) Long disciplineId,
            @Valid @RequestBody GradeRequestDTO gradeRequestDTO
    );
}
