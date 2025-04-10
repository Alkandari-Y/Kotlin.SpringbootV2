ALTER TABLE public.users
    ADD email VARCHAR(255)
    CONSTRAINT unique_user_email UNIQUE
    DEFAULT NULL;

ALTER TABLE public.users
    ADD password VARCHAR(255)
    DEFAULT NULL;

SELECT * FROM public.users;


DO $FN$
    BEGIN
        FOR counter IN 1..100 LOOP
            UPDATE public.users
                SET email= 'someEmail' || counter || '@exmaple.com',  password='secretPassword123'
                WHERE id = counter;
        END LOOP;
    END;
$FN$
