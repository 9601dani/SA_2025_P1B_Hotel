INSERT INTO category (name, created_at) VALUES
                                            ('HABITACIONES',        '2025-01-01T12:00:00'),
                                            ('SUITES',              '2025-01-02T12:00:00'),
                                            ('SERVICIOS',           '2025-01-03T12:00:00'),
                                            ('RESTAURANTE & BAR',   '2025-01-04T12:00:00'),
                                            ('SPA & WELLNESS',      '2025-01-05T12:00:00'),
                                            ('EVENTOS & SALONES',   '2025-01-06T12:00:00');

INSERT INTO rooms (
    id, location_id, name, description, category, status,
    cost_per_day, price_per_day, capacity, number_of_beds, room_number, floor_number,
    smoking_allowed, created_at, updated_at, image_url
) VALUES
-- Oficina Central
('11111111-1111-1111-1111-111111111111', '550e8400-e29b-41d4-a716-446655440000',
 'Habitación Estándar', 'Habitación con cama doble y escritorio', 'HABITACIONES', 'AVAILABLE',
 50.00, 75.00, 2, 1, 101, 1, FALSE, '2025-09-05 10:00:00', '2025-09-05 10:00:00',
 'images/17b83ed4-1394-4f04-be39-08852c42e8a4'),

('22222222-2222-2222-2222-222222222222', '550e8400-e29b-41d4-a716-446655440000',
 'Suite Ejecutiva', 'Suite amplia con área de estar y minibar', 'SUITES', 'AVAILABLE',
 100.00, 150.00, 3, 2, 201, 2, TRUE, '2025-09-05 10:05:00', '2025-09-05 10:05:00',
 'images/17b83ed4-1394-4f04-be39-08852c42e8a4'),

-- Sucursal Sur
('33333333-3333-3333-3333-333333333333', '123e4567-e89b-12d3-a456-426614174002',
 'Habitación Deluxe', 'Habitación remodelada con balcón', 'HABITACIONES', 'MAINTENANCE',
 65.00, 95.00, 2, 1, 302, 4, FALSE, '2025-09-05 10:10:00', '2025-09-05 10:10:00',
 'images/17b83ed4-1394-4f04-be39-08852c42e8a4'),

('33333333-3333-3333-3333-333333333555', '123e4567-e89b-12d3-a456-426614174002',
 'Habitación Deluxe', 'Habitación remodelada con balcón', 'HABITACIONES', 'AVAILABLE',
 70.00, 100.00, 2, 1, 301, 3, FALSE, '2025-09-05 10:10:00', '2025-09-05 10:10:00',
 'images/17b83ed4-1394-4f04-be39-08852c42e8a4'),

-- Sucursal CDMX
('44444444-4444-4444-4444-444444444444', '123e4567-e89b-12d3-a456-426614174004',
 'Penthouse', 'Habitación de lujo con vista a Reforma', 'SUITES', 'AVAILABLE',
 200.00, 300.00, 4, 2, 401, 4, TRUE, '2025-09-05 10:20:00', '2025-09-05 10:20:00',
 'images/17b83ed4-1394-4f04-be39-08852c42e8a4');


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

-- A) 3 noches Habitación Estándar: subTotal=225.00, tax=27.00, desc=10.00 -> total=242.00
INSERT INTO appointments (
    id, description, location_id, id_client, status, sub_total,
    discount_amount, discount_code, tax, total,
    created_at, updated_at, start_date, end_date, user_employee_id
) VALUES (
             '55555555-1111-0000-0000-000000000001',
             'Reserva 3 noches Habitación Estándar',
             '550e8400-e29b-41d4-a716-446655440000',
             'CUST-001',
             'CREATED',
             225.00,
             10.00, 'WELCOME10',
             27.00,
             242.00,
             '2025-09-05 12:00:00',
             '2025-09-05 12:05:00',
             '2025-09-10',
             '2025-09-13',
             'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaa0001'
         );

-- B) 1 noche Penthouse: subTotal=300.00, tax=36.00, sin descuento -> total=336.00
INSERT INTO appointments (
    id, description, location_id, id_client, status, sub_total,
    discount_amount, discount_code, tax, total,
    created_at, updated_at, start_date, end_date, user_employee_id
) VALUES (
             '55555555-2222-0000-0000-000000000002',
             'Reserva 1 noche Penthouse',
             '123e4567-e89b-12d3-a456-426614174004',
             'CUST-002',
             'CREATED',
             300.00,
             0.00, '',
             36.00,
             336.00,
             '2025-09-06 09:30:00',
             '2025-09-06 09:45:00',
             '2025-09-15',
             '2025-09-16',
             'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaa0002'
         );

-- C) 2 noches Deluxe (Sucursal Sur): subTotal=190.00, tax=22.80, desc=20.00 -> total=192.80
INSERT INTO appointments (
    id, description, location_id, id_client, status, sub_total,
    discount_amount, discount_code, tax, total,
    created_at, updated_at, start_date, end_date, user_employee_id
) VALUES (
             '55555555-3333-0000-0000-000000000003',
             'Reserva 2 noches Habitación Deluxe',
             '123e4567-e89b-12d3-a456-426614174002',
             'CUST-003',
             'CANCELLED',
             190.00,
             20.00, 'PROMO20',
             22.80,
             192.80,
             '2025-09-07 14:00:00',
             '2025-09-07 14:15:00',
             '2025-09-20',
             '2025-09-22',
             'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaa0003'
         );
-- Items para la reserva A (3 noches Estándar, 75.00 x 3 = 225.00)
INSERT INTO item (
    id, room_id, room_name, quantity, unit_price, line_total, appointment_id
) VALUES (
             '66666666-1111-0000-0000-000000000001',
             '11111111-1111-1111-1111-111111111111',
             'Habitación Estándar',
             3,
             75.00,
             225.00,
             '55555555-1111-0000-0000-000000000001'
         );

-- Items para la reserva B (1 noche Penthouse, 300.00 x 1 = 300.00)
INSERT INTO item (
    id, room_id, room_name, quantity, unit_price, line_total, appointment_id
) VALUES (
             '66666666-2222-0000-0000-000000000002',
             '44444444-4444-4444-4444-444444444444',
             'Penthouse',
             1,
             300.00,
             300.00,
             '55555555-2222-0000-0000-000000000002'
         );

-- Items para la reserva C (2 noches Deluxe, 95.00 x 2 = 190.00)
INSERT INTO item (
    id, room_id, room_name, quantity, unit_price, line_total, appointment_id
) VALUES (
             '66666666-3333-0000-0000-000000000003',
             '33333333-3333-3333-3333-333333333333',
             'Habitación Deluxe',
             2,
             95.00,
             190.00,
             '55555555-3333-0000-0000-000000000003'
         );

