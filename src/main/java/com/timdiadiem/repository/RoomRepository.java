package com.timdiadiem.repository;

import com.timdiadiem.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    void deleteById(Long id);
    List<Room> findAllByHotel_Id(Long id);
}
