package com.timdiadiem.service.impl;

import com.timdiadiem.model.City;
import com.timdiadiem.repository.CityRepository;
import com.timdiadiem.service.pkInterface.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository repository;
    @Override
    public Page<City> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<City> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(City city) {

    }

    @Override
    public void delete(City city) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
