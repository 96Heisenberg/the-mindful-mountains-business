CREATE TABLE day_itinerary_room (
    day_id VARCHAR(255) NOT NULL,
    room_id VARCHAR(255) NOT NULL,

    CONSTRAINT pk_day_itinerary_room
        PRIMARY KEY (day_id, room_id),

    CONSTRAINT fk_day_itinerary_room_day
        FOREIGN KEY (day_id)
        REFERENCES day_itinerary(day_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_day_itinerary_room_room
        FOREIGN KEY (room_id)
        REFERENCES room(room_id)
        ON DELETE CASCADE
);