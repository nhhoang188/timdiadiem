package com.timdiadiem.service.impl;

import com.timdiadiem.model.Province;
import com.timdiadiem.repository.ProvinceRepository;
import com.timdiadiem.service.pkInterface.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    ProvinceRepository repository;

    @Override
    public Page<Province> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Province> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Province province) {

    }

    @Override
    public void delete(Province province) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
