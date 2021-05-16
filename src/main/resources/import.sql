DROP TABLE IF EXISTS carts_products CASCADE;

DROP TABLE IF EXISTS carts CASCADE;

DROP TABLE IF EXISTS buyers CASCADE;

DROP TABLE IF EXISTS products CASCADE;

-------------------products-------------------------

CREATE SEQUENCE IF NOT EXISTS products_id_seq AS integer;

CREATE TABLE products (id integer PRIMARY KEY DEFAULT nextval('products_id_seq'), cost integer, title varchar(255));

ALTER SEQUENCE products_id_seq OWNED BY products.id;

-------------------buyers---------------------------

CREATE SEQUENCE IF NOT EXISTS buyers_id_seq AS integer;

CREATE TABLE buyers (id integer PRIMARY KEY DEFAULT nextval('buyers_id_seq'), name varchar(255));

ALTER SEQUENCE buyers_id_seq OWNED BY buyers.id;

-------------------carts----------------------------

CREATE SEQUENCE IF NOT EXISTS carts_id_seq AS integer;

CREATE TABLE carts (id integer PRIMARY KEY DEFAULT nextval('carts_id_seq'), buyer_id integer REFERENCES buyers (id));

ALTER SEQUENCE carts_id_seq OWNED BY carts.id;

ALTER TABLE buyers ADD COLUMN cart_id integer REFERENCES carts (id) ON DELETE CASCADE;

-------------------carts_products-------------------

CREATE TABLE carts_products (cart_id integer NOT NULL REFERENCES carts (id), product_id integer NOT NULL REFERENCES products (id));

-------------------insert---------------------------

INSERT INTO products (title, cost) VALUES ('bread', 30), ('milk', 30), ('egg', 80), ('butter', 80), ('chicken', 100), ('meat', 300), ('loaf', 35), ('flour', 100), ('olives', 70), ('yoghurt', 80), ('hamburger', 50), ('sweets', 250), ('biscuit', 120), ('sunflower oil', 100), ('cheese', 560), ('salt', 30), ('cream', 85), ('sugar', 40), ('cake', 200), ('bun', 25);

INSERT INTO buyers (name) VALUES ('john'), ('ann'), ('alex'), ('sergio'), ('suzan');

INSERT INTO carts (buyer_id) VALUES (1), (2), (3), (4), (5);

UPDATE buyers SET cart_id = (SELECT id FROM carts WHERE carts.buyer_id = buyers.id);

INSERT INTO carts_products (cart_id, product_id) VALUES (1, 1), (1, 3), (1, 6), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (3, 1), (3, 2), (4, 1), (4, 6), (5, 1), (5, 3), (5, 4), (5, 5);