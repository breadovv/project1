DROP DATABASE game_shop;

CREATE DATABASE game_shop;

\connect game_shop

CREATE TABLE "Genres" (
                          "genre_id" serial,
                          "genre" text,
                          PRIMARY KEY ("genre_id")
);

CREATE TABLE "Games" (
                         "game_id" serial,
                         "genre_id" int,
                         "title" text,
                         "price" money,
                         "release_date" date,
                         PRIMARY KEY ("game_id"),
                         CONSTRAINT "FK_Games.genre_id"
                             FOREIGN KEY ("genre_id")
                                 REFERENCES "Genres"("genre_id")
);

CREATE TABLE "Users" (
                        "user_id" serial,
                        "name" text,
                        "phone" text,
                        "email" text,
                        "password" text,
                        PRIMARY KEY ("user_id")
);

CREATE TABLE "Cart" (
                        "cart_id" serial,
                        "user_id" int,
                        "game_id" int,
                        "purchase_date" date,
                        "amount_of_games" int,
                        "total_price" money,
                        "status" smallint,
                        PRIMARY KEY ("cart_id"),
                        CONSTRAINT "FK_Cart.user_id"
                            FOREIGN KEY ("user_id")
                                REFERENCES "Users"("user_id"),
                        CONSTRAINT "FK_Cart.game_id"
                            FOREIGN KEY ("game_id")
                                REFERENCES "Games"("game_id")
);

