INSERT INTO "Users" (user_id, name, phone, email) VALUES
    (1, 'nagibator_228', '88005553535', 'nagibator@gmail.com'),
    (2, 'breadovv', '87777777777', 'breadov@gmail.com'),
    (3, 'doni', '86666666666', 'doni@gmail.com'),
    (4, 'nurgaly', '85555555555', 'nurgaly@gmail.com');

INSERT INTO "Genres" (genre_id, genre) VALUES
    (1, 'Adventure'),
    (2, 'Simulation'),
    (3, 'Sports'),
    (4, 'Fighting'),
    (5, 'Horror'),
    (6, 'Indie'),
    (7, 'Shooter');

INSERT INTO public."Games"("genre_id", "title", price, release_date)
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
