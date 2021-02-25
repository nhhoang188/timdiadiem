package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Room;

import java.util.List;

public interface RoomService extends serviceInterface<Room> {
    List<Room> findAllByHotel(Long id);
}
