package com.ruanmoraes.student_management_api.controllers;

import com.ruanmoraes.student_management_api.dtos.request.EnrollmentRequestDTO;
import com.ruanmoraes.student_management_api.dtos.response.EnrollmentResponseDTO;
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

public interface EnrollmentControllerDocs {
    @Operation(
            summary = "Find all enrollments",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all enrollments",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EnrollmentResponseDTO.class)
                            )
                    )
            }
    )
    ResponseEntity<List<EnrollmentResponseDTO>> findAll();

    @Operation(
            summary = "Find enrollment by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved enrollment",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EnrollmentResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Enrollment not found",
                            content = @Content()
                    )
            }
    )
    ResponseEntity<EnrollmentResponseDTO> findById(@PathVariable Long id);

    @Operation(
            summary = "Find enrollment by studentId and disciplineId",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved enrollment",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EnrollmentResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Enrollment not found",
                            content = @Content()
                    )
            }
    )
    ResponseEntity<EnrollmentResponseDTO> findByStudentIdAndDisciplineId(
            @RequestParam(value = "studentId", required = true) Long studentId,
            @RequestParam(value = "disciplineId", required = true) Long disciplineId);

    @Operation(
            summary = "Create a new enrollment",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created enrollment",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EnrollmentResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Enrollment already exists for the given student and discipline",
                            content = @Content()
                    )
            }
    )
    ResponseEntity<EnrollmentResponseDTO> create(@Valid @RequestBody EnrollmentRequestDTO enrollmentRequestDTO);

    @Operation(
            summary = "Delete enrollment by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted enrollment"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Enrollment not found",
                            content = @Content()
                    )
            }
    )
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
