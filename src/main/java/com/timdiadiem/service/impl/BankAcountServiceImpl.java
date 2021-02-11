package com.timdiadiem.service.impl;

import com.timdiadiem.model.BankAcount;
import com.timdiadiem.repository.BankAcountRepository;
import com.timdiadiem.service.pkInterface.BankAcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class BankAcountServiceImpl implements BankAcountService {
    @Autowired
    BankAcountRepository repository;

    @Override
    public Page<BankAcount> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<BankAcount> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(BankAcount bankAcount) {
        repository.save(bankAcount);
    }

    @Override
    public void delete(BankAcount bankAcount) {
        repository.delete(bankAcount);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
