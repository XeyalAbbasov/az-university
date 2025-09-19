package az.university.teacher_service.service;

import az.university.teacher_service.exception.TutorNotFoundException;
import az.university.teacher_service.model.Tutor;
import az.university.teacher_service.repository.TutorRepository;
import az.university.teacher_service.request.CreateTutorRequest;
import az.university.teacher_service.response.TutorAddResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TutorService {


    private TutorRepository tutorRepository;
    private ModelMapper modelMapper;

    public TutorService(TutorRepository tutorRepository, ModelMapper modelMapper) {
        this.tutorRepository = tutorRepository;
        this.modelMapper = modelMapper;
    }

    public TutorAddResponse create(CreateTutorRequest request) {

        Tutor tutor = new Tutor();
        modelMapper.map(request,tutor);
        tutor.setActive(true);
        tutorRepository.save(tutor);

        TutorAddResponse response = new TutorAddResponse();
        response.setTutorId(tutor.getId());

        return response;
    }

    public void activateTutor(Long tutorId) {

        Tutor tutor = findTutorById(tutorId);
        tutor.setActive(true);
        tutorRepository.save(tutor);
    }

    public void deactivateTutor(Long tutorId) {

        Tutor tutor = findTutorById(tutorId);
        tutor.setActive(false);
        tutorRepository.save(tutor);
    }

    protected Tutor findTutorById(Long id){

        return tutorRepository.findById(id).orElseThrow(()-> new TutorNotFoundException("Tutor could not be found by following ! "+id));
    }
}
