package com.tbd.control5.backend;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @PostMapping("/institutions")
    public ResponseEntity<InstitutionModel> createInstitution(@RequestBody InstitutionModel institution) {
        try {
            institutionRepository.insert(institution);
            return new ResponseEntity<>(institution, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/institutions/{institution_id}")
    public ResponseEntity<InstitutionModel> findInstitution(@PathVariable("institution_id") String institution_id) {
        Optional<InstitutionModel> institution = institutionRepository.findById(institution_id);
        if (!institution.isPresent()) {
            throw new NotFoundException("La institución indicada no existe");
        }
        return new ResponseEntity<>(institution.get(),HttpStatus.OK);
    }

    @DeleteMapping("/institutions/{institution_id}")
    public ResponseEntity<InstitutionModel> deleteInstitution(@PathVariable("institution_id") String institution_id) {
        Optional<InstitutionModel> institution = institutionRepository.findById(institution_id);
        if (!institution.isPresent()) {
            throw new NotFoundException("La institución indicada no existe");
        }
        institutionRepository.deleteById(institution_id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/institutions/{institution_id}")
    public ResponseEntity<InstitutionModel> updateInstitution(@PathVariable("institution_id") String institution_id, @RequestBody InstitutionModel u_institution) {
        Optional<InstitutionModel> institution = institutionRepository.findById(institution_id);
        if (!institution.isPresent()) {
            throw new NotFoundException("La institución indicada no existe");
        }
        try {
            InstitutionModel result = institution.get();
            result.setName(u_institution.getName());
            result.setEmail(u_institution.getEmail());
            result.setContact(u_institution.getContact());
            institutionRepository.save(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/institutions/all")
    public ResponseEntity<List<InstitutionModel>> findAllInstitutions() {
        try {
            Iterable<InstitutionModel> records = institutionRepository.findAll();
            List<InstitutionModel> institutions = new ArrayList<>();
            records.forEach(institutions::add);
            return new ResponseEntity<>(institutions, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}