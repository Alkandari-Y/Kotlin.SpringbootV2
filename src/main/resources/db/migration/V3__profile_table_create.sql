DROP TABLE IF EXISTS "profiles";
CREATE TABLE "profiles" (
    "id"   SERIAL PRIMARY KEY ,
    "user_id" INT REFERENCES public.users (id),
    "first_name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "phone_number" VARCHAR(255) NOT NULL
);