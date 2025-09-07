INSERT INTO category (name, created_at) VALUES
('HABITACIONES',        '2025-01-01T12:00:00'),
('SUITES',              '2025-01-02T12:00:00'),
('SERVICIOS',           '2025-01-03T12:00:00'),
('RESTAURANTE & BAR',   '2025-01-04T12:00:00'),
('SPA & WELLNESS',      '2025-01-05T12:00:00'),
('EVENTOS & SALONES',   '2025-01-06T12:00:00');

INSERT INTO rooms (
    id, location_id, name, description, category, status,
    price_per_day, capacity, number_of_beds, room_number, floor_number,
    smoking_allowed, created_at, updated_at
) VALUES
-- Oficina Central
('11111111-1111-1111-1111-111111111111', '550e8400-e29b-41d4-a716-446655440000',
 'Habitación Estándar', 'Habitación con cama doble y escritorio', 'HABITACIONES', 'AVAILABLE',
 75.00, 2, 1, 101, 1, FALSE, '2025-09-05 10:00:00', '2025-09-05 10:00:00'),

('22222222-2222-2222-2222-222222222222', '550e8400-e29b-41d4-a716-446655440000',
 'Suite Ejecutiva', 'Suite amplia con área de estar y minibar', 'SUITES', 'AVAILABLE',
 150.00, 3, 2, 201, 2, TRUE, '2025-09-05 10:05:00', '2025-09-05 10:05:00'),

-- Sucursal Sur
('33333333-3333-3333-3333-333333333333', '123e4567-e89b-12d3-a456-426614174002',
 'Habitación Deluxe', 'Habitación remodelada con balcón', 'HABITACIONES', 'MAINTENANCE',
 95.00, 2, 1, 301, 3, FALSE, '2025-09-05 10:10:00', '2025-09-05 10:10:00'),

-- Sucursal CDMX
('44444444-4444-4444-4444-444444444444', '123e4567-e89b-12d3-a456-426614174004',
 'Penthouse', 'Habitación de lujo con vista a Reforma', 'SUITES', 'AVAILABLE',
 300.00, 4, 2, 401, 4, TRUE, '2025-09-05 10:20:00', '2025-09-05 10:20:00');

INSERT INTO amenities (id, name, room_id) VALUES
-- Habitación Estándar
('aaaa1111-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'WiFi Gratis',  '11111111-1111-1111-1111-111111111111'),
('aaaa2222-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Televisión',   '11111111-1111-1111-1111-111111111111'),

-- Suite Ejecutiva
('bbbb1111-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Minibar',     '22222222-2222-2222-2222-222222222222'),
('bbbb2222-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Jacuzzi',     '22222222-2222-2222-2222-222222222222'),

-- Habitación Deluxe
('cccc1111-cccc-cccc-cccc-cccccccccccc', 'Balcón Privado', '33333333-3333-3333-3333-333333333333'),
('cccc2222-cccc-cccc-cccc-cccccccccccc', 'Smart TV',       '33333333-3333-3333-3333-333333333333'),

-- Penthouse
('dddd1111-dddd-dddd-dddd-dddddddddddd', 'Terraza Panorámica', '44444444-4444-4444-4444-444444444444'),
('dddd2222-dddd-dddd-dddd-dddddddddddd', 'Servicio de Mayordomo', '44444444-4444-4444-4444-444444444444');
