package com.timdiadiem.service.pkInterface;

import com.timdiadiem.model.Room;

public interface RoomService extends serviceInterface<Room> {
    Iterable<Room> findRoomByHotelId(Long id);
}
