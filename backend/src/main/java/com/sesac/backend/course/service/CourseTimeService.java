package com.sesac.backend.course.service;

import com.sesac.backend.course.dto.CourseTimeDto;
import com.sesac.backend.course.repository.CourseOpeningRepository;
import com.sesac.backend.course.repository.CourseTimeRepository;
import com.sesac.backend.entity.CourseOpening;
import com.sesac.backend.entity.CourseTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CourseTimeService {

    private final CourseTimeRepository courseTimeRepository;
    private final CourseOpeningRepository courseOpeningRepository;

    // 강의 시간등록 메소드
    public CourseTimeDto createCourseTime(CourseTimeDto courseTimeDto) {
        // 시간 유효성 검사
        validateTimeRange(courseTimeDto);

        // 강의 개설 정보 조회
        CourseOpening courseOpening = courseOpeningRepository.findById(courseTimeDto.getOpeningId())
                .orElseThrow(() -> new RuntimeException("강의 개설 정보를 찾을 수 없습니다."));

        // CourseTime 엔티티 생성
        CourseTime courseTime = CourseTime.builder()
                .courseOpening(courseOpening)
                .dayOfWeek(courseTimeDto.getDayOfWeek())
                .startTime(courseTimeDto.getStartTime())
                .endTime(courseTimeDto.getEndTime())
                .classroom(courseTimeDto.getClassroom())
                .build();

        // 저장
        CourseTime savedTime = courseTimeRepository.save(courseTime);
        log.info("Created course time: {}", savedTime);

        return convertToDto(savedTime);
    }

    // 모든 강의 시간 조회 메소드
    @Transactional(readOnly = true)
    public List<CourseTimeDto> getAllCourseTimes() {
        return courseTimeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 특정 강의 시간 조회 메소드
    @Transactional(readOnly = true)
    public CourseTimeDto getCourseTime(UUID timeId) {
        CourseTime courseTime = courseTimeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("강의 시간을 찾을 수 없습니다."));
        return convertToDto(courseTime);
    }



    // 강의 시간 수정
    public CourseTimeDto updateCourseTime(UUID timeId, CourseTimeDto courseTimeDto) {
        // 시간 유효성 검사
        validateTimeRange(courseTimeDto);

        CourseTime courseTime = courseTimeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("강의 시간을 찾을 수 없습니다."));

        // 강의 개설 정보 조회 (변경된 경우)
        if (!courseTime.getCourseOpening().getOpeningId().equals(courseTimeDto.getOpeningId())) {
            CourseOpening newCourseOpening = courseOpeningRepository.findById(courseTimeDto.getOpeningId())
                    .orElseThrow(() -> new RuntimeException("강의 개설 정보를 찾을 수 없습니다."));
            courseTime.setCourseOpening(newCourseOpening);
        }

        // 내용 업데이트
        courseTime.setDayOfWeek(courseTimeDto.getDayOfWeek());
        courseTime.setStartTime(courseTimeDto.getStartTime());
        courseTime.setEndTime(courseTimeDto.getEndTime());
        courseTime.setClassroom(courseTimeDto.getClassroom());

        CourseTime updatedTime = courseTimeRepository.save(courseTime);
        return convertToDto(updatedTime);
    }

    // 강의 시간 삭제
    public void deleteCourseTime(UUID timeId) {
        CourseTime courseTime = courseTimeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("강의 시간을 찾을 수 없습니다."));

        courseTimeRepository.delete(courseTime);
    }

    /**
     * Entity를 DTO로 변환하는 private 메서드
     */
    private CourseTimeDto convertToDto(CourseTime courseTime) {
        return CourseTimeDto.builder()
                .timeId(courseTime.getTimeId())
                .openingId(courseTime.getCourseOpening().getOpeningId())
                .dayOfWeek(courseTime.getDayOfWeek())
                .startTime(courseTime.getStartTime())
                .endTime(courseTime.getEndTime())
                .classroom(courseTime.getClassroom())
                .build();
    }



    /**
     * 시간 범위 유효성을 검사하는 private 메서드
     */
    private void validateTimeRange(CourseTimeDto dto) {
        if (dto.getStartTime() != null && dto.getEndTime() != null &&
                !dto.getEndTime().isAfter(dto.getStartTime())) {
            throw new IllegalArgumentException("종료 시간은 시작 시간보다 늦어야 합니다.");
        }
    }
}