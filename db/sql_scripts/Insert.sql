INSERT INTO "Genres" (name) VALUES
    ('Adventure'),
    ( 'Simulation'),
    ( 'Sports'),
    ( 'Fighting'),
    ( 'Horror'),
    ( 'Indie'),
    ( 'Shooter');

INSERT INTO "Games"("genre_id", "title", price, release_date)
VALUES
    (1, 'Uncharted 4: A Thief''s End', 39.99, '2016-05-10'),

    (1, 'Assassin''s Creed Valhalla', 49.99, '2020-11-10'),

    (2, 'The Sims 4', 29.99, '2014-09-02'),

    (2, 'Stardew Valley', 14.99, '2016-02-26'),

    (3, 'FIFA 23', 69.99, '2022-09-30'),

    (3, 'NBA 2K23', 59.99, '2022-09-09'),

    (4, 'Tekken 7', 39.99, '2017-06-02'),

    (4, 'Mortal Kombat 11', 49.99, '2019-04-23'),

    (5, 'Resident Evil Village', 59.99, '2021-05-07'),

    (6, 'Hollow Knight', 14.99, '2017-02-24'),

    (7, 'Call of Duty: Modern Warfare II', 69.99, '2022-10-28'),

    (7, 'DOOM Eternal', 39.99, '2020-03-20'),

    (5, 'MiSide', 10.00, '2024-12-11');
