package com.timdiadiem.service.impl;

import com.timdiadiem.model.Convenient;
import com.timdiadiem.repository.ConvenientRepository;
import com.timdiadiem.service.pkInterface.ConvenientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ConvenientServiceImpl implements ConvenientService {
    @Autowired
    ConvenientRepository convenientRepository;
    @Override
    public Page<Convenient> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Convenient> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Convenient convenient) {

    }

    @Override
    public void delete(Convenient convenient) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Iterable<Convenient> findConvenientByHotelId(Long id) {
        return convenientRepository.findConvenientByHotelId(id);
    }
}
