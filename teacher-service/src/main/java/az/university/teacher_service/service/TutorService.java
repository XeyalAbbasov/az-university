package az.university.teacher_service.service;

import az.university.teacher_service.client.AuthenticationClient;
import az.university.teacher_service.exception.MyException;
import az.university.teacher_service.exception.TutorNotFoundException;
import az.university.teacher_service.model.Tutor;
import az.university.teacher_service.repository.TutorRepository;
import az.university.teacher_service.request.CreateTutorRequest;
import az.university.teacher_service.request.UpdateTutorRequest;
import az.university.teacher_service.response.TutorAddResponse;
import az.university.teacher_service.util.Constants;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TutorService {


    private TutorRepository tutorRepository;
    private ModelMapper modelMapper;
    private AuthenticationClient authenticationClient;

    public TutorService(TutorRepository tutorRepository, ModelMapper modelMapper, AuthenticationClient authenticationClient) {
        this.tutorRepository = tutorRepository;
        this.modelMapper = modelMapper;
        this.authenticationClient = authenticationClient;
    }

    @Value("${internal.api.key}")
    private String internalApiKey;

    public TutorAddResponse create(final CreateTutorRequest request) {

        //done
        authenticationClient.checkUsernameExists(request.getUsername(),internalApiKey);

        Tutor tutor = new Tutor();
        modelMapper.map(request, tutor);
        tutor.setActive(true);
        tutorRepository.save(tutor);

        authenticationClient.sendTutorToAuth(tutor.getId(), request, internalApiKey, "ROLE_CONTROL_TUTOR");

        TutorAddResponse response = new TutorAddResponse();
        response.setTutorId(tutor.getId());

        return response;
    }

    public String update(Long tutorId, final UpdateTutorRequest request, String username) {

        Tutor tutor = findTutorById(tutorId);

        Long loggedId = authenticationClient.getUserIdByUsername(username, internalApiKey);

        if (!tutor.getId().equals(loggedId)) {
            throw new MyException("You must update your own lesson !", null, Constants.POSSESSION);
        } else {
            tutor.setFirstName(request.getFirstName());
            tutor.setLastName(request.getLastName());
            tutor.setFatherName(request.getFatherName());
            tutor.setEmail(request.getEmail());
            tutor.setPhone(request.getPhone());

            tutorRepository.save(tutor);

            return "Tutor has been updated successfully ";
        }
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

    protected Tutor findTutorById(Long id) {

        return tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException("Tutor could not be found by following ! " + id));
    }

}
