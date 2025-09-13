-- Tabla Category
CREATE TABLE category (
    name VARCHAR(255) NOT NULL PRIMARY KEY,
    created_at DATETIME
);

-- Tabla Rooms
CREATE TABLE rooms (
    id CHAR(36) NOT NULL PRIMARY KEY,
    location_id CHAR(36),
    name VARCHAR(255),
    description TEXT,
    category VARCHAR(255),
    status VARCHAR(50),
    cost_per_day DECIMAL(19,2),
    price_per_day DECIMAL(19,2),
    capacity INT,
    number_of_beds INT,
    room_number INT,
    floor_number INT,
    smoking_allowed BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME,
    image_url VARCHAR(500)
);

-- Tabla Amenities
CREATE TABLE amenities (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    room_id CHAR(36) NOT NULL,
    CONSTRAINT fk_amenities_room FOREIGN KEY (room_id) REFERENCES rooms(id)
);

-- Tabla Appointments
CREATE TABLE appointments (
    id CHAR(36) NOT NULL PRIMARY KEY,
    description TEXT,
    location_id CHAR(36),
    id_client VARCHAR(255),
    status VARCHAR(50),
    sub_total DECIMAL(19,2),
    discount_amount DECIMAL(19,2) NOT NULL DEFAULT 0,
    discount_code VARCHAR(255),
    tax DECIMAL(19,2),
    total DECIMAL(19,2),
    created_at DATETIME,
    updated_at DATETIME,
    start_date DATE,
    end_date DATE,
    user_employee_id CHAR(36)
);

-- Tabla Item (para appointments)
CREATE TABLE item (
    id CHAR(36) NOT NULL PRIMARY KEY,
    room_id CHAR(36),
    room_name VARCHAR(255),
    quantity INT,
    unit_price DECIMAL(19,2),
    line_total DECIMAL(19,2),
    appointment_id CHAR(36) NOT NULL,
    CONSTRAINT fk_item_appointment FOREIGN KEY (appointment_id) REFERENCES appointments(id)
);
