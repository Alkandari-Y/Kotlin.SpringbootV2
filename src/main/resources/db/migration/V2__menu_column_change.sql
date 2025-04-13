ALTER TABLE public.users
    ADD email VARCHAR(255)
    CONSTRAINT unique_user_email UNIQUE
    DEFAULT NULL;

ALTER TABLE public.users
    ADD password VARCHAR(255)
    DEFAULT NULL;

SELECT * FROM public.users;
