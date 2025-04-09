INSERT INTO public.users (username, name)
VALUES
    ('dude', 'some dude'),
    ('bro', 'some bro'),
    ('chief', 'master chief'),
    ('spart', 'leonidas k'),
    ('bama', 'pr. obama'),
    ('santa', 'st. clause'),
    ('py', 'pythonista');

SELECT * FROM public.users;


INSERT INTO public.restaurants (name)
VALUES
    ('memes curry'),
    ('five guys'),
    ('pick');

SELECT * FROM public.restaurants;

INSERT INTO public.menu_items (name, restaurant_id)
VALUES
    ('noodles', 1),
    ('ramen', 1),
    ('burgers', 2);

SELECT * FROM public.menu_items;

INSERT INTO public.orders (user_id, restaurant_id)
VALUES
    (1, 1),
    (1, 2);

SELECT * FROM public.orders;


INSERT INTO public.orders_items (menu_item_id, order_id, quantity)
VALUES
    (1, 1, 3),
    (2, 2, 5),
    (1, 1, 1);

SELECT * FROM public.orders_items;

