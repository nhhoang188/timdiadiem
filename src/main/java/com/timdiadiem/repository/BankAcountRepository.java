package com.timdiadiem.repository;

import com.timdiadiem.model.BankAcount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BankAcountRepository extends PagingAndSortingRepository<BankAcount, Long> {
}
