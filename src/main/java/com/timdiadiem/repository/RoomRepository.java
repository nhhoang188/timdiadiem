package com.timdiadiem.repository;

import com.timdiadiem.model.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    void deleteById(Long id);
    Iterable<Room> findRoomByHotelId(Long id);
}
