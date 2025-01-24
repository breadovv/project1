CREATE TABLE "Genres" (
                          "Genre_id" serial,
                          "Genre" text,
                          PRIMARY KEY ("Genre_id")
);

CREATE TABLE "Games" (
                         "Game_id" serial,
                         "Genre_id" int,
                         "Title" text,
                         "price" money,
                         "release_date" date,
                         PRIMARY KEY ("Game_id"),
                         CONSTRAINT "FK_Games.Genre_id"
                             FOREIGN KEY ("Genre_id")
                                 REFERENCES "Genres"("Genre_id")
);

CREATE TABLE "User" (
                        "User_ID" serial,
                        "Name" text,
                        "Phone" text,
                        "Email" text,
                        PRIMARY KEY ("User_ID")
);

CREATE TABLE "Cart" (
                        "Cart_id" serial,
                        "User_id" int,
                        "Game_id" int,
                        "Purchase_date" date,
                        "Amount_of_games" int,
                        "Total_price" money,
                        "Status" smallint,
                        PRIMARY KEY ("Cart_id"),
                        CONSTRAINT "FK_Cart.Cart_id"
                            FOREIGN KEY ("Cart_id")
                                REFERENCES "User"("User_ID"),
                        CONSTRAINT "FK_Cart.Game_id"
                            FOREIGN KEY ("Game_id")
                                REFERENCES "Games"("Game_id")
);

