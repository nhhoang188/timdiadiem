package com.timdiadiem.service.impl;

import com.timdiadiem.model.Tour;
import com.timdiadiem.repository.TourRepository;
import com.timdiadiem.service.pkInterface.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class TourServiceImpl implements TourService {
    @Autowired
    TourRepository tourRepository;

    @Override
    public Page<Tour> findAll(Pageable pageable) {
        return tourRepository.findAll(pageable);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public void save(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public void delete(Tour tour) {
        tourRepository.delete(tour);
    }

    @Override
    public void deleteById(Long id) {
        tourRepository.deleteById(id);
    }
}
