DROP TABLE IF EXISTS users;
CREATE TABLE "users" (
      "id" SERIAL PRIMARY KEY ,
      "username" VARCHAR(200),
      "name" VARCHAR(200)
);

DROP TABLE IF EXISTS "restaurants";
CREATE TABLE "restaurants" (
    "id"   SERIAL PRIMARY KEY ,
    "name" VARCHAR(200)
);

DROP TABLE IF EXISTS "menus";
CREATE TABLE "menus" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255),
    "restaurant_id" INT REFERENCES restaurants (id),
    "price" NUMERIC NOT NULL
);

DROP TABLE IF EXISTS "orders";
CREATE TABLE "orders" (
    "id" SERIAL PRIMARY KEY,
    "user_id" INT REFERENCES users (id),
    "restaurant_id" INT REFERENCES restaurants (id)
);

DROP TABLE IF EXISTS "orders_items";
CREATE TABLE "orders_items" (
    "id" SERIAL PRIMARY KEY,
    "menu_id" INT REFERENCES menus (id),
    "order_id" INT REFERENCES orders (id),
    "quantity" INT NOT NULL,
    CONSTRAINT quantity_non_negative CHECK(quantity>=0)
);



