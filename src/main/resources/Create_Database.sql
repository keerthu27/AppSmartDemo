/*First delete products table and the customers table */
DROP TABLE IF EXISTS public.products;
DROP TABLE IF EXISTS public.customers;
DROP SEQUENCE IF EXISTS public.customer_seq;
DROP SEQUENCE IF EXISTS public.products_seq;

CREATE SEQUENCE IF NOT EXISTS public.customer_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 1;

/* Create products table */
CREATE TABLE IF NOT EXISTS public.customers
(
    id bigint NOT NULL DEFAULT nextval('customer_seq'::regclass),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_deleted boolean NOT NULL DEFAULT false,
    created_at timestamp NOT NULL,
    modified_at timestamp,
    CONSTRAINT customer_id_pk PRIMARY KEY (id)
)



CREATE SEQUENCE IF NOT EXISTS public.products_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 1;

/* Create customers table */
CREATE TABLE IF NOT EXISTS public.products
(
    id bigint NOT NULL DEFAULT nextval('products_seq'::regclass),
    customer_id bigint NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(1024) COLLATE pg_catalog."default",
    price numeric(10,2) NOT NULL,
    is_deleted boolean DEFAULT false,
    created_at timestamp NOT NULL,
    modified_at timestamp,
    CONSTRAINT product_id_pk PRIMARY KEY (id),
    CONSTRAINT product_customer_id_fk FOREIGN KEY (customer_id)
        REFERENCES public.customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
