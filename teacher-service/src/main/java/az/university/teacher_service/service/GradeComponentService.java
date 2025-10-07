package az.university.teacher_service.service;

import az.university.teacher_service.exception.GradeComponentNotFoundException;
import az.university.teacher_service.model.GradeComponent;
import az.university.teacher_service.model.Lesson;
import az.university.teacher_service.repository.GradeComponentRepository;
import az.university.teacher_service.request.CreateComponentRequest;
import az.university.teacher_service.request.UpdateComponentRequest;
import az.university.teacher_service.response.ComponentAddResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GradeComponentService {
    private final ModelMapper modelMapper;
    private GradeComponentRepository gradeComponentRepository;
    private LessonService lessonService;

    public GradeComponentService(GradeComponentRepository gradeComponentRepository, LessonService lessonService, ModelMapper modelMapper) {
        this.gradeComponentRepository = gradeComponentRepository;
        this.lessonService = lessonService;
        this.modelMapper = modelMapper;
    }


    public ComponentAddResponse createComponent(Long lessonId, final CreateComponentRequest request) {

        Lesson lesson = lessonService.findLessonById(lessonId);

        GradeComponent gradeComponent = new GradeComponent();

        modelMapper.map(request, gradeComponent);

        gradeComponent.setLesson(lesson);

        gradeComponentRepository.save(gradeComponent);

        ComponentAddResponse response = new ComponentAddResponse();
        response.setComponentId(gradeComponent.getId());

        return response;
    }

    public ComponentAddResponse updateComponent(Long id, final UpdateComponentRequest request) {

        GradeComponent component=findGradeComponentById(id);

        component.setName(request.getName());
        component.setMaxValue(request.getMaxValue());

        gradeComponentRepository.save(component);
        ComponentAddResponse response = new ComponentAddResponse();
        response.setComponentId(component.getId());
        return response;
    }

    protected GradeComponent findGradeComponentById(Long id) {

        return gradeComponentRepository.findById(id).orElseThrow(() -> new GradeComponentNotFoundException("Component could not be found by following ! " + id));

    }


}
