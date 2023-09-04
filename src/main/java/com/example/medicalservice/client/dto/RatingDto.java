package com.example.medicalservice.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RatingDto {
    Integer ratingId;
    Integer rating;
    String review;
    LocalDateTime date;
    Integer centreId;
    Set<UserDto> user;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
