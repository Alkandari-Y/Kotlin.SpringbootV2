
-- Requires V1 data and migrations upto V2
-- Updates users up to ID 100 with new data for email and password
DO $FN$
    BEGIN
        FOR counter IN 1..100 LOOP
            UPDATE public.users
                SET email= 'someEmail' || counter || '@exmaple.com',  password='secretPassword123'
                WHERE id = counter;
        END LOOP;
    END;
$FN$
