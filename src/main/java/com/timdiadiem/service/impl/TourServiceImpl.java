package com.timdiadiem.service.impl;

import com.timdiadiem.model.Tour;
import com.timdiadiem.repository.TourRepository;
import com.timdiadiem.service.pkInterface.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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

    @Override
    public Page<Tour> findAllByNameContaining(String name, Pageable pageable) {
        return tourRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Page<Tour> findAllByPrice(Double price, Pageable pageable) {
        return tourRepository.findAllByPrice(price,pageable);
    }

    @Override
    public Page<Tour> findTourByPriceLessThanEqual(Double price, Pageable pageable) {
        return tourRepository.findTourByPriceLessThanEqual(price,pageable);
    }

    @Override
    public Page<Tour> findTourByNameAndPriceLessThanEqual(String name, Double price, Pageable pageable) {
        return tourRepository.findTourByNameAndPriceLessThanEqual(name,price,pageable);
    }

    @Override
    public Page<Tour> findTourByNameAndPrice(String name, Double price, Pageable pageable) {
        return tourRepository.findTourByNameAndPrice(name,price,pageable);
    }
}
