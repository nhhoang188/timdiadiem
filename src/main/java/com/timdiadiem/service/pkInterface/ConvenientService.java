package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Convenient;

public interface ConvenientService extends serviceInterface<Convenient> {
    void deleteById(Long id);
    Iterable<Convenient> findConvenientByHotelId(Long id);
}
